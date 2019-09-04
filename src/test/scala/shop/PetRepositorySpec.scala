package test.shop

import cats.effect.IO
import org.scalatest.FunSuite
import shop.{Pet, PetRepository}

class PetRepositorySpec extends FunSuite {

//  val petToby = Pet(name = "toby")
//  val petBolt = Pet(name = "bolt")

//  test("create()"){
//      val repo = new PetRepository()
//
//      assert(0 === repo.cache.size)
//
//      val a = repo.create(petBolt)
//      val b = repo.create(petToby)
//
//      assert(2 === repo.cache.size)
//      assert(a.isInstanceOf[Either[String, Pet]])
//      assert(b.isInstanceOf[Either[String, Pet]])
//  }

  test("findByName()") {
    val repo = new PetRepository()

    for {
      result1 <- repo.findByName("bolt")
      result2 <- repo.findByName("something weird")
    } yield {
      assert(result1 == Some(Pet("Bolt", 17)))
      assert(result2 == None)
    }
//    assert(List() === result2, "list should be empty")
  }

//  test("doesNotExist()") {
//    val repo = new PetRepository()
//
//    val savedPet = repo.create(petBolt)
//    val unsavedPet = Pet(name="lassye")
//
//    val result1 = repo.doesNotExist(savedPet.right.get)
//    val result2 = repo.doesNotExist(unsavedPet)
//
//    assert(Left("the animal exist") === result1)
//    assert(Right() === result2)
//  }
}
