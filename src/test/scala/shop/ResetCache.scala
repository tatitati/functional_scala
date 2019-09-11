package test.shop

import cats.effect._
import shop.{Pet, PetRepository}

trait ResetCache {
  self: PetRepository =>

  def reset(): IO[Unit] = {
    IO{
      cache = Map(
        "Bolt" -> Pet("Bolt", 17),
        "Lassie" -> Pet("Lassie", 10)
      )
    }
  }
}