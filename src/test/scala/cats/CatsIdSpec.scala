package test.cats

import org.scalatest.FunSuite
import cats.Id

class CatsIdSpec extends FunSuite {

  // Definition:
  // Id[A] = A

  test("aa") {
      val casted = "Dave" : Id[String]

      assert(casted === "Dave")
      assert(casted.isInstanceOf[Id[String]])
  }
}
