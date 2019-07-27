package learning

import org.scalatest.FunSuite

class FlatMapSpec extends FunSuite {

  test("flatmap") {
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
}
