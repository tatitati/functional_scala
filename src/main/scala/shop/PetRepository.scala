package shop

import cats.Monad
import cats.instances.option._

import scala.util.Random

class PetRepository {
  private var cache: Map[Long, Pet] = Map()
  private val random = new Random

  def create[F[_]](pet: Pet): F[Pet] = { // it can returns List[Pet], Option[Pet], ....
    val randomId = random.nextLong()
    val petToSave = pet.copy(id = Some(randomId))

    cache += (randomId -> petToSave)

    Monad[F].pure(petToSave)
  }
  
//
//  def findByNameAndCategory(name: String, category: String): F[Set[Pet]] =
//    cache.values
//      .filter(p => p.name == name && p.category == category)
//      .toSet
//      .pure[F]
//
//  def doesNotExist(pet: Pet): Either[F, PetAlreadyExistsError, Unit] = EitherT {
//    repository.findByNameAndCategory(pet.name, pet.category).map { matches =>
//      if (matches.forall(possibleMatch => possibleMatch.bio != pet.bio)) {
//        Right(())
//      } else {
//        Left(PetAlreadyExistsError(pet))
//      }
//    }
//  }
}
