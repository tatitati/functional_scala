package SeparateDataFromBehaviour.test.pet

import cats.data.State
import SeparateDataFromBehaviour.order.OrderId
import SeparateDataFromBehaviour.pet.Pet
import SeparateDataFromBehaviour.test.{Faker, SeedLong}

object BuilderPetOps {

  case class BuilderState(orderId: OrderId, age: Int, name: String, price: Int)

  // If I do this I can see that I pass States around, so I can abstract this pattern by using Monad State:
  // =========
  //
  //    val buildState1 = BuilderPetOps.any()
  //    val buildState2 = BuilderPetOps.withAge(32, buildState1)
  //    val pet = BuilderPetOps.build(buildState2)

  def any(): State[SeedLong, BuilderState] = State { (seedLong:SeedLong) =>
    val randomInt: State[SeedLong, Int] = Faker.positiveInt()

    val createPet: State[SeedLong, BuilderState] = for {
      age <- randomInt
      price <- randomInt
    } yield BuilderState(OrderId("any_id"), age, "any name", price)

    createPet.run(seedLong).value
  }

  def withAge(withAge: Int): State[BuilderState, Unit] = State { (builderPet: BuilderState) =>
    val builderWithAge = builderPet.copy(age = withAge)
    State.set[BuilderState](builderWithAge).run(builderPet).value
  }

  def withPrice(withPrice: Int): State[BuilderState, Unit] = State { (builderPet: BuilderState) =>
    val builderWithAge = builderPet.copy(price = withPrice)
    State.set[BuilderState](builderWithAge).run(builderPet).value
  }

  def withName(withName: String, builderPet: BuilderState): BuilderState = {
    builderPet.copy(name = withName)
  }

  def withOrderId(withOrderId: OrderId): State[BuilderState, Unit] = State { builderPet:BuilderState =>
    val builderWithOrderId = builderPet.copy(orderId = withOrderId)
    State.set[BuilderState](builderWithOrderId).run(builderPet).value
  }

  def build(): State[BuilderState, Pet] = State{ builderState =>
    val built = Pet(
      orderId = builderState.orderId,
      age = builderState.age,
      name = builderState.name,
      price = builderState.price
    )
    State.pure[BuilderState, Pet](built).run(builderState).value
  }
}

