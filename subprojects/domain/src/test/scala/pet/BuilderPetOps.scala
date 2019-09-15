package domain.test.pet

import domain.order.OrderId
import domain.pet.Pet
import domain.test.Faker

object BuilderPetOps {

  def any(): BuilderPet = { // TODO: THIS IS NOT REFERENCIAL TRANSPARENT
    BuilderPet(
      orderId = OrderId("asdf"),
      age = 23,
      name = "asdf",
      price = 2323
    )
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

