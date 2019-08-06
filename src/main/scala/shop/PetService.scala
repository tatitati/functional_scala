package shop

class PetService(petRepository: PetRepository) {

  def create(pet: Pet): Either[String, Pet] = {
    for {
      _ <- petRepository.doesNotExist(pet)
      saved <- petRepository.create(pet)
    } yield saved   // if create() returns an Either, and saved (the extracted value) is a Pet.
                    // However, the resulting output of this function is still Either. This is
                    // because for{} blocks operate with the inner items, but doesn't modify the container
  }
}
