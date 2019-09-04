package shop

import cats.data.EitherT
import cats.effect.IO
import cats.implicits._

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): EitherT[IO, String, Unit] = {
//    for {
//      _ <- .exist(pet.name)
//      petSaved <- EitherT.liftF(petRepository.create(pet))
//    } yield petSaved

    EitherT{
      petRepository.exist(pet.name) flatMap {
        case true => IO.unit
        case false => petRepository.create(pet)
      }
    }
  }
}