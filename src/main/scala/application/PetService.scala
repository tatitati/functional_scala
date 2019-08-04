package application

import domain.Pet
import infrastructure.PetRepository

class PetService (
                 petRepository: PetRepository
                 ) {
  def create(pet: Pet) = for {
    _ <- petRepository.create(pet)
  }


}
