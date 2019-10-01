package learning.test.doobie

import org.scalatest.FunSuite
import doobie._
import cats._
import cats.effect._
import cats.implicits._
import doobie.implicits._
import doobie.util.ExecutionContexts

class DoobieSpec extends FunSuite {

  test("asdfasd"){
    implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

    // A transactor that gets connections from java.sql.DriverManager and executes blocking operations
    // on an our synchronous EC. See the chapter on connection handling for more info.
    val xa = Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",     // driver classname
      "jdbc:postgresql:doobie_db",     // connect URL (driver-specific)
      "postgres",                       // user
      "1234",                           // password
      Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
    )
    val program1 = 42.pure[ConnectionIO]
    val io = program1.transact(xa)
    println(io.unsafeRunSync)
  }
}
