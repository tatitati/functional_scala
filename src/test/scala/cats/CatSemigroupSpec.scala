package test.cats

import org.scalatest.FunSuite
import cats.Semigroup
import cats.implicits._

class CatSemigroupSpec extends FunSuite {
  // Definition:
  //
  // trait Semigroupal[F[_]] {
  //  def product[A, B](fa: F[A], fb: F[B]): F[(A, B]
  // }

  test("semigroup") {
    val result = Semigroup[String].combine("hi", "there")

    assert("hithere" == result)
  }
}
