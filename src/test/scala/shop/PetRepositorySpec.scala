package test.shop

import org.scalatest.FunSuite
import shop.{Pet, PetRepository}

class PetRepositorySpec extends FunSuite {

  val petToby = Pet(name = "toby", bio="born in germany")
  val petBolt = Pet(name = "bolt", bio="worked in army")

  test("create()"){
      val repo = new PetRepository()

      assert(0 === repo.cache.size)

      val a = repo.create(petBolt)
      val b = repo.create(petToby)

      assert(2 === repo.cache.size)
      assert(a.isInstanceOf[Pet])
      assert(b.isInstanceOf[Pet])
  }

  test("findByName()") {
    val repo = new PetRepository()

    val savedBolt = repo.create(petBolt)
    val result1 = repo.findByName("bolt")
    val result2 = repo.findByName("not-found")

    assert(List(savedBolt) === result1)
    assert(List() === result2)
  }
}
