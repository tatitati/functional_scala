package shop

import cats.data.EitherT
import cats.syntax.either._

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): Either[String, Pet] = {
    for {
      _ <- petRepository.doesNotExist(pet) // return: Either[String, Unit]
      saved <- petRepository.create(pet)   // return: Either[String, Pet]
    } yield saved
  }
}
