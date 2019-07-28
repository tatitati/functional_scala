package learning

import org.scalatest.FunSuite

class ForCompressionSpec extends FunSuite{
  test("for compressions basic") {
    val result1 = for {
      x <- (1 to 3)
    } yield x

    assert(List(1,2,3) === result1)

    val result2 = for {
      x <- 1 to 3
      y <- 4 to 5
    } yield(x, y)

    assert(List((1,4), (1,5), (2,4), (2,5), (3,4), (3,5)) === result2)
  }

  test("for compressions and map can be equivalents") {
    val x = 1 to 3

    // with map
    val y = x.map(_ * 2)
    assert(Vector(2, 4, 6) === y)

    // with for compressions
    val z = for {
      i <- x
    } yield i * 2
    assert(Vector(2, 4, 6) === z)
  }

  test("more equivalents") {
    val x = 1 to 2
    val y = 3 to 4

    // with for comprehession
    val w = for {
      xi <- x
      yi <- y
    } yield xi * yi

    val z = x flatMap {
      xi => y map { yi => xi * yi}
    }

    assert(Vector(3, 4, 6, 8) === w)
    assert(Vector(3, 4, 6, 8) === z)
  }
}
