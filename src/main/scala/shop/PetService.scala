package shop

import cats.data._
import cats.effect._
import cats.implicits._

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): EitherT[IO, PetExist.type, PetDontExist.type ] = {
    val exists: IO[Boolean] = petRepository.exist(pet.name)
    val create: IO[Unit] = petRepository.create(pet)
    val errMsg: String = "it already exists"


    val run: IO[Either[PetExist.type, PetDontExist.type]] = exists.flatMap {
      case true => PetExist.asLeft[PetDontExist.type].pure[IO]
      case false => create.map(_ => PetDontExist.asRight[PetExist.type])
    }

    EitherT(run)
  }

  def list: IO[List[Pet]] = {
    petRepository.list()
  }

  def find(pet:Pet): OptionT[IO, Pet] = {
    OptionT(
      petRepository.findByName(pet.name)
    )
  }

  def update(newage: Int, pet: Pet): EitherT[IO, PetDontExist.type, Unit] = {
    val exist: IO[Boolean] = petRepository.exist(pet.name)
    val update: IO[Unit] = petRepository.update(newage, pet)

    val run: IO[Either[PetDontExist.type, Unit]] = exist.flatMap{
      case true => update.map(_ => ().asRight[PetDontExist.type])
      case false => PetDontExist.asLeft[Unit].pure[IO]
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