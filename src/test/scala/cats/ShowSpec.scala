package cats

import org.scalatest.FunSuite
import cats.instances.string._
import cats.instances.int._

class ShowSpec extends FunSuite {
  test("show") {
    val showInt: Show[Int] = Show.apply[Int]
    val showString: Show[String] = Show.apply[String]

    assert("123" === showInt.show(123))
    assert("whatever" === showString.show("whatever"))
  }
}
