package test.learning

import org.scalatest.FunSuite

class FoldLeftRightSpec extends FunSuite {
  test("foldleft") {
    val prices: Seq[String] = Seq("a", "b", "c")
    val result1 = prices.foldLeft("____")(_ + _)
    val result2 = prices.foldRight("____")(_ + _)

    assert("____abc" === result1)
    assert("abc____" === result2)
  }
}
