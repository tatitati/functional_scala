package shop.domain

import org.scalatest.FunSuite
import shop.domain.pet.Pet

class BuilderPetOpsSpec extends FunSuite {

  test("I can build a pet") {
    val bPet = BuilderPetOps.any()
    val pet = BuilderPetOps.build(bPet)

    assert(pet.isInstanceOf[Pet])
  }
}
