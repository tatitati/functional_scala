package domain.test.pet

import domain.pet.Pet
import org.scalatest.FunSuite

class BuilderPetOpsSpec extends FunSuite {

  test("Can build a pet") {
    val bPet = BuilderPetOps.any()
    val pet = BuilderPetOps.build(bPet)

    assert(pet.isInstanceOf[Pet])
  }

  test("Can update things in the builder") {
    val bPet1 = BuilderPetOps.any()
    val bPet2 = BuilderPetOps.withAge(32, bPet1)

    val pet = BuilderPetOps.build(bPet2)

    assert(pet.age === 32)
  }
}
