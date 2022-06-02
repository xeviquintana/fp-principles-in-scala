
lazy val sharedSettings = Seq(
  assignment := "",
  course := "",
  javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled"),
  libraryDependencies += "org.scalameta" %% "munit" % "0.7.26" % Test,
  scalacOptions ++= Seq("-language:implicitConversions", "-deprecation"),
  scalaVersion := "3.0.0",
  testFrameworks += new TestFramework("munit.Framework"),
  version := "0.1.0"
)

lazy val `week1-example1` = (project in file("week1-example1"))
  .settings(
    sharedSettings,
    assignment := "example",
    course := "progfun1"
)

lazy val `week1-assignment1` = (project in file("week1-assignment1"))
  .settings(
    sharedSettings,
    assignment := "recfun",
    course := "progfun1"
  )

lazy val `week2-example1` = (project in file("week2-example1"))
  .settings(
    sharedSettings
  )

lazy val `week2-assignment1` = (project in file("week2-assignment1"))
  .settings(
    sharedSettings,
    assignment := "funsets",
    course := "progfun1"
)

lazy val `week3-example1` = (project in file("week3-example1"))
  .settings(
    sharedSettings,
    assignment := "funsets",
    course := "progfun1"
)

lazy val `week3-assignment1` = (project in file("week3-assignment1"))
  .settings(
    sharedSettings,
    assignment := "objsets",
    course := "progfun1"
)

lazy val `week4-example1` = (project in file("week4-example1"))
  .settings(
    sharedSettings,
    assignment := "objsets",
    course := "progfun1"
  )

lazy val root = project
  .in(file("."))
  .settings(
    name := "fp-principles-in-scala",
    description := "Coursera Functional Programming Principles in Scala course",
    Global / lintUnusedKeysOnLoad := false,
    sharedSettings
  )
  .aggregate(
      `week1-example1`,
      `week1-assignment1`,
      `week2-example1`,
      `week2-assignment1`,
      `week3-example1`,
      `week3-assignment1`,
      `week4-example1`
  )
