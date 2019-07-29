package test.learning

import org.scalatest.FunSuite

class FlatMapSpec extends FunSuite {

  // Definition:
  // flatMap[B](f: A => List[B]): List[B]

  test("basic") {
      assert(List("FOO", "BAR")                     === List("foo", "bar").map(_.toUpperCase()))
      assert(List('F', 'O', 'O', 'B', 'A', 'R')     === List("foo", "bar").flatMap(_.toUpperCase()))
      assert(List(Some(1), Some(2), None)           === List(Some(1), Some(2), None).map(x => x))
      assert(List(1, 2)                             === List(Some(1), Some(2), None).flatMap(x => x))
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
