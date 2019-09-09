package test.shop

import org.scalatest.FunSuite
import shop.{Pet, PetRepository, PetService}

class PetServiceSpec extends FunSuite{

  val service = new PetService(new PetRepository())

  test("service can create a user") {
    val result = service.create(Pet("toby" ,32))
    println(result)
  }
//
//  test("service CANNOT create a user") {
//    val service = new PetService(new PetRepository())
//    val petToby = Pet(name = "toby")
//
//    val result1 = service.create(petToby)
//    val result2 = service.create(petToby)
//
//    val check = for {
//      petCreated <- result2
//    } yield {
//      println("This output is NOT visible")
//      petCreated
//    }
//
//    assert(check.isLeft)
//  }
}