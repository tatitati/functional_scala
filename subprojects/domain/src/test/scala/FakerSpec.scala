package SeparateDataFromBehaviour.test

import cats.data.State
import SeparateDataFromBehaviour.order.OrderId
import SeparateDataFromBehaviour.test.pet.BuilderPetOps.BuilderState
import org.scalatest.FunSuite

class FakerSpec extends FunSuite {

  test("Explain how to use state in builder"){
    val result = Faker.positiveInt()

    val state = result.run(SeedLong(10)).value

    assert((SeedLong(-8702919015481313007L),1254031283) == state)
  }

  test("Understand a bit better State") {
    val anInt: State[SeedLong, Int] = Faker.positiveInt()

    val createPet: State[SeedLong, BuilderState] = for {
      age <- anInt
      price <- anInt
    } yield BuilderState(OrderId("any_id"), age, "any name", price)

    assert(
      (
        SeedLong(3854786773771191933L),
        BuilderState(OrderId("any_id"),542183646,"any name",584218809)
      ) === createPet.run(SeedLong(2323)).value)
  }

  test("State is passed even if we dont care result values") {
    val anInt: State[SeedLong, Int] = Faker.positiveInt()

    val createPet: State[SeedLong, Unit] = for {
      _ <- anInt
      _ <- anInt
    } yield Unit

    assert(
      (
        SeedLong(3854786773771191933L),
        ()
      ) === createPet.run(SeedLong(2323)).value)
  }
}
