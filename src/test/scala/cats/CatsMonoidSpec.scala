package test.cats

import org.scalatest.FunSuite
import cats.Monoid
import cats.instances.string._

class CatsMonoidSpec extends FunSuite {
  test("monoid") {
    val result1 = Monoid[String].combine("hi", "there")
    val result2 = Monoid[String].combine("hi", "there")

    assert("hithere" === result1)
    assert("" === Monoid[String].empty)
  }
}
