package domain.test.pet

import domain.order.OrderId

case class BuilderPet(
                       orderId: OrderId,
                       age: Int,
                       name: String,
                       price: Int
)

