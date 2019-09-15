package domain.test.order

import order.{DEBITCARD, Order}
import org.scalatest.FunSuite
import com.github.nscala_time.time.Imports._


class OrderTest extends FunSuite {

  test("I can create an order"){
    val order = Order(
      date = DateTime.now,
      medium = DEBITCARD,
      country = "France"
    )
  }
}
