package test.learning.cats.MonadTransformers

import cats.data.{EitherT}
import cats.instances.list._
import org.scalatest.FunSuite

class EithertSpec extends FunSuite{
  test("basic") {
    val myOptionList: List[Option[Int]] = List(
      None,
      Some(2),
      Some(3),
      None,
      Some(5)

    )
    val result = EitherT.fromOptionF(myOptionList, "option not defined")

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
}
