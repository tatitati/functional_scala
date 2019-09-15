import order.OrderId

case class BuilderPet(
                       val orderId: OrderId,
                       val age: Int,
                       val name: String,
                       val price: Int
)

