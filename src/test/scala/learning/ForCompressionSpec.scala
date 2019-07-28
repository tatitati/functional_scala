package learning

import org.scalatest.FunSuite

class ForCompressionSpec extends FunSuite{
  test("for compressions") {
    val result = for {
      x <- (1 to 3).toList
      y <- (4 to 5).toList
    } yield(x, y)

    assert(List((1,4), (1,5), (2,4), (2,5), (3,4), (3,5)) === result)
  }
}
