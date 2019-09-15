package infrastructure.pet

sealed trait PetCheck
case object PetExist extends PetCheck
case object PetDontExist extends PetCheck
