package infrastructure.test.pet

import cats.effect._
import SeparateDataFromBehaviour.order.OrderId
import SeparateDataFromBehaviour.pet.Pet
import infrastructure.pet.PetRepository

trait ResetCache {
  self: PetRepository =>

  def reset(): IO[Unit] = {
    IO{
      cache = Map(
        "Bolt" -> Pet(OrderId("00001A"), "Bolt", 17, 232),
        "Lassie" -> Pet(OrderId("00002A"), "Lassie", 10, 232)
      )
    }
  }
}