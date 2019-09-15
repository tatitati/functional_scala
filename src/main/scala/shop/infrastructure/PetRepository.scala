package shop.infrastructure

import cats.effect.IO
import shop.domain.Pet

class PetRepository {
  var cache: Map[String, Pet] = Map(
    "Bolt" -> Pet("Bolt", 17, 172),
    "Lassie" -> Pet("Lassie", 10, 230)
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

  def updateAge(newage: Int, pet: Pet): IO[Unit] = {
    IO{
      cache -= pet.name
      cache += (pet.name -> Pet(pet.name, newage, pet.price))
    }
  }

  def updatePrice(price: Int, pet: Pet): IO[Unit] = {
    IO{
      cache -= pet.name
      cache += (pet.name -> Pet(pet.name, pet.age, pet.price))
    }
  }
}
