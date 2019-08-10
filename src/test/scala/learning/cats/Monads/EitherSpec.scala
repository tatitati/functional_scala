package test.learning.cats.Monads

import cats.syntax.either._
import org.scalatest.FunSuite

class EitherSpec extends FunSuite {

  test("Can create Either instances") {
      case class Name(value: String)

      val a = Name("Diego").asRight[Name]
      val b = 4.asRight[Int]

      assert(Right(Name("Diego")) == a)
      assert(Right(4) == b)
  }

  test("I can create Eithers from other variables") {
    val a = Either.fromOption[String, Int](Some(1), "Seems that we passwed None")
    val b = Either.fromOption[String, Int](None, "Seems that we passwed None")

    assert(Right(1) == a)
    assert(Left("Seems that we passwed None") == b)
  }

  test("Can transform eithers") {
    val a = "whatever".asLeft[Any].getOrElse(0)
    val b = 3.asRight[Any]
    val c = 3.asLeft[Any]
    val d = "hi".asRight[Any]

    assert(0 === a)
    assert(Right(3) == b)
    assert(Left(3) == c)
    assert(Right("hi") == d)
  }
}
