package domain.test.pet

import domain.order.OrderId
import domain.pet.Pet
import domain.test.{Faker, Seed}
import org.scalatest.FunSuite

class BuilderPetSpec extends FunSuite {
  test("I can create pets") {
    val createPet = for{
      age <- Faker.int()
      price <- Faker.int()
    } yield Pet(orderId = OrderId("asdf"), name = "asdfads", age = age, price = price)

    val (nextSeed, pet) = createPet.run(Seed(100)).value

    assert(
      Pet(OrderId("asdf"),"asdfads",344588959,-1883182926) == pet
    )
  }
}
