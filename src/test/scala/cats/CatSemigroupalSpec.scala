package test.cats

import cats.Semigroupal
import cats.instances.option._
import org.scalatest.FunSuite

class CatSemigroupalSpec extends FunSuite {
  // Definition:
  //
  // trait Semigroupal[F[_]] {
  //  def product[A, B](fa: F[A], fb: F[B]): F[(A, B]
  // }
  test("semigroupal") {
    val result = Semigroupal[Option].product(Some(123), Some("abc"))
    assert(Some((123, "abc")) === result)
  }

}
