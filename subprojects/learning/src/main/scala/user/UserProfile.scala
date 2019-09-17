package learning.domain.user

import com.github.nscala_time.time.Imports.DateTime

case class UserProfile(
  firstname: String,
  surname: String,
  datebirth: Option[DateTime],
  email: String
)
