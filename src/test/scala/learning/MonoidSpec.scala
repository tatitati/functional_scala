package learning

import org.scalatest.FunSuite

class MonoidSpec extends FunSuite {
  trait Monoid[A] {
    // an identity element
    def id: A
    // an associative operation
    def op(x: A, y: A): A
  }

  val stringMonoid = new Monoid[String] {
    def id = ""
    def op(x: String, y: String) = x + y
  }

  def listMonoid[A] = new Monoid[List[A]] {
    def id = Nil
    def op(x: List[A], y: List[A]) = x ++ y
  }

  assert("aabb" === stringMonoid.op("aa", "bb"))
  assert("" === stringMonoid.id)

  assert(List("aa", "bb", "cc", "dd") === listMonoid.op(List("aa", "bb"), List("cc", "dd")))
  assert(Nil === listMonoid.id)
}
