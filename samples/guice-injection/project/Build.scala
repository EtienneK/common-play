import sbt._
import Keys._
import play.Project._

object Build extends sbt.Build {
  val appName = "guice-injection"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,

    "common-play-guice" % "common-play-guice_2.10" % "1.0-SNAPSHOT"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    
  )
}