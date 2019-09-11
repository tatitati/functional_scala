package shop

import cats.data._
import cats.effect._
import cats.implicits._

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