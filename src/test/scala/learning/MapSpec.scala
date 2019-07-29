package test.learning

import org.scalatest.FunSuite

class MapSpec extends FunSuite {

  // Definition
  //    def map[B](f: A => B): List[B]
  //
  // If we pass a List[Int] to map we have:
  //    def map[B](f: Int => B): List[B]

  test("map") {
    assert(List(1,2,3,4,5)                  === List(1,2,3,4,5).map( x => x))
    assert(List(2, 4, 6, 8, 10)             === List(1,2,3,4,5).map( x => x * 2 ))
    assert(List("1", "2", "3", "4", "5")    === List(1,2,3,4,5).map( x => x.toString ))
    assert(List(2, 5)                       === List("hi", "there").map( _.length ))
  }

  test("pass function") {
    def isPar(a: Int): Option[Int] = {
      a % 2 match {
        case 0 => Some(a)
        case _ => None
      }
    }


    assert(List(None, Some(2), None, Some(4))   === List(1,2,3,4).map(isPar(_)))
  }
}
