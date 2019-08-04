package test.learning.cats.Monads

import cats.data.State
import org.scalatest.FunSuite

class StateMonadSpec extends FunSuite{

  test("run(), runS(), runA()") {
      val a = State[Int, String] { x =>
        (x, s"value is: $x")
      }

      val(state1, result1) = a.run(10).value
      val state2 = a.runS(10).value
      val result3 = a.runA(10).value

      assert(10 === state1 && "value is: 10" === result1)
      assert(10 === state2)
      assert("value is: 10" === result3)
  }

  test("composing") {
    val step1 = State[Int, String] { num =>
      (num + 1, s"From step 1: ${num + 1}")
    }

    val step2 = State[Int, String] { num =>
      (num * 2, s"From step 2: ${num * 2}")
    }

    val both = for {
      a <- step1
      b <- step2
    } yield (a, b)

    val (state, result) = both.run(20).value

    assert(42 === state)
    assert(("From step 1: 21", "From step 2: 42") === result)
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
