package repository

import cats.effect.IO
import com.github.nscala_time.time.Imports.DateTime
import learning.domain.user.{User, UserAccount, UserId, UserProfile}

class RepositoryUser {
  var cache: Map[String, User] = Map(
    "surname1" -> User(
      UserProfile(
        firstname = "firstname1",
        surname = "surname1",
        datebirth = None,
        email = "email1"
      ),
      UserAccount(
        username = "username1",
        salt = "salt1",
        hashedPassword = "hash1",
        emailConfirmed = true,
        registeredDateTime = DateTime.now()
      )
    ),
    "surname2" -> User(
      UserProfile(
        firstname = "firstname2",
        surname = "surname2",
        datebirth = None,
        email = "email2"
      ),
      UserAccount(
        username = "username2",
        salt = "salt2",
        hashedPassword = "hash2",
        emailConfirmed = true,
        registeredDateTime = DateTime.now()
      )
    )
  )

  def create(user: User): Unit = {
      cache += (user.getUserId().value -> user)
  }
}
