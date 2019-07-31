package test.cats.Monads

import cats.data.State
import org.scalatest.FunSuite

class StateMonadSpec extends FunSuite{

  test("state monad") {
      val a = State[Int, String] {
        x => (x, s"value is: $x")
      }

      val(state1, result1) = a.run(10).value

      assert(10 === state1)
      assert("value is: 10" === result1)

      val state2 = a.runS(10).value
      assert(10 === state2)

      val result3 = a.runA(10).value
      assert("value is: 10" === result3)
  }

  test("composing with state monad") {
    val step1 = State[Int, String] {
      num =>
        val ans = num + 1
        (ans, s"From step 1: $ans")
    }

    val step2 = State[Int, String] {
      num =>
        val ans = num * 2
        (ans, s"From step 2: $ans")
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

    val beginningState = GolfState(0)
    val result: (GolfState, Int) = stateWithNewDistance.run(beginningState).value

    assert(
      (GolfState(35), 35) === result
    )
  }


}
