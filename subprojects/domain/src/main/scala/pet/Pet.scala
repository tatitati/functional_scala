package SeparateDataFromBehaviour.pet

import SeparateDataFromBehaviour.order.OrderId

case class Pet(
                orderId: OrderId,
                name: String,
                age: Int,
                price: Int
)
