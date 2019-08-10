package test.learning.cats.MonadTransformers

import cats.data.OptionT
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

  test("Monad transformers are simply nesting flatMaps") {
    val a = OptionT(List(Option(32)))

    for{
      aVal <- a
    } yield {
      assert(32 === aVal )
    }
  }
}
