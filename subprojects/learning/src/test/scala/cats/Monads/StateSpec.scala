package test.learning.cats.Monads

import cats.data.State
import cats.effect.IO
import org.scalatest.FunSuite

class StateSpec extends FunSuite {

  test("State make a computation based on an initial state") {
    val a = State[Int, String] { initialState =>
      val nextState = initialState*2
      val result = s"value is: $nextState"
      (nextState, result)
    }

    // pass initial state
    val result1 = a.run(10).value

    assert((20, "value is: 20") === result1) // (state, result)
  }

  test("run(), runS(), runA()") {
      val a = State[Int, String] { x =>
        (x, s"value is: $x")
      }

      val result1 = a.run(10).value
      val result2 = a.runS(10).value
      val result3 = a.runA(10).value

      assert((10, "value is: 10") === result1)
      assert(10 === result2)
      assert("value is: 10" === result3)
  }

  test("State can compose and chain") {
    val step1 = State[Int, String] { input =>
      (input + 1, s"From step 1: ${input + 1}")
    }

    val step2 = State[Int, String] { input =>
      (input * 2, s"From step 2: ${input * 2}")
    }

    val both = for {
      a <- step1  // (+1)
      b <- step2  // (*2)
    } yield (a, b)

    val result = both.run(20).value

    assert(
      // (state, result)
      (42, ("From step 1: 21", "From step 2: 42")) === result
    )
  }

  test("get(), set(), pure(), inspect(), modify()") {
    val resultGet = State.get[Int].run(10).value
    val resultSet = State.set[Int](30).run(10).value
    val resultPure = State.pure[Int, String]("Result").run(10).value
    val resultInspect = State.inspect[Int, String](_ + "!").run(10).value
    val resultModify = State.modify[Int](_ + 1).run(10).value

    // (state, result)
    assert((10, 10)       === resultGet, "extract the state as the result")
    assert((30, ())       === resultSet, "update state, and return unit as the result")
    assert((10, "Result") === resultPure, "ignore state and return supplied result")
    assert((10, "10!")    === resultInspect, "extract the state via a transformation function")
    assert((11, ())       === resultModify, "update the state using an update function")
  }

  test("composing with get(), set(), ....") {
    import State._

    val chain = for {
      a <- get[Int]
      _ <- set[Int](a+1)
      b <- get[Int]
      _ <- modify[Int](_ + 1)
      c <- get[Int]
      d <- inspect[Int, Int](_ * 1000)
    } yield (a, b, c, d)

    val result = chain.run(1).value

    assert(
      // (state, result)
      (3, (1,2,3,3000)) === result
    )
  }

  test("use case") {
    case class GolfState(distance: Int)

    def swing(distance: Int): State[GolfState ,Int] = State {
      (s: GolfState) =>
        val newAmount = s.distance + distance
        (GolfState(newAmount), newAmount)
    }

    val stateWithNewDistance: State[GolfState, Int] = for {
      _ <- swing(20)
      _ <- swing(15)
      totalDistance <- swing(0)
    } yield totalDistance


    val result: (GolfState, Int) = stateWithNewDistance
      .run(GolfState(0)) // initial distance
      .value

    assert(
      (GolfState(35), 35) === result
    )
  }
}
