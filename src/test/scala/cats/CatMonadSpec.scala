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

  test("pure()") {
    val result1 = Monad[Option].pure(3)
    assert(result1 === Some(3))
  }

  test("flatMap()") {
    val result1 = Monad[Option]
      .flatMap(Some(3))(a => Some(a + 2))

    assert(result1 === Some(5))
  }
}
