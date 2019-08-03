package test.cats

import org.scalatest.FunSuite
import cats.Semigroup
import cats.implicits._

class CatSemigroupSpec extends FunSuite {

  test("semigroup") {
    val result = Semigroup[String].combine("hi", "there")

    assert("hithere" == result)
  }
}
