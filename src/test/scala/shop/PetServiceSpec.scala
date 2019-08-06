package test.shop

import org.scalatest.FunSuite
import shop.{Pet, PetRepository, PetService}

class PetServiceSpec extends FunSuite{

  test("service") {
    val service = new PetService(new PetRepository())
    val petToby = Pet(name = "toby")

    val result = service.create(petToby)

    for {
      petCreated <- result
    } yield {
      assert(result.isRight)
      assert(petCreated.isInstanceOf[Pet])
    }
  }
}