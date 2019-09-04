package shop

import cats._
import cats.Monad
import cats.effect.IO
import cats.implicits._
import cats.syntax.either._

import scala.util.Random

class PetRepository {
  var cache: Map[String, Pet] = Map(
    "Bolt" -> Pet("Bolt", 17),
    "Lassie" -> Pet("Lassie", 10)
  )


  //  def create(pet: Pet): Either[String, Pet] = { // it can returns List[Pet], Option[Pet], ....
  //    val randomId = random.nextLong()
  //    val petToSave = pet.copy(id = Some(randomId))
  //
  //    cache += (randomId -> petToSave)
  //
  //    Right(petToSave)
  //  }

  def findByName(name: String): IO[Option[Pet]] = {
    val result = cache.contains(name) match {
      case true => Some(cache(name))
      case false => None
    }

    result.pure[IO]
  }



//  def doesNotExist(pet: Pet): Either[String, Unit] = {
//    val found = this.findByName(pet.name)
//    println(found)
//    if (found.size === 0) {
//      Right()
//    } else {
//      Left("the animal exist")
//    }
//  }
}
