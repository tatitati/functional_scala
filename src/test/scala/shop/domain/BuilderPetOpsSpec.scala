package shop.domain

import org.scalatest.FunSuite
import shop.domain.pet.Pet

class BuilderPetOpsSpec extends FunSuite {

  test("I can build a pet") {
    val bPet = BuilderPetOps.any()
    val pet = BuilderPetOps.build(bPet)

    assert(pet.isInstanceOf[Pet])
  }

  test("I update things in the builder") {
    val bPet1 = BuilderPetOps.any()
    val bPet2 = BuilderPetOps.withAge(32, bPet1)
    val pet = BuilderPetOps.build(bPet2)

    assert(pet.age === 32)
  }
}
