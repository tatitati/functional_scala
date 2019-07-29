package test.learning

import org.scalatest.FunSuite

class MapSpec extends FunSuite {
  test("map") {

    val result1 = List(1,2,3,4,5).map( x => x * 2 )
    val result2 = List("hi", "there").map( x => x.length )
    val result3 = List("hi", "there").map( _.length )
    val result4 = List("foo", "bar").map(_.split(""))


    assert(List(2, 4, 6, 8, 10) === result1, "Convert Int => Int")
    assert(List(2, 5) === result2, "Convert String => Int")
    assert(List(2, 5) === result3, "Convert String => Int, using a shotert syntax")
    //assert(List(Array("f", "o", "o"), Array("b", "a", "r")) === result4)
  }

  test("pass function") {
    def isPar(a: Int): Option[Int] = {
      a % 2 match {
        case 0 => Some(a)
        case _ => None
      }
    }

    val result1 = List(1,2,3,4).map( x => isPar(x) )
    val result2 = List(1,2,3,4).map(isPar(_))

    assert(List(None, Some(2), None, Some(4)) === result1)
    assert(List(None, Some(2), None, Some(4)) === result2)
  }
}
