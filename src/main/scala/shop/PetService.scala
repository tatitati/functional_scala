package shop

import cats.data._
import cats.effect._
import cats.implicits._

class PetService(petRepository: PetRepository) {

//  def create(pet: Pet): EitherT[IO, String, Unit] = {
//    val exists: IO[Boolean] = petRepository.exist(pet.name)
//    val create: IO[Unit] = petRepository.create(pet)
//    val errMsg: String = "it already exists"
//
//    val run: IO[Either[String,Unit]] = exists.flatMap {
//      case true => Either.left(errMsg).pure[IO]
//      case false => create.map(Either.right(_))
//    }
//
//    EitherT(run)
//  }

  def create(pet: Pet): EitherT[IO, PetExist.type, PetDontExist.type ] = {
    val exists: IO[Boolean] = petRepository.exist(pet.name)
    val create: IO[Unit] = petRepository.create(pet)
    val errMsg: String = "it already exists"


    val run: IO[Either[PetExist.type, PetDontExist.type]] = exists.flatMap {
      // I can do also: IO{PetExist.asLeft[PetDontExist.type]}
      // observe that because we are flattening IO[<something>] we have to convert the previous standalone Either
      // into an IO[Either], everything inside the flatMap must return IO in order to flatten it
      case true => PetExist.asLeft[PetDontExist.type].pure[IO]

      // override the IO[Unit] for IO[Either]
      case false => create.map(_ => PetDontExist.asRight[PetExist.type])
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