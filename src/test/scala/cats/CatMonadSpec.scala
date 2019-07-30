package test.cats

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._   // for Monad
import cats.Id
import org.scalatest.FunSuite

// actually I NEED ONLY THESE TWO IMPORTS IN GENERAL WITH CATS, NO MORE
//import cats._
//import cats.implicits._

class CatMonadSpec extends FunSuite {

//  trait Monad[F[_]] {
//    def pure[A](value: A): F[A]
//    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B
//    def map[A, B](value: F[A])(func: A => B): F[B]
//  }

  // WITH OPTION

  test("Option: pure()") {
    val monadOption = Monad[Option]  //monad option is instance of cats.instances.OptionInstances$$anon$1@376c94a8
    val result1 = monadOption.pure(3)

    assert(result1 === Some(3))
  }

  test("Option: flatMap()") {
    val result1 = Monad[Option]
      .flatMap(Some(3))(a => Some(a + 2))

    assert(result1 === Some(5))
  }

  test("Option: flatMap() changing resulting type") {
    val result1 = Monad[Option]
      .flatMap(Some(3))(a => Some(a.toString ))

    assert(result1 === Some("3"), "Input can be Some[int], but after flapMap I have Some[String]")
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

  // with ID monad
  test("ID") {
    val result1 = "Dave" : Id[String]
    val result2 = 34 : Id[Int]

    assert(result1.isInstanceOf[String] && "Dave" === result1)
    assert(result2.isInstanceOf[Int]    && 34 === result2)

  }

  // use case
  test("use case") {
    import cats.syntax.functor._
    import cats.syntax.flatMap._


    // bound context here:
    // we are saying that for F[_] we have an implicit Monad[F[_]]
    def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] = {
      for {
        x <- a
        y <- b
      } yield x*x + y*y
    }

    val result1 = sumSquare(Option(3), Option(4))
    val result2 = sumSquare(List(3), List(4))
    val result3 = sumSquare(3 : Id[Int], 4 : Id[Int])

    assert(Some(25) === result1)
    assert(List(25) === result2)
    assert(25 === result3)
  }
}
