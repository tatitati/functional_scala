package test.cats

import org.scalatest.FunSuite
import cats.Functor
import cats.instances.list._
import cats.instances.option._

class CatFunctorSpec extends FunSuite {

  test("functor in cats") {
    val list1 = List(1,2,3)
    val list2 = Functor[List].map(list1)(_ * 2)
    assert(List(2,4,6) === list2)
  }
}
