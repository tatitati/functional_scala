package test.cats

import org.scalatest.FunSuite
import cats.Monoid
import cats.instances.string._

class CatsMonoidSpec extends FunSuite {
  test("monoid") {
    val result = Monoid[String].combine("hi", "there")

    assert("hithere" === result)
  }
}
