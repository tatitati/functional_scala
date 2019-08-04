package test.learning.cats

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
    val result1 = Semigroupal[Option].product(Some(123), Some("abc"))
    val result2 = Semigroupal[Option].product(Some(123), None)

    assert(Some((123, "abc")) === result1)
    assert(None === result2)
  }

  test("semigroupal and multiple contexts") {
    val result1 = Semigroupal.tuple3(Option(2), Option(22), Option(222))
    val result2 = Semigroupal.tuple3(Option(2), Option(22), None)
    
    assert(Some((2,22,222)) === result1)
    assert(None === result2)
  }
}
