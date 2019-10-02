package doobie

import cats.effect.{Blocker, IO}
import doobie.util.ExecutionContexts

trait CustomDbConnection {
  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:doobie_db",
    "postgres",
    "1234",
    Blocker.liftExecutionContext(ExecutionContexts.synchronous)
  )

  // these are to get .quick()
  val y = xa.yolo
  import y._
}
