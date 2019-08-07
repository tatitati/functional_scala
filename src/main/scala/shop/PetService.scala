package shop

import cats.data.EitherT
import cats.syntax.either._

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): Either[String, Pet] = {
    for {
      _ <- petRepository.doesNotExist(pet)
      saved <- petRepository.create(pet)
    } yield saved
  }
}
