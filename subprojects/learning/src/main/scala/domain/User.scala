package learning.domain.user

case class User(
   profile: UserProfile,
   account: UserAccount
 ) {

  def getUserId(): UserId = {
    UserId(profile.surname)
  }

  def getUserId(user: User): UserId = user.getUserId()

  def updateEmail(user: User, newEmail: String): this.type = {
    this.copy(
      profile = user.profile.copy(email = newEmail)
    )
  }
}



