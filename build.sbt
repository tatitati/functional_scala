import Dependencies._

scalacOptions += "-Ypartial-unification"
organization := "com.example"
organizationName := "example"

val commonsSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.8"
)

val thirdDependencies = Seq(
  scalaTest % Test,
  "org.typelevel" %% "cats-core" % "2.0.0-M4",
  "org.typelevel" %% "cats-effect" % "1.3.1",
  "com.github.nscala-time" %% "nscala-time" % "2.22.0"
)

lazy val application = (project in file("subprojects/application"))
  .dependsOn(domain % "test->test;compile->compile", infrastructure % "test->test;compile->compile")
  .settings(
    name := "application subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
)

lazy val infrastructure = (project in file("subprojects/infrastructure"))
  .dependsOn(domain % "test->test;compile->compile")
  .settings(
    name := "infrastructure subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
)

lazy val domain = (project in file("subprojects/domain"))
  .settings(
    name := "domain subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
)

lazy val learning = (project in file("subprojects/learning"))
  .settings(
    name := "learning subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
)

lazy val root = (project in file("."))
  .aggregate(learning, domain, infrastructure, application)
  .settings(
    name := "root project",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
)