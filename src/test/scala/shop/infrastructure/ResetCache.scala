package shop.infrastructure

import cats.effect._
import shop.domain.order.OrderId
import shop.domain.pet.Pet

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