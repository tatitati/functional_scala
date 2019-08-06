package shop

import cats._
import cats.Monad
import cats.implicits._

import scala.util.Random

class PetRepository {
  var cache: Map[Long, Pet] = Map()
  private val random = new Random

  def create[F[_]: Applicative](pet: Pet): Pet = { // it can returns List[Pet], Option[Pet], ....
    val randomId = random.nextLong()
    val petToSave = pet.copy(id = Some(randomId))

    cache += (randomId -> petToSave)

    //Monad[F].pure(petToSave)
    petToSave
  }

//
  def findByName(name: String): Set[Pet] =
    cache.values
      .filter(p => p.name == name)
      .toSet
//
//  def doesNotExist(pet: Pet): Either[String, Unit] = Either {
//    repository.findByNameAndCategory(pet.name, pet.category).map { matches =>
//      if (matches.forall(possibleMatch => possibleMatch.bio != pet.bio)) {
//        Right(())
//      } else {
//        Left(PetAlreadyExistsError(pet))
//      }
//    }
//  }
}
