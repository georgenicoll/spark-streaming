val junitVersion = "4.12"
val scalazVersion = "7.1.1"
val scalaTestVersion = "1.3"
val slf4jVersion = "1.7.10"
val sparkVersion = "1.2.1"

val orgScalaz = "org.scalaz"
val scalazDeps = Seq(
  orgScalaz %% "scalaz-core" % scalazVersion,
  orgScalaz %% "scalaz-effect" % scalazVersion,
  orgScalaz %% "scalaz-typelevel" % scalazVersion,
  orgScalaz %% "scalaz-scalacheck-binding" % scalazVersion % "test"
)
val orgApacheSpark = "org.apache.spark"
val sparkStreaming = orgApacheSpark %% "spark-streaming" % sparkVersion
val slf4japi = "org.slf4j" % "slf4j-api" % slf4jVersion
val slf4jsimple = "org.slf4j" % "slf4j-simple" % slf4jVersion
val junit = "junit" % "junit" % junitVersion
val scalatest = "org.scalatest" %% "scalatest" % scalaTestVersion % "test"

lazy val commonSettings = Seq(
  organization := "org.monkeynuthead",
  version := "0.1.0",
  scalaVersion := "2.11.5",
  sbtVersion := "0.13.7",
  scalacOptions ++= Seq("-feature","-unchecked","-deprecation")
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "spark-streaming",
    libraryDependencies ++= Seq(
      scalazDeps,
      Seq(slf4japi, slf4jsimple, sparkStreaming, junit, scalatest)
    ).flatten,
    initialCommands in console := "import scalaz._, Scalaz._"
  )
