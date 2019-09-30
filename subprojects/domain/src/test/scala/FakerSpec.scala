package SeparateDataFromBehaviour.test

import cats.data.State
import domain.order.OrderId
import domain.test.pet.BuilderPetOps.BuilderState
import org.scalatest.FunSuite

class FakerSpec extends FunSuite {

  test("Can generate random Longs") {
    val tuple: State[Seed, (Long, Long)] = for{
      long1 <- Faker.nextLong()
      long2 <- Faker.nextLong()
    } yield (long1, long2)

    val finalState = tuple.run(Seed(2323)).value
    assert(finalState == (Seed(3854786773771191933L),(2323,-8957604078071282010L)), "long1 and long2 must be differents")
  }

  test("Understand a bit better State") {
    val nextIntPositive: State[Seed, Int] = Faker.nextIntPositive()

    val createPet: State[Seed, BuilderState] = for {
      age <- nextIntPositive
      price <- nextIntPositive
    } yield BuilderState(OrderId("any_id"), age, "any name", price)

    assert(
      (Seed(3854786773771191933L), BuilderState(OrderId("any_id"),542183646,"any name",584218809))
        === createPet.run(Seed(2323)).value
    )
  }

  test("State is passed even if we dont care result values") {
    val nextIntPositive: State[Seed, Int] = Faker.nextIntPositive()

    val createPet: State[Seed, Unit] = for {
      _ <- nextIntPositive
      _ <- nextIntPositive
    } yield Unit

    assert(
      (Seed(3854786773771191933L), ())
        === createPet.run(Seed(2323)).value
    )
  }
}
