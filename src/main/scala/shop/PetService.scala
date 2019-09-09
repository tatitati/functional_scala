package shop

import cats.data._
import cats.effect._
import cats.implicits._

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): EitherT[IO, String, Unit] = {
    val exists: IO[Boolean] = petRepository.exist(pet.name)
    val create: IO[Unit] = petRepository.create(pet)
    val errMsg = "it already exists"

    val run: IO[Either[String,Unit]] = exists.flatMap {
      case true => errMsg.asLeft[Unit].pure[IO]
      case false => create.map(_.asRight[String])
    }
    EitherT(run)
  }

//      EitherT.fromEither{
//        if(petRepository.exist(pet.name)) {
//          Left(IO{"it already exist"})
//        } else {
//          Right(petRepository.create(pet))
//        }
//      }


//    EitherT.fromEither{
//      if(petRepository.exist(pet.name)) { // error: is IO[Boolean], no boolean
//        Left(IO{"it already exist"})
//      } else {
//        Right(petRepository.create(pet))
//      }
//    }

}