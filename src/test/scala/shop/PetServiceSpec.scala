package test.shop

import cats.data.EitherT
import cats.effect.IO
import org.scalatest.FunSuite
import shop.{Pet, PetDontExist, PetExist, PetRepository, PetService}

class PetServiceSpec extends FunSuite{

  val service = new PetService(new PetRepository())

  test("service can create a user") {
    val programRight: EitherT[IO, PetExist.type, PetDontExist.type] = service.create(Pet("toby" ,32))
    val resultRight = programRight.value.unsafeRunSync()
    assert(Right(PetDontExist) == resultRight)

    val programLeft: EitherT[IO, PetExist.type, PetDontExist.type] = service.create(Pet("Bolt" ,17))
    val resultLeft = programLeft.value.unsafeRunSync()
    assert(Left(PetExist) == resultLeft)
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