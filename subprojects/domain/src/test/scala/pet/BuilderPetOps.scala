package domain.test.pet

import domain.order.OrderId
import domain.pet.Pet
import domain.test.{Faker, Seed}

object BuilderPetOps {

  def any(): BuilderPet = {
    val createPet = for{
      age <- Faker.positiveInt()
      price <- Faker.positiveInt()
    } yield BuilderPet(
      orderId = OrderId("asdf"),
      age = age,
      name = "asdf",
      price = price
    )

    val (_, petBuilder) = createPet.run(Seed(100)).value

    petBuilder
  }

  def withAge(withAge: Int, builderPet: BuilderPet): BuilderPet = {
    builderPet.copy(age = withAge)
  }

  def withName(withName: String, builderPet: BuilderPet): BuilderPet = {
    builderPet.copy(name = withName)
  }

  def withPrice(withPrice: Int, builderPet: BuilderPet): BuilderPet = {
    builderPet.copy(price = withPrice)
  }

  def withOrderId(withOrderId: OrderId, builderPet: BuilderPet): BuilderPet = {
    builderPet.copy(orderId = withOrderId)
  }

  def build(builderPet: BuilderPet): Pet = {
    Pet(
      orderId = builderPet.orderId,
      age = builderPet.age,
      name = builderPet.name,
      price = builderPet.price
    )
  }
}

