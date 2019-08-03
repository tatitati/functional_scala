package test.cats.Monads

import cats.{Eval, Id, Monad}
import cats.instances.list._
import org.scalatest.FunSuite

// actually I NEED ONLY THESE TWO IMPORTS IN GENERAL WITH CATS, NO MORE
//import cats._
//import cats.implicits._

class ListMonadSpec extends FunSuite {

  //  trait Monad[F[_]] {
  //    def pure[A](value: A): F[A]
  //    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B
  //    def map[A, B](value: F[A])(func: A => B): F[B]
  //  }

  test("pure()") {
    val result1 = Monad[List].pure(3)
    assert(result1 === List(3))
  }

  test("flatMap()") {
    val result1 = Monad[List]
      .flatMap(List(3))(a => List(a, a*10))

    assert(result1 === List(3, 30))
  }

  test("map()") {
    val result1 = Monad[List]
      .map(List(3))(a => a + 10)

    assert(result1 === List(13))
  }

  test("use case") {
    import cats.syntax.flatMap._
    import cats.syntax.functor._


    // bound context here:
    // we are saying that for F[_] we have an implicit Monad[F[_]]
    def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] = {
      for {
        x <- a
        y <- b
      } yield x*x + y*y
    }

    val result2 = sumSquare(List(3), List(4))

    assert(List(25) === result2)
  }
}
