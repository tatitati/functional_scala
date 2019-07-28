package learning

import org.scalatest.FunSuite

class GenericSpec extends FunSuite {

    test("generic with _") {
      def func1(a: List[_]): Int = {
        a.size
      }

      assert(3 === func1(List("a", "b", "c")))
    }
}
