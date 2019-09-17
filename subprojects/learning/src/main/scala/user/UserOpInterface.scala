package learning.domain.user

trait UserOpInterface {
  def getUserId(user: User): UserId
  def equals(user1: User, user2: User): Boolean
  def updateEmail(user: User): User
  def isEmailConfirmed(user: User): Boolean
  def confirmEmail(user: User): User
}


