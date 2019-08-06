package test.learning.scala

import org.scalatest.FunSuite

class Something(name: String) {

  def getName(): String = {
    name
  }
}

class ClassSpec extends FunSuite {
  test("class constructor directly assign variables to the class") {
    var a = new Something("whatever")
    assert("whatever" === a.getName())
  }
}
