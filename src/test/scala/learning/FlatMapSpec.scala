package test.learning

import org.scalatest.FunSuite

class FlatMapSpec extends FunSuite {

  test("flatmap vs map working with Options") {
    def isPar(a: Int): Option[Int] = {
      a % 2 match {
        case 0 => Some(a)
        case _ => None
      }
    }

    val l = List(1,2,3,4,5)

    // with map
    val result1 = l.map( x => isPar(x) )
    assert(List(None, Some(2), None, Some(4), None) === result1)

    // with flatmap
    val result2 = l.flatMap( x => isPar(x) )
    assert(List(2, 4) === result2)
  }

  test("flatmap vs map working with Lists") {
    def createList(a: Int): List[Int] = {
      List(a)
    }

    val l = List(1,2,3,4,5)

    // with map
    val result1 = l.map( x => createList(x) )
    // val result1 = l.map( x => List(x) )
    assert(List(List(1), List(2), List(3), List(4), List(5)) === result1)

    // with flatmap
    val result2 = l.flatMap( x => createList(x) )
    assert(List(1, 2, 3, 4, 5) === result2)
  }

  test("flatmap = map + flatten") {
    val fruits = Seq("apple", "banana", "orange")

    // with map + flatten
    val mapResult = fruits.map(_.toUpperCase)
    val result1 = mapResult.flatten
    assert(List('A', 'P', 'P', 'L', 'E', 'B', 'A', 'N', 'A', 'N', 'A', 'O', 'R', 'A', 'N', 'G', 'E') === result1)

    // with flatmap
    val result2 = fruits.flatMap(_.toUpperCase)
    assert(List('A', 'P', 'P', 'L', 'E', 'B', 'A', 'N', 'A', 'N', 'A', 'O', 'R', 'A', 'N', 'G', 'E') === result2)
  }
}
