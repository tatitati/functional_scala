package SeparateDataFromBehaviour.test

import cats.data.State
import scala.util.Random

final case class Seed(long: Long) {
  def next = Seed(long * 6364136223846793005L + 1442695040888963407L)
}

object Faker {
  def anyOf[T](items: T*): T = {
    items(Random.nextInt(items.length))
  }

  def nextLong(): State[Seed, Long] = State(seed => (seed.next, seed.long))

  def nextBoolean(): State[Seed, Boolean] = State( seed => (seed.next, seed.long > 0))

  def nextInt(): State[Seed, Int] = State { seed => (seed.next, seed.long.toInt) }

  def nextIntPositive(): State[Seed, Int] = State { seed => (seed.next, seed.long.toInt.abs)}

  def nextInInterval(max: Int): State[Seed, Int] = State { seed =>
    // (rand() % (max + 1 - min)) + min
    val min = 0
    val number =  (seed.long.toInt.abs % (max + 1 - min)) + min

    (seed.next, number.abs)
  }

  def nextString(length: Int = 10): String = {
    val value = for(i <- 1 to length) yield { Random.nextPrintableChar() }
    value.mkString
  }
}
