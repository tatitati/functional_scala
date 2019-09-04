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

  def create(pet: Pet): IO[Unit] = {
    IO{
      cache += (pet.name -> pet)
    }
  }

  def findByName(name: String): IO[Option[Pet]] = {
    IO {
      cache.contains(name) match {
        case true => Some(cache(name))
        case false => None
      }
    }
  }

  def list(): IO[List[Pet]] = {
    IO{
      cache.map(_._2).toList
    }
  }

  def exist(name: String): IO[Boolean] = {
    IO{
      cache.contains(name)
    }
  }
}
