package infrastructure.pet

sealed trait PetCheck
final case object PetExist extends PetCheck
final case object PetDontExist extends PetCheck
