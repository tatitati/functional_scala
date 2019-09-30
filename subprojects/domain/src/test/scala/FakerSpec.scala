package SeparateDataFromBehaviour.test

import cats.data.State
import domain.order.OrderId
import domain.test.pet.BuilderPetOps.BuilderState
import org.scalatest.FunSuite

class FakerSpec extends FunSuite {

  test("Understand a bit better State") {
    val anInt: State[Seed, Int] = Faker.positiveInt()

    val createPet: State[Seed, BuilderState] = for {
      age <- anInt
      price <- anInt
    } yield BuilderState(OrderId("any_id"), age, "any name", price)

    assert(
      (
        Seed(3854786773771191933L),
        BuilderState(OrderId("any_id"),542183646,"any name",584218809)
      ) === createPet.run(Seed(2323)).value)
  }

  test("State is passed even if we dont care result values") {
    val anInt: State[Seed, Int] = Faker.positiveInt()

    val createPet: State[Seed, Unit] = for {
      _ <- anInt
      _ <- anInt
    } yield Unit

    assert(
      (
        Seed(3854786773771191933L),
        ()
      ) === createPet.run(Seed(2323)).value)
  }

  test("Explain how to use state in builder"){
    val result = Faker.positiveInt()

    val state = result.run(Seed(10)).value

    assert((Seed(-8702919015481313007L),1254031283) == state)
  }
}
