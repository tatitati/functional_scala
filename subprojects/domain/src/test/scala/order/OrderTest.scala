package domain.test.order

import org.scalatest.FunSuite
import com.github.nscala_time.time.Imports._
import domain.order.{DEBITCARD, Order}


class OrderTest extends FunSuite {

  test("I can create an order"){
    val order = Order(
      date = DateTime.now,
      medium = DEBITCARD,
      country = "France"
    )
  }
}
