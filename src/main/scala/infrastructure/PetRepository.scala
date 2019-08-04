package infrastructure

import domain.Pet
import scala.util.Random
import cats._
import cats.implicits._

class PetRepository {
  private var cache: Map[Long, Pet] = Map()
  private val random = new Random

  def create(pet: Pet): Option[Pet] = {
    val randomId = random.nextLong()
    val petToSave = pet.copy(id = Some(randomId))

    cache += (randomId -> petToSave)

    petToSave.pure[Option]
  }
}
