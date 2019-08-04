package application

import domain.Pet
import infrastructure.PetRepository

class PetService (
                 petRepository: PetRepository
                 ) {
  def create(pet: Pet): Pet = ???
}
