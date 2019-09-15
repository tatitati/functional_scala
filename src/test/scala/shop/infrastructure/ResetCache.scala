package shop.infrastructure

import cats.effect._
import shop.domain.Pet

trait ResetCache {
  self: PetRepository =>

  def reset(): IO[Unit] = {
    IO{
      cache = Map(
        "Bolt" -> Pet("Bolt", 17, 232),
        "Lassie" -> Pet("Lassie", 10, 232)
      )
    }
  }
}