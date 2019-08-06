package shop

class PetService (petRepository: PetRepository) {

  private var repo =  petRepository

//  def create(pet: Pet) = {
//    for {
//      <- petRepository.doesNotExist(pet)
//      _ <- petRepository.create(pet)
//    }
//  }
}
