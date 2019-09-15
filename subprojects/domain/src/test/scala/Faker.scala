package domain.test

import cats.data.State
import domain.test.RandomIntGenerator.Generator

import scala.util.Random

object Faker {
  def anyOf[T](items: T*): T = {
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
    val result = State[Long, Int] {
      seed  =>
        val result: Long = (seed*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
        val newState = result >>> 16
        (newState, result.toInt)
    }

    result.run(
      System.currentTimeMillis()
    ).value._2
  }

  def long(): Long = {
    Random.nextLong()
  }
}
