package learning.test.user

import learning.domain.user.{UserId, UserOp}
import learning.test.user.builders.{BuildUser, BuildUserProfile}
import org.scalatest.FunSuite

class UserOpSpec extends FunSuite {

  test("UserOp.getUserId()") {

    val userId = UserOp.getUserId(BuildUser().build())

    assert(userId.isInstanceOf[UserId])
  }

  test("UserOp.updateEmail(...)") {
    val profile = BuildUserProfile().withEmail("email1").build()
    val user1 = BuildUser()
      .withUserProfile(profile)
      .build()

    assert("email1" === user1.profile.email)

    val user11 = UserOp.updateEmail(user1, "email2")

    assert("email2" === user11.profile.email)
  }
}
