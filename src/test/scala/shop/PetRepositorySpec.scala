package test.shop

import org.scalatest.FunSuite
import shop.{Pet, PetRepository}

class PetRepositorySpec extends FunSuite {

  val petToby = Pet(name = "toby")
  val petBolt = Pet(name = "bolt")

  test("create()"){
      val repo = new PetRepository()

      assert(0 === repo.cache.size)

      val a = repo.create(petBolt)
      val b = repo.create(petToby)

      assert(2 === repo.cache.size)
      assert(a.isInstanceOf[Either[String, Pet]])
      assert(b.isInstanceOf[Either[String, Pet]])
  }

  test("findByName()") {
    val repo = new PetRepository()

    val savedBolt = repo.create(petBolt).right.get
    val result1 = repo.findByName("bolt")
    val result2 = repo.findByName("not-found")

    assert(List(savedBolt) === result1, "list should contain a found pet")
    assert(List() === result2, "list should be empty")
  }

//  test("doesNotExist()") {
//    val repo = new PetRepository()
//
//    val savedBolt = repo.create(petBolt)
//    val unsaved = Pet(name="lassye")
//
//    val result1 = repo.doesNotExist(savedBolt)
//    val result2 = repo.doesNotExist(unsaved)
////    val result2 = repo.findByName("not-found")
//
//    assert(Right() === result1)
//    assert(Left("the animal doesnt exist") === result2)
//  }
}
