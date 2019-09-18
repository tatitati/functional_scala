package learning.test.domain.builders

trait Buildable[T] {
  def build(): T
}

