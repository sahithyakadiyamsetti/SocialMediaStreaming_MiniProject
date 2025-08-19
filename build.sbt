ThisBuild / version := "0.1.0-SNAPSHOT"

// Use Scala 2.12.x (works with Spark 3.5.1)
ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "SocialMediaStreaming",
    version := "0.1",
    scalaVersion := "2.12.18",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core"      % "3.5.1",
      "org.apache.spark" %% "spark-sql"       % "3.5.1",
      "org.apache.spark" %% "spark-graphx"    % "3.5.1",
      "org.apache.spark" %% "spark-streaming" % "3.5.1"
    )
  )
