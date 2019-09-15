package shop.domain

import java.util.UUID
import shop.domain.order.OrderId
import shop.domain.pet.Pet
import test.shop.Faker

case class BuilderPet(
                       val orderId: OrderId,
                       val age: Int,
                       val name: String,
                       val price: Int
)

