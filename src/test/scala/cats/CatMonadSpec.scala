package test.cats

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._   // for Monad
import org.scalatest.FunSuite

class CatMonadSpec extends FunSuite {

//  trait Monad[F[_]] {
//    def pure[A](value: A): F[A]
//
//    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
//
//    def map[A, B](value: F[A])(func: A => B): F[B]
//  }


  // WITH OPTION

  test("Option: pure()") {
    val result1 = Monad[Option].pure(3)
    assert(result1 === Some(3))
  }

  test("Option: flatMap()") {
    val result1 = Monad[Option]
      .flatMap(Some(3))(a => Some(a + 2))

    assert(result1 === Some(5))
  }

  test("Option: map()") {
    var result1 = Monad[Option]
      .map(Some(3))(a => 2*a)

    assert(result1 === Some(6))
  }

  // with LIST

  test("LIST: pure()") {
    val result1 = Monad[List].pure(3)
    assert(result1 === List(3))
  }

  test("LIST: flatMap()") {
    val result1 = Monad[List]
      .flatMap(List(3))(a => List(a, a*10))

    assert(result1 === List(3, 30))
  }

  test("LIST: map()") {
    val result1 = Monad[List]
      .map(List(3))(a => a + 10)

    assert(result1 === List(13))
  }
}
