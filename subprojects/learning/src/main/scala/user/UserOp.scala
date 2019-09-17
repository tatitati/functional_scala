package learning.domain.user

object UserOp {
  def getUserId(user: User): UserId = user.getUserId()

  def updateEmail(user: User, newEmail: String): User = {
    user.copy(
      profile = user.profile.copy(email = newEmail)
    )
  }

//  def isEmailConfirmed(user: User): Boolean = ???
//
//  def confirmEmail(user: User): User = ???
}
