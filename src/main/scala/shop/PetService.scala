package shop

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): Either[String, Pet] = {
    val result = for {
      _ <- petRepository.doesNotExist(pet)
      saved <- petRepository.create(pet)
    } yield saved

    result
  }
}
