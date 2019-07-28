package test.learning

import org.scalatest.FunSuite

class FlatMapSpec extends FunSuite {

  test("flatmap vs map") {
    def isPar(a: Int): Option[Int] = {
      a % 2 match {
        case 0 => Some(a)
        case _ => None
      }
    }

    val l = List(1,2,3,4,5)

    val result1 = l.map( x => isPar(x) )
    val result2 = l.flatMap( x => isPar(x) )

    assert(List(None, Some(2), None, Some(4), None) === result1)
    assert(List(2, 4) === result2)
  }

  test("flatmap vs map 2") {
    def createList(a: Int): List[Int] = {
      List(a)
    }

    val l = List(1,2,3,4,5)

    val result1 = l.map( x => createList(x) )
    val result2 = l.flatMap( x => createList(x) )

    assert(List(List(1), List(2), List(3), List(4), List(5)) === result1)
    assert(List(1, 2, 3, 4, 5) === result2)
  }
}
