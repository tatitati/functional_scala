package SeparateDataFromBehaviour.test.order

import org.scalatest.FunSuite
import com.github.nscala_time.time.Imports._
import SeparateDataFromBehaviour.order.{DEBITCARD, Order}
import cats.effect.IO

class OrderTest extends FunSuite {

  test("I can create an order"){
    // WRONG:
    //
    //    val order = Order(
    //      date = DateTime.now,
    //      medium = DEBITCARD,
    //      country = "France"
    //    )
    
    val build: IO[Order] = for {
      date <- IO{ DateTime.now }
      medium <- IO(DEBITCARD)
      country <- IO("france")
    } yield Order(date, medium, country)

    val anOrder = build.unsafeRunSync()
    assert(anOrder.isInstanceOf[Order])
  }
}
