package test.learning.cats.MonadTransformers

import cats.data.{EitherT, OptionT}
import cats.instances.list._
import cats.syntax.applicative._
import org.scalatest.FunSuite

class OptionTSpec extends FunSuite {

  type ListOption[A] = OptionT[List, A]

  test("can create OptionT in two ways") {
    case class User(name: String)

    val a = 32.pure[ListOption]
    val b = OptionT(List(Option(32)))

    assert(a == b)
  }

  test("Monad transformers are simply nesting flatMaps, so the access without needing nested loops to the value") {
    val a = OptionT(List(Option(32)))
    //    we have:
    //        OptionT
    //          List
    //            Option

    for{
      aVal <- a
    } yield {
      assert(32 === aVal , "Monad transformer cross two layers of monads in once (List and Option in here)")
    }
  }

  test("whatever") {
    import cats.instances.either._

    //  we want:
    //        OptionT
    //          Either
    //            Option

    type ErrorOr[A] = Either[String, A]
    type ErrorOrOption[A] = OptionT[ErrorOr, A]

    val a = 10.pure[ErrorOrOption] // OptionT(Right(Some(10))) === a

    // assert(OptionT(Right(Some(10))) == true)  // find out why is failing
  }

}
