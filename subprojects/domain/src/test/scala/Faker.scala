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

  def nextString(length: Int = 10): String = {
    val value = for(i <- 1 to length) yield { Random.nextPrintableChar() }
    value.mkString
  }

  def nextBoolean(): State[Seed, Boolean] = State { seed =>
      val result: Long = (seed.long*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      (seed.next, result.toLong > 0)
  }

  def nextInt(): State[Seed, Int] = State { seed =>
      val result: Long = (seed.long*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      (seed.next, result.toInt)
  }

  def nextLong(): State[Seed, Long] = State(seed => (seed.next, seed.long))

  def nextIntPositive(): State[Seed, Int] = State { seed =>
    (seed.next, seed.long.toInt.abs)
  }
}
