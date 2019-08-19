package test.learning.cats

import org.scalatest.FunSuite
import cats.{Semigroup, Semigroupal}
import cats.implicits._

class CatSemigroupSpec extends FunSuite {

  // Trait Semigroup[A] {
  //    def combine(x: A, y: A): A
  // }

  test("semigroup") {
    val result = Semigroup[String].combine("hi", "there")

    assert("hithere" == result)
  }

  test("semigroups are not semigroupal:") {
    val result1 = Semigroup[String].combine("hi", "there")
    val result2 = Semigroupal[Option].product(Some("hi"), Some("there"))

    assert("hithere" == result1)
    assert(Some(("hi", "there")) === result2)
  }
}
