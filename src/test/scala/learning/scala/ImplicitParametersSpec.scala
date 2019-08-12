package test.learning.scala

import org.scalatest.FunSuite

class ImplicitParametersSpec extends FunSuite {

  test("I don't need to pass any parameter") {
    def sayHello(implicit name: String): String = s"Hello $name"

    implicit val friend = "Antonio"

    assert("Hello Antonio" === sayHello)
  }
}
