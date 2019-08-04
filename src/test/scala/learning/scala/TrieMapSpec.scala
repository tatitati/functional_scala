package test.learning.scala

import org.scalatest.FunSuite

import scala.collection.concurrent.TrieMap

class TrieMapSpec extends FunSuite{
  test("hashmap with +=") {
      val map1 = TrieMap(
        "key1" -> "value1",
        "key2" -> "value2",
        "key3" -> "value3"
      )

    map1 += ("key4" -> "value4")

    assert(map1 === TrieMap(
      "key1" -> "value1",
      "key2" -> "value2",
      "key3" -> "value3",
      "key4" -> "value4"
    ))
  }
}
