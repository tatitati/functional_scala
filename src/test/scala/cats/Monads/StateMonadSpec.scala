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
}
