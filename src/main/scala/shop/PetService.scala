package shop


class PetService (
                 petRepository: PetRepository
                 ) {
  def create(pet: Pet) = ???
//  for {
//    _ <- petRepository.create(pet)
//  }


}
