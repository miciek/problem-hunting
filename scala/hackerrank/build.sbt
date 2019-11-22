lazy val root = (project in file("."))
  .settings(
    name := "hackerrank",
    organization := "Michał Płachta",
    version := "1.0",
    scalaVersion := "2.12.8",
    scalacOptions ++= List(
        "-unchecked",
        "-Xlint"
      ),
    fork in run := true,
    javaOptions in run += "-ea",
    libraryDependencies ++= {
      Seq("org.scalatest" %% "scalatest" % "3.0.8" % Test)
    }
  )
