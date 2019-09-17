package learning.domain.user

case class User(
   profile: UserProfile,
   account: UserAccount
 ) {

  def getUserId(): UserId = {
    UserId(profile.surname)
  }
}



