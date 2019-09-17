package learning.test.user.builders

trait Buildable[T] {
  def build(): T
}

