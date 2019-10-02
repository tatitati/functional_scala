package learning.test.doobie

import org.scalatest.FunSuite
import doobie._
import cats._
import cats.effect._
import cats.implicits._
import doobie.implicits._
import doobie.util.ExecutionContexts

class SelectingDataSpec extends FunSuite with CustomDbConnection {
  test("Can request to db") {
    val result: Int = sql"select 42"
      .query[Int]       // Query0[Int]
      .unique           // ConnectionIO[Int]
      .transact(xa)     // IO[Int]
      .unsafeRunSync()  // Int

    assert(42 == result)
  }

  test("Can fetch ALL the rows"){
    val result1: List[String] = sql"select name from country"
      .query[String]    // Query0[String]
      .to[List]         // ConnectionIO[List[String]]
      .transact(xa)     // IO[List[String]]
      .unsafeRunSync    // List[String]


    assert(List("spain", "italy", "France") == result1) // we read by default all the rows

    val result2 = result1.take(2)
    assert(List("spain", "italy") == result2)
  }

  test("Can avoid fetching all the rows with streams") {
    val result: List[String] = sql"select name from country"
      .query[String]    // Query0[String]
      .stream           // Stream[ConnectionIO, String]
      .take(2)          // Stream[ConnectionIO, String]
      .compile.toList   // ConnectionIO[List[String]]
      .transact(xa)     // IO[List[String]]
      .unsafeRunSync    // List[String]

    assert(List("spain", "italy") == result)
  }

  test("multi column select") {
    val y = xa.yolo // a stable reference is required
    import y._

    val result = sql"select code, name, population, gnp from country"
      .query[(String, String, Int, Option[Double])]
      .stream
      .take(2)
      .compile.toList
      .quick
      .unsafeRunSync

    assert(
      List(("SP" ,"spain",500,Some(8.0)), ("IT" ,"italy",600,Some(3.0)))
      == result
    )
  }
}
