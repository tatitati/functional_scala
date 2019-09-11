package test.shop

import cats.effect.IO
import org.scalatest.FunSuite
import shop.{Pet, PetRepository}

class PetRepositorySpec extends FunSuite {

  val repo = new PetRepository()

  test("repo.create()"){
    val create: IO[Unit] = repo.create(Pet("colmillo_blanco", 8))

    assert(() == create.unsafeRunSync())
  }

  test("repo.findByName()") {
    // repo.findByName() ::
    val find1:IO[Option[Pet]] = repo.findByName("Bolt")
    val find2:IO[Option[Pet]] = repo.findByName("No existing")

    assert(Some(Pet("Bolt", 17)) == find1.unsafeRunSync())
    assert(None == find2.unsafeRunSync())
  }

  test("repo.list()") {
    val list:IO[List[Pet]] = repo.list()
    for {
      result <- list
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
    val exist:IO[Boolean] = repo.exist("Bolt")
    for {
      result <- exist
    } yield {
      assert(true === result)
    }
  }

  test("repo.update()") {
    val result:IO[Unit] = repo.update(55, Pet("Bolt", 17))

    assert(() == result.unsafeRunSync)
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
