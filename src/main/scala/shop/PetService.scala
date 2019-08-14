package shop

import cats.data.EitherT
import cats.effect.IO
import cats.syntax.either._

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): Either[String, Pet] = {
    for {
      _ <- petRepository.doesNotExist(pet) // return: Either[String, Unit]
      action <- Right{IO{println("yeaaaaaaaaaaa")}}
      saved <- petRepository.create(pet)   // return: Either[String, Pet]
    } yield {
      action.unsafeRunSync() // I cannot mix monads, however I can extract and do shit in here
      saved
    }
  }
}
