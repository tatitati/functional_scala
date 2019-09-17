package learning.domain.user

object UserOp {
  def apply(): UserOp = new UserOp()
}

class UserOp extends UserOpInterface {
  override def getUserId(user: User): UserId = ???

  override def equals(user1: User, user2: User): Boolean = ???

  override def updateEmail(user: User): User = ???

  override def isEmailConfirmed(user: User): Boolean = ???

  override def confirmEmail(user: User): User = ???
}
