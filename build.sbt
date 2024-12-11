ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.2"

lazy val root = (project in file("."))
  .settings(
    name := "TGbotti-prenew"
  )

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client3" %% "core" % "3.9.5",  // HTTP client
  "io.circe" %% "circe-parser" % "0.14.9",            // JSON parsing
  "io.circe" %% "circe-generic" % "0.14.7"            // JSON generic support
)
