package shop

import cats._
import cats.Monad
import cats.effect.IO
import cats.implicits._
import cats.syntax.either._
import scala.util.Random

class PetRepository {
  var cache: Map[String, Pet] = Map(
    "Bolt" -> Pet("Bolt", 17),
    "Lassie" -> Pet("Lassie", 10)
  )

  def create(pet: Pet): IO[Pet] = {
    cache += (pet.name -> pet)

    pet.pure[IO]
  }

  def findByName(name: String): IO[Option[Pet]] = {
    val result = cache.contains(name) match {
      case true => Some(cache(name))
      case false => None
    }

    result.pure[IO]
  }

  def updateAgePet(newAge: Int) = ???
  def exist(pet: Pet) = ???
}
