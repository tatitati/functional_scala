package domain.test.pet

import cats.data.State
import domain.order.OrderId
import domain.pet.Pet
import domain.test.SeedLong
import domain.test.pet.BuilderPetOps.BuilderState
import org.scalatest.FunSuite

class BuilderPetSpec extends FunSuite {
  test("I can use Monad State to use pet builder") {
      val (_, any) = BuilderPetOps.any().run(SeedLong(100)).value

      val createPet: State[BuilderState, Pet] = for{
        _ <- BuilderPetOps.withAge(34)
        _ <- BuilderPetOps.withPrice(110)
        built <- BuilderPetOps.build()
      } yield built

      assert(
        (
          BuilderState(OrderId("any_id"),34,"any name",110),
          Pet(OrderId("any_id"),"any name",34,110)
        ) == createPet.run(any).value
      )
  }

//  test("Can build a pet") {
//    val bPet = BuilderPetOps.any()
//    val pet = BuilderPetOps.build(bPet)
//
//    assert(pet.isInstanceOf[Pet])
//  }
//
//  test("Can update things in the builder") {
//    val bPet1 = BuilderPetOps.any()
//    val bPet2 = BuilderPetOps.withAge(32, bPet1)
//
//    val pet = BuilderPetOps.build(bPet2)
//
//    assert(pet.age === 32)
//  }
}
