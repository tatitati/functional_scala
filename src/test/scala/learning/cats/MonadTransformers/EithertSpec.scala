package test.learning.cats.MonadTransformers

import cats.data.EitherT
import cats.effect.IO
import cats.instances.list._
import cats.syntax.all._
import org.scalatest.FunSuite

class EithertSpec extends FunSuite{
  test("EitherT.fromOptionF") {
    val myOptionList: List[Option[Int]] = List(
      None,
      Some(2),
      Some(3),
      None,
      Some(5)

    )
    val result = EitherT.fromOptionF(myOptionList, "option not defined")

    //    List
    //        Left
    //          String
    //        Right
    //          Int
    //        Right
    //          Int
    //        Left
    //          String
    //        ...

    assert(
      EitherT(List(
        Left("option not defined"),
        Right(2),
        Right(3),
        Left("option not defined"),
        Right(5))
      ) == result
    )
  }

  test("EitherT.liftF"){
    val value = Option(10).pure[IO] // => IO(Some(10))

    val result = EitherT.liftF(value)
    assert(true === result.isRight)


  }
}
