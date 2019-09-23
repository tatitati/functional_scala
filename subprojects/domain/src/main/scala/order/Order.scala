package SeparateDataFromBehaviour.order

import com.github.nscala_time.time.Imports._

case class Order(
                date: DateTime,
                medium: OrderMedium,
                country: String
) {
  def setMedium(withmedium: OrderMedium): Order = {
    this.copy(medium = withmedium)
  }

  def setCountry(withCountry: String): Order = {
    this.copy(country = withCountry)
  }
}
