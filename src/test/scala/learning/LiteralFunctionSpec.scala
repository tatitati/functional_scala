package learning

import org.scalatest.FunSuite

class LiteralFunctionSpec extends FunSuite {

  test("with infered return type") {
    val sum = (x: Int, y: Int) => x + y
    assert(sum(2, 3) == 5)
  }

  test("with explicit return type") {
    val sum1: (Int , Int) => Int = (x, y) => { x + y }
    assert(sum1(2, 3) == 5)
  }


  test("passing functions to functions") {
    def mathOperation(a: Int, b: Int, f: (Int, Int) => Int) = {
      f(a, b)
    }

    def sum(a: Int, b: Int): Int = {
      a + b
    }

    def multiply(a: Int, b: Int): Int = {
      a * b
    }

    assert(5 === mathOperation(2, 3, sum))
    assert(6 === mathOperation(2, 3, multiply))
    assert(6 === mathOperation(2, 3, (x: Int, y: Int) => x*y))
  }
}
