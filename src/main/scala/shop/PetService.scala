package shop

class PetService(petRepository: PetRepository) {

  def create(pet: Pet) = {
    for {
      _ <- petRepository.doesNotExist(pet)
      saved <- petRepository.create(pet)
    } yield saved
  }
}
