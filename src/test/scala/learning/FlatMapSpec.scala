package test.learning

import org.scalatest.FunSuite

class FlatMapSpec extends FunSuite {

  // Definition of flatMap():
  // def flatMap[B](f: A => List[B]): List[B]
  //
  // Definition of map():
  // def map[B](f: A => B): List[B]
  test("basic") {
      assert(List(List(1, 1), List(2, 2))           === List(1, 2).map(x => List(x, x)))
      assert(List(1, 1, 2, 2)                       === List(1, 2).flatMap(x => List(x, x)))

      assert(List("FOO", "BAR")                     === List("foo", "bar").map(_.toUpperCase()))
      assert(List('F', 'O', 'O', 'B', 'A', 'R')     === List("foo", "bar").flatMap(_.toUpperCase()))

      assert(List(Some(1), Some(2), None)           === List(Some(1), Some(2), None).map(x => x))
      assert(List(1, 2)                             === List(Some(1), Some(2), None).flatMap(x => x))

      assert(List(List(1), List(2))                 === List(List(1), List(2)).map(x => x))
      assert(List(1, 2)                             === List(List(1), List(2)).flatMap(x => x))
  }

  test("flatmap = map + flatten") {
    assert( List("APPLE", "BANANA")                                     === Seq("apple", "banana").map(_.toUpperCase))
    assert(List('A', 'P', 'P', 'L', 'E', 'B', 'A', 'N', 'A', 'N', 'A')  === Seq("apple", "banana").map(_.toUpperCase).flatten)
    assert(List('A', 'P', 'P', 'L', 'E', 'B', 'A', 'N', 'A', 'N', 'A')  === Seq("apple", "banana").flatMap(_.toUpperCase))
  }

  test("how to do a + b") {
    val a = Some(2)
    val b = Some(3)

    val sum = a.flatMap {
      x => b.map {
          y => x + y
        }
    }

    assert(Some(5) === sum, "better result than with map()")
  }
}
