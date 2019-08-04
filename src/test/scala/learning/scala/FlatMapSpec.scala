package test.earning.scala

import org.scalatest.FunSuite

class FlatMapSpec extends FunSuite {

  // About [A]...[A] defines the content of our List, is defined like:
  //
  //      trait List[A] {
  //        def map[B](f: A => B): List[B]
  //        def flatMap[B](f: A => List[B]): List[B]
  //      }

  test("basic") {
      assert(List("1", "2")                         === List(1, 2).map(_.toString))
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

  test("Flatmap, like map, can extract the value of a wrapper") {
    val a = Some(22)

    val sum =
      a.flatMap { aVal =>
        assert(22 === aVal)
        Some(5)
      }

    assert(Some(5) === sum, "better result than with map()")
  }

  test("how to do a + b WITH FLATMAP") {
    val a = Some(2)
    val b = Some(3)

    val sum =
      a.flatMap { aVal =>
        b.map { bVal =>
          aVal + bVal
        }
    }

    assert(Some(5) === sum, "better result than with map()")
  }

  test("how to do a + b WITH FLATMAP2") {
    val a = Some(2)
    val b = Some(3)
    val c = Some(4)

    val sum =
      a.flatMap { aVal =>
        b.flatMap { bVal =>
          c.map { cVal =>
            aVal + bVal + cVal
        }
      }
    }

    assert(Some(9) === sum, "better result than with map()")
  }

  test("how to do a + b WITH FLATMAP5") {
    val a = Some(2)
    val b = None
    val c = Some(4)

    val sum =
      a.flatMap { aVal =>
        b.flatMap { bVal =>
          c.map { cVal =>
            aVal + bVal + cVal
          }
        }
      }

    assert(None === sum, "None as there is one none")
  }

  test("how to do a + b WITH MAP") {
    val a = Some(2)
    val b = Some(3)

    val sum =
      a.map{ aVal =>
        b.map { bVal =>
          aVal + bVal
      }
    }

    assert(Some(Some(5)) === sum)
  }
}
