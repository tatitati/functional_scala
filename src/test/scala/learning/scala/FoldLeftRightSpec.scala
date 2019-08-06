package test.learning.scala

import org.scalatest.FunSuite

class FoldLeftRightSpec extends FunSuite {

  test("fold()") {
    val numbers = List(1, 3, 5)
    def add(a: Int, b: Int): Int = a + b

    val result1 = numbers.fold(0)(_ + _)
    val result2 = numbers.fold(0)(add)

    assert(9 === result1)
    assert(9 === result2)
  }

  test("foldleft") {
    val prices: Seq[String] = Seq("a", "b", "c")
    val result1 = prices.foldLeft("____")(_ + _ )
    val result2 = prices.foldRight("____")(_ + _)
    val result3 = prices.fold("____")(_ + _)

    assert("____abc" === result1)
    assert("abc____" === result2)
    assert("____abc" === result3)
  }
}
