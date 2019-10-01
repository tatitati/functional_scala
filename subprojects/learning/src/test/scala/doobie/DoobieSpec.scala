package learning.test.doobie

import org.scalatest.FunSuite
import doobie._
import cats._
import cats.effect._
import cats.implicits._
import doobie.implicits._
import doobie.util.ExecutionContexts

class DoobieSpec extends FunSuite {
  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:doobie_db",
    "postgres",
    "1234",
    Blocker.liftExecutionContext(ExecutionContexts.synchronous)
  )

  test("ConnectionIO is converted into IO"){
    val program1 = 42.pure[ConnectionIO]
    val io: IO[Int] = program1.transact(xa)

    assert(42 == io.unsafeRunSync)
  }

  test("Request to db simple operation") {
    val program2: ConnectionIO[Int] = sql"select 42".query[Int].unique
    val io2: IO[Int] = program2.transact(xa)

    assert(42 == io2.unsafeRunSync)
  }
}
