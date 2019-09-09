package test.learning.cats.Monads

import cats.syntax.either._
import org.scalatest.FunSuite

class EitherSpec extends FunSuite {
  case class Name(value: String)

  test("I can create right") {
    val e1: Either[String, Int] = Right(5)
    val e11: Either[String, Int] = Either.right(5)
    val e111 = Name("Diego").asRight[Name]
    val e1111 = 4.asRight[Int]

    assert(Right(5) == e1)
    assert(Right(5) == e11)
    assert(Right(Name("Diego")) == e111)
    assert(Right(4) == e1111)
  }

  test("I can create left") {
    val e3: Either[String, Int] = Left("hello")
    val e33: Either[String, Int] = Either.left("hello")

    assert(Left("hello") == e3)
    assert(Left("hello") == e33)
  }

  test("map() and flatMap() operates with the nested item") {
    val e1: Either[String, Int] = Right(5)
    val e2 = e1.right.map(_ + 1)
    
    assert(Right(6) == e2)
  }

  test("Left is created from None option") {
    val b = Either.fromOption[String, Int](None, "Text in Left")

    assert(Left("Text in Left") == b)
  }

  test("Right is created from Some option") {
    val a = Either.fromOption[String, Int](Some(1), "")

    assert(Right(1) == a)
  }
}
