package SeparateDataFromBehaviour.test

import cats.data.State
import domain.order.OrderId
import domain.test.pet.BuilderPetOps.BuilderState
import org.scalatest.FunSuite

class FakerSpec extends FunSuite {

  test("Can generate random Longs") {
    val tuple: State[Seed, (Long, Long, Long)] = for{
      long1 <- Faker.nextLong()
      long2 <- Faker.nextLong()
      long3 <- Faker.nextLong()
    } yield (long1, long2, long3)

    val (state, value) = tuple.run(Seed(2323)).value
    assert(value == (2323,-8957604078071282010L,3854786773771191933L), "long1 and long2 must be differents")
  }

  test("Can generate random Booleans") {
    val tuple: State[Seed, (Boolean, Boolean, Boolean)] = for{
      bool1 <- Faker.nextBoolean()
      bool2 <- Faker.nextBoolean()
      bool3 <- Faker.nextBoolean()
    } yield (bool1, bool2, bool3)

    val (state, value) = tuple.run(Seed(2323)).value

    assert(value == (true,false,true))
  }

  test("can generate positive Ints") {
    val tuple: State[Seed, (Int, Int, Int)] = for{
      int1 <- Faker.nextIntPositive()
      int2 <- Faker.nextIntPositive()
      int3 <- Faker.nextIntPositive()
    } yield (int1, int2, int3)

    val (state, value) = tuple.run(Seed(2323)).value

    assert(value == (2323,403143002,1350465923))
  }

  test("can select random item in range") {
    val tuple: State[Seed, (Int, Int, Int)] = for{
      int1 <- Faker.nextInInterval(5)
      int2 <- Faker.nextInInterval(5)
      int3 <- Faker.nextInInterval(5)
    } yield (int1, int2, int3)

    val (state, value) = tuple.run(Seed(2323)).value

    assert(value == (1,2,5))
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
