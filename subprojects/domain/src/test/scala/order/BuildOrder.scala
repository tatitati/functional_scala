package SeparateDataFromBehaviour.test.order

import SeparateDataFromBehaviour.order.{DEBITCARD, Order, OrderMedium}
import cats.effect.IO
import com.github.nscala_time.time.Imports.DateTime

object BuildOrder{

  def apply(): IO[BuildOrder] = {
    for {
      date <- IO(DateTime.now)
      medium <- IO(DEBITCARD)
      country <- IO("any_country")
    } yield BuildOrder(date, medium, country)
  }
}

case class BuildOrder(
                date: DateTime,
                medium: OrderMedium,
                country: String
  ) {

  def withCountry(withCountry: String): BuildOrder = {
    this.copy(country = withCountry)
  }

  def withDate(withDate: DateTime): BuildOrder = {
    this.copy(date = withDate)
  }

  def build(): Order = {
    Order(
      date,
      medium,
      country
    )
  }



//  date: DateTime,
//  medium: OrderMedium,
//  country: String


}
