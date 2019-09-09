package test.shop

import cats.effect.IO
import org.scalatest.FunSuite
import shop.{Pet, PetRepository}

class PetRepositorySpec extends FunSuite {

  val repo = new PetRepository()

  test("repo.create()"){
    val pet = Pet("colmillo_blanco", 8)

    for{
      result <- repo.create(pet)
    } yield {
      assert(result == Unit)
    }
  }

  test("repo.findByName()") {
    // repo.findByName() :: IO[Option[Pet]]
    for {
      result1 <- repo.findByName("bolt")
      result2 <- repo.findByName("No existing")
    } yield {
      assert(result1 == Some(Pet("Bolt", 17)))
      assert(result2 == None)
    }
  }

  test("repo.list()") {
    // repo.list() :: IO[List[Pet]]
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

  test("repo.exist()") {
    // epo.exist() :: IO[Boolean]
    for {
      result <- repo.exist("Bolt")
    } yield {
      assert(true === result)
    }
  }

//  test("asdfads") {
//    repo.exist("boolt").flatMap {
//        case true => {
//           println("trueee")
//          true
//        }
//        case false => {
//          println("falseee")
//          false
//        }
//    }
//  }
}
