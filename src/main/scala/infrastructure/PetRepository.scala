package infrastructure

import domain.Pet
import scala.util.Random

class PetRepository {
  private var cache = Map[Long, Pet]
  private val random = new Random

  def create(pet: Pet) = {
    val randomId = random.nextLong()
    val petToSave = pet.copy(id = Some(randomId))

    cache += (randomId -> petToSave)
    //toSave.pure[F]
  }
}
