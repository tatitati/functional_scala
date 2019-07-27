package learning

import org.scalatest.FunSuite

class FunctorSpec extends FunSuite {

  def functor[X, Y](f: X => Y): List[X] => List[Y] = {

    def fun: (List[X]) => List[Y] = (arg: List[X]) => arg match {
      case x :: xs => f(x) :: fun(xs)
      case Nil => Nil
    }

    fun
  }

  test("functor") {
    val p = List("Hi", "there")

    def doubleEachChar(s: String) = (for (c <- s) yield c + "" + c).toList.mkString
    def numberOfLowerCaseChars(s: String) = s.filter(c => c.isLower).length

    val f1 = functor(doubleEachChar)
    val f2 = functor(numberOfLowerCaseChars)

    assert( List("HHii", "tthheerree") === f1(p))
    assert(List(1, 5) === f2(p))
  }

  test("passing functions to other functions") {

  }
}
