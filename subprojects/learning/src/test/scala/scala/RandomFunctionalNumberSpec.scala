package learning.test.scala

import cats.effect.Sync
import org.scalatest.FunSuite

import scala.util.Random

class RandomFunctionalNumberSpec extends FunSuite {

  test("I can generate like:") {
    val number = Random.nextInt

  }

  test("But also I can generate like:") {
    val rng = new Random

    println(rng.nextInt())
    println(rng.nextInt())
  }

  test("asdf") {
    case class SimpleRNG(seed: Long){
      def nextInt: (Int, SimpleRNG) = {
        val newSeed = (seed*0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
        val nextRandomInt = SimpleRNG(newSeed)
        val newState = (newSeed >>> 16).toInt
        (newState, nextRandomInt)
      }
    }

    val random1 = SimpleRNG(4)
    println(random1.nextInt)
    println(random1.nextInt)
    println(random1.nextInt)
  }
}
