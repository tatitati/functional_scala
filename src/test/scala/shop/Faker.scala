package test.shop

import scala.util.Random

object Faker {
  def apply[T](items: T*): T = {
    items(Random.nextInt(items.length))
  }

  def text(length: Int = 10): String = {
    val value = for(i <- 1 to length) yield { Random.nextPrintableChar() }
    value.mkString
  }

  def boolean(): Boolean = {
    Random.nextBoolean()
  }

  def int(): Int = {
    Random.nextInt()
  }

  def long(): Long = {
    Random.nextLong()
  }
}
