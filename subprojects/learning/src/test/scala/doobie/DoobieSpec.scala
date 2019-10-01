package learning.test.doobie

import org.scalatest.FunSuite
import doobie._
import cats._
import cats.effect._
import cats.implicits._
import doobie.implicits._
import doobie.util.ExecutionContexts

class DoobieSpec extends FunSuite {

  test("Connect doobie to db"){
    implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

    val xa = Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",
      "jdbc:postgresql:doobie_db",
      "postgres",
      "1234",
      Blocker.liftExecutionContext(ExecutionContexts.synchronous)
    )
    val program1 = 42.pure[ConnectionIO]
    val io = program1.transact(xa)

    assert(42 == io.unsafeRunSync)
  }
}
