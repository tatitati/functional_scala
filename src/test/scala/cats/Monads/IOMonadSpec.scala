package test.cats.Monads

import cats.effect.IO
import org.scalatest.FunSuite

class IOMonadSpec extends FunSuite {
  
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
