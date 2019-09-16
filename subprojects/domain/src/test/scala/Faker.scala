package domain.test

import cats.data.State
import scala.util.Random

final case class SeedLong(long: Long) {
  def next = SeedLong(long * 6364136223846793005L + 1442695040888963407L)
}

object Faker {
  def anyOf[T](items: T*): T = {
    items(Random.nextInt(items.length))
  }

  def text(length: Int = 10): String = {
    val value = for(i <- 1 to length) yield { Random.nextPrintableChar() }
    value.mkString
  }

  def boolean(): State[SeedLong, Boolean] = State {(seed: SeedLong) =>
      val result: Long = (seed.long*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      (seed.next, result.toLong > 0)
  }

  def int(): State[SeedLong, Int] = State { (seed: SeedLong) =>
      val result: Long = (seed.long*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      (seed.next, result.toInt)
  }

  def positiveInt(): State[SeedLong, Int] = State { (seed: SeedLong) =>
      val result: Long = (seed.long*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      (seed.next, result.toInt.abs)
  }

  def long(): State[SeedLong, Long] = State { (seed: SeedLong) =>
      val result: Long = (seed.long*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      (seed.next, result.toLong)
  }
}
