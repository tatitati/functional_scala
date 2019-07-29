package test.cats

import org.scalatest.FunSuite
import cats.Monad
import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.instances.option._
import cats.instances.list._
import scala.language.higherKinds
import cats.Id

class CatsIdSpec extends FunSuite {

  // Definition:
  // Id[A] = A

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x*x + y*y

  test("basic") {
    assert(Some(25) === sumSquare(Option(3), Option(4)))
    assert(List(25) === sumSquare(List(3), List(4)))

    val resultWithId = sumSquare(3 : Id[Int], 4 : Id[Int])
    assert(25 === resultWithId)
    assert(resultWithId.isInstanceOf[Id[Int]])
  }
}
