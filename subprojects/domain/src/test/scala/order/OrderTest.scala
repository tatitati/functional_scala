package SeparateDataFromBehaviour.test.order

import org.scalatest.FunSuite
import com.github.nscala_time.time.Imports._
import SeparateDataFromBehaviour.order.{DEBITCARD, Order, OrderError}
import cats.effect.IO

class OrderTest extends FunSuite {

  test("Builder") {
    val buildOrder: IO[Order] = for {
      any <- BuildOrder()
      create <- IO{ any.withCountry("any_contry") }
      built <- IO{create.build()}
    } yield built

    val anOrder = buildOrder.unsafeRunSync()

    assert(anOrder.isInstanceOf[Order])

  }

  test("Can create an order in a functional way"){
    // WRONG OOP way:
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

  test("Country can be updated") {
    val build: IO[Order] = for {
      date <- IO{ DateTime.now }
      medium <- IO(DEBITCARD)
      country <- IO("france")
    } yield Order(date, medium, country)

    val anOrder = build.unsafeRunSync()
    val updatedOrder = anOrder.setCountry("any_country")

    assert(updatedOrder.isInstanceOf[Right[_, Order]])
  }

  test("Country can return LEFT") {
    val build: IO[Order] = for {
      date <- IO{ DateTime.now }
      medium <- IO(DEBITCARD)
      country <- IO("france")
    } yield Order(date, medium, country)

    val anOrder = build.unsafeRunSync()
    val updatedOrder = anOrder.setCountry("")

    assert(updatedOrder.isInstanceOf[Left[OrderError, _]])
  }
}
