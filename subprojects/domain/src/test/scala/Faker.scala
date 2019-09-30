package domain.test

import cats.data.State
import scala.util.Random

final case class Seed(long: Long) {
  def next = Seed(long * 6364136223846793005L + 1442695040888963407L)
}

object Faker {
  def nextOf[T](items: T*): State[Seed, T] = {
    // for {
    //    nextNumber <- nextInInterval(items.length)
    // } yield items(nextNumber)

    nextInInterval(items.length).map(nextNumber =>
      items(nextNumber)
    )
  }

  def nextString(length: Int = 10): State[Seed, String] = {
    val all = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

    val result: State[Seed, Seq[String]] = for{
      _ <- List.range(0, length)
      nextIndex: Int <- nextInInterval(all.length)
      character: String <- all(nextIndex)
    } yield character

    result.map(x => x.mkString(""))
  }

  def nextLong(): State[Seed, Long] = State(seed => (seed.next, seed.long))

  def nextBoolean(): State[Seed, Boolean] = State( seed => (seed.next, seed.long > 0))

  def nextInt(): State[Seed, Int] = State { seed => (seed.next, seed.long.toInt) }

  def nextIntPositive(): State[Seed, Int] = State { seed => (seed.next, seed.long.toInt.abs)}

  def nextInInterval(max: Int): State[Seed, Int] = State { seed =>
    // (rand() % (max + 1 - min)) + min
    val min = 0
    val number =  (seed.long.toInt.abs % (max - min)) + min

    (seed.next, number)
  }
}
