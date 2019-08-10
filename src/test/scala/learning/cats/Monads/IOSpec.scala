package test.learning.cats.Monads

import cats.data.OptionT
import cats.effect.IO
import cats.implicits._
import org.scalatest.FunSuite

class IOSpec extends FunSuite {
  test("pure()") {
    case class User(name: String)

    val user1 = User("manolo")
    val IoUser = user1.pure[IO] // lift User to IO

    assert(IoUser.isInstanceOf[IO[User]])
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
