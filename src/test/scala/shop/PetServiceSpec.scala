package test.shop

import cats.data.{EitherT, OptionT}
import cats.effect.IO
import org.scalatest.FunSuite
import shop.{Pet, PetDontExist, PetExist, PetRepository, PetService}

class PetServiceSpec extends FunSuite{

  val service = new PetService(new PetRepository())

  test("service.create()") {
    val programRight: EitherT[IO, PetExist.type, PetDontExist.type] = service.create(Pet("toby" ,32))
    val resultRight = programRight.value.unsafeRunSync()
    assert(Right(PetDontExist) == resultRight)

    val programLeft: EitherT[IO, PetExist.type, PetDontExist.type] = service.create(Pet("Bolt" ,17))
    val resultLeft = programLeft.value.unsafeRunSync()
    assert(Left(PetExist) == resultLeft)
  }

  test("service.find()") {
    val result1:OptionT[IO, Pet] = service.find(Pet("nonexisting", 23))
    assert(None === result1.value.unsafeRunSync())

    val result2:OptionT[IO, Pet] = service.find(Pet("Bolt", 23))
    assert(Some(Pet("Bolt",17)) === result2.value.unsafeRunSync())
  }

  test("service.update()") {
    val update1:EitherT[IO, PetDontExist.type, Unit] = service.update(666, Pet("Bolt", 32))
    assert(Right(()) == update1.value.unsafeRunSync())

    val update2:EitherT[IO, PetDontExist.type, Unit] = service.update(666, Pet("non_existing", 32))
    assert(Left(PetDontExist) == update2.value.unsafeRunSync())
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