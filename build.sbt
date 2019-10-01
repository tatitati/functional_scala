import Dependencies._

scalacOptions += "-Ypartial-unification"
organization := "com.example"
organizationName := "example"

val commonsSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.8"
)

lazy val doobieVersion = "0.8.4"

val thirdDependencies = Seq(
  scalaTest % Test,
  "com.github.nscala-time" %% "nscala-time" % "2.22.0",

  //  Cats
  "org.typelevel" %% "cats-core" % "2.0.0-M4",
  "org.typelevel" %% "cats-effect" % "1.3.1",

  //  Refined
  "eu.timepit" %% "refined"                 % "0.9.10",
  "eu.timepit" %% "refined-cats"            % "0.9.10",

  //  Doobie
  "org.tpolecat" %% "doobie-core"     % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.tpolecat" %% "doobie-specs2"   % doobieVersion
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