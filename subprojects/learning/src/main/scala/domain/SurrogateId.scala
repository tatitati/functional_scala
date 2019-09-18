package learning.domain

import learning.domain.user.User

//trait SurrogateId[T] {
//  self: T =>
//  def setSurrogateId(withSurrogateId: Option[Long]): T = {
//    self.copy(surrogateId = withSurrogateId)
//  }
//}
//


//object something {
//
//  //  trait HasSurrogateId[T] {
//  //    def setSurrogateId(obj: T, id: Long): T
//  //  }
//  //
//  //  implicit val myvalue = new HasSurrogateId[User] {
//  //    def setSurrogateId(user: User, id: Long): User = {
//  //      user.copy(surrogateId = id)
//  //    }
//  //  }
//  //}
//
//  implicit class HasSurrogateId(user: User) {
//    def setSurrogateId(user: User, id: Option[Long]): User = {
//      user.copy(surrogateId = id)
//    }
//  }
//}