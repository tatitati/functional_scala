package test.learning.scala

import org.scalatest.FunSuite

class ImplicitConversionSpec extends FunSuite {
  
  test("I can add a new method to a class") {
    case class Person(name: String) {
      def greet(): String = s"Hello $name"
    }

    implicit def liftStringToperson(str: String): Person = Person(str)

    assert("Hello Maria" === "Maria".greet())
  }
}
