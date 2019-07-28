package learning

import org.scalatest.FunSuite

class FunctorSpec extends FunSuite {

  trait Functor[F[_]] {
    def map[T, Y](l: F[T])(f: T => Y): F[Y]
  }

  object functor {
    val listFunctor = new Functor[List] {
      def map[T, Y](l: List[T])(f: (T) => Y): List[Y] = l.map(f)
    }
  }

  test("new functor") {
    val listFunctor = functor.listFunctor
    val result = listFunctor.map(List(1,2,3,4,5,6))(_ * 2)

    assert(result === List(2,4,6,8,10,12))
  }
}
