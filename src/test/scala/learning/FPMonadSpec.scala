package test.learning

import org.scalatest.FunSuite

class FPMonadSpec extends FunSuite {

    trait Monad[F[_]] {
      def pure[A](value: A): F[A]

      def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

      def map[A, B](value: F[A])(func: A => B): F[B]
    }
}
