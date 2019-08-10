package test.learning.cats.Monads

import cats.effect.IO
import cats.implicits._
import org.scalatest.FunSuite

class IOMonadSpec extends FunSuite {
  test("pure()") {
    case class User(name: String)

    val a = User("manolo")
    val b = a.pure[IO] // lift User to IO

    assert(b.isInstanceOf[IO[User]])
  }

  test("use case") {
      val program: IO[Unit] = for {
        _ <- IO { println("Introduce your name") }
        name <- IO { scala.io.StdIn.readLine }
        nameUC = name.toUpperCase
        _ <- IO { println(s"Hi $nameUC !!")}
      } yield()

      program.unsafeRunSync()
  }
}
