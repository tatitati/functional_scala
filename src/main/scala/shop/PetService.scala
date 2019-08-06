package shop

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): Either[String, Pet] = {
    for {
      _ <- petRepository.doesNotExist(pet) // doesntExist returns Either[String, Pet]
      saved <- petRepository.create(pet)
    } yield saved
  }
}
