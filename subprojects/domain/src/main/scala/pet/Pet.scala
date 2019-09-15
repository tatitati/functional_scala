package domain.pet

import domain.order.OrderId

case class Pet(
                orderId: OrderId,
                name: String,
                age: Int,
                price: Int
)
