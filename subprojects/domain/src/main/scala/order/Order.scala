package order

import com.github.nscala_time.time.Imports._

case class Order(
                date: DateTime,
                medium: OrderMedium,
                country: String
)
