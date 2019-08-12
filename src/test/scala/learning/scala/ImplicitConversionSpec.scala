package test.learning.scala

import org.scalatest.FunSuite

class ImplicitConversionSpec extends FunSuite {

  test("I can add a new method to another class LIFTING OR WRAPPING") {
    case class Person(name: String) {
      def greet(): String = s"Hello person: $name"
    }

    case class Counter(number: Int) {
      def greet(): String = s"Hello number: $number"
    }

    implicit def liftStringToperson(str: String): Person = Person(str)
    implicit def liftIntToperson(number: Int): Counter = Counter(number)

    assert("Hello person: Maria" === "Maria".greet())
    assert("Hello number: 5" === 5.greet())
  }

  test("I can use a shourtcut to do the same") {
    implicit class StringToperson(str: String) {
      def greet(): String = s"Hello $str"
    }

    assert("Hello Maria" === "Maria".greet())
  }
}
