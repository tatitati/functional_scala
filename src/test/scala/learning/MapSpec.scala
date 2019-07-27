package learning

import org.scalatest.FunSuite

class MapSpec extends FunSuite {
  test("map") {
    val l = List(1,2,3,4,5)

    val result = l.map( x => x*2 )

    assert(List(2, 4, 6, 8, 10) === result)
  }

  test("pass function") {
    def isPar(a: Int): Option[Int] = {
      a % 2 match {
        case 0 => Some(a)
        case _ => None
      }
    }

    val l = List(1,2,3,4,5)

    val result = l.map( x => isPar(x) )

    assert(List(None, Some(2), None, Some(4), None) === result)
  }
}
