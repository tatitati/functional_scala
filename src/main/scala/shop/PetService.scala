package shop

import cats.data.EitherT
import cats.effect.IO
import cats.syntax.either._

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): Either[String, Pet] = {
    for {
      _ <- petRepository.exist(pet.name)
      saved <- petRepository.create(pet)
    } yield saved
  }
}
