package test.cats.Monads

import cats.{Eval, Id, Monad}
import cats.instances.list._
import cats.instances.option._
import org.scalatest.FunSuite

// actually I NEED ONLY THESE TWO IMPORTS IN GENERAL WITH CATS, NO MORE
//import cats._
//import cats.implicits._

class EvalMonadSpec
  extends FunSuite {

  //  trait Monad[F[_]] {
  //    def pure[A](value: A): F[A]
  //    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B
  //    def map[A, B](value: F[A])(func: A => B): F[B]
  //  }

  test("Eval monad") {
    val now = Eval.now(math.random.toInt + 1000)
    val later = Eval.later(math.random.toInt + 2000)
    val always = Eval.always(math.random.toInt + 2000)

    assert(
      now.isInstanceOf[Eval[Int]] &&
        later.isInstanceOf[Eval[Int]] &&
        always.isInstanceOf[Eval[Int]]
    )
  }
}
