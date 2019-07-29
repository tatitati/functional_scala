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
    assert(Vector(2, 4, 6) === (1 to 3).map(_ * 2))

    // with for compressions
    val z = for {
      i <- 1 to 3
    } yield i * 2
    assert(Vector(2, 4, 6) === z)
  }

  test("how to do a+b ?") {
    val z = for {
      a <- Some(2)
      b <- Some(3)
    } yield a+b
    assert(Some(5) === z)
  }

  test("how to do a+b if some is not valid?") {
    def convertInt(from: String): Option[Int] = {
      try{
        Some(from.trim.toInt)
      } catch {
        case e: Exception => None
      }
    }
    val z = for {
      a <- convertInt("2")
      b <- convertInt("this is None")
    } yield a+b
    assert(None === z)
  }

  test("more equivalents") {
    // with for comprehession
    val w = for {
      xi <- 1 to 2
      yi <- 3 to 4
    } yield xi * yi

    val z = (1 to 2).flatMap {
      xi => (3 to 4).map { yi => xi * yi}
    }

    assert(Vector(3, 4, 6, 8) === w)
    assert(Vector(3, 4, 6, 8) === z)
  }
}
