package test.shop

import cats.effect.IO
import org.scalatest.FunSuite
import shop.{Pet, PetRepository}

class PetRepositorySpec extends FunSuite {

  val repo = new PetRepository()

  test("create()"){
    val pet = Pet("colmillo_blanco", 8)

    for{
      result <- repo.create(pet)
    } yield {
      assert(result == pet)
    }
  }

  test("findByName()") {
    for {
      result1 <- repo.findByName("bolt")
      result2 <- repo.findByName("something weird")
    } yield {
      assert(result1 == Some(Pet("Bolt", 17)))
      assert(result2 == None)
    }
  }

  test("list") {
    for {
      result <- repo.list()
    } yield {
      assert(
        result == List(
          Pet("Bolt", 17),
          Pet("Lassie", 10)
        )
      )
    }
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
