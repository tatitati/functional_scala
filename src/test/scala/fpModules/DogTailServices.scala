package test.fpModules

import java.awt.Color

trait Animal {}
abstract class AnimalWithTail(tailColor: Color) extends Animal {}

trait DogTailServices {

    // Implementers must be a subtype of AnimalWithTail
    // self-type: this trait can only be mixed into classes that extend AnimalWithTail
    this: AnimalWithTail =>

    def wagTail = println("waggint tail")
    def lowerTail = println("lowering tail")
    def raiseTail = println("raising tail")
    def curlTail = println("curling tail")
}

trait DogMouthServices {

  // Implementers must be a subtype of AnimalWithTail
  // self-type: this trait can only be mixed into classes that extend AnimalWithTail
  this: AnimalWithTail =>

  def bark = println("barking mouth")
  def lick = println("licking mouth")
}
