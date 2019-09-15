package shop.domain.pet

import shop.domain.order.OrderId

case class Pet(
                orderId: OrderId,
                name: String,
                age: Int,
                price: Int
)
