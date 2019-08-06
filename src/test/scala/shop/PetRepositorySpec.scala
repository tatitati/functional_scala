package test.shop

import org.scalatest.FunSuite
import shop.{Pet, PetRepository}

class PetRepositorySpec extends FunSuite {

  val petToby = Pet(name = "toby")
  val petBolt = Pet(name = "bolt")

  test("create()"){
      val repo = new PetRepository()

      val a = repo.create(petBolt)
      val b = repo.create(petToby)

      assert(a.isInstanceOf[Pet])
      assert(b.isInstanceOf[Pet])
  }

  test("findByName()") {
    val repo = new PetRepository()

    assert(0 === repo.cache.size)

    repo.create(petBolt)
    repo.create(petToby)

    assert(2 === repo.cache.size)
  }
}
