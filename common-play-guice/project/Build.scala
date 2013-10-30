import sbt._
import Keys._
import play.Project._

object Build extends sbt.Build {
  val appName = "camelcode"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,

    "org.reflections" % "reflections" % "0.9.9-RC1",

    // Guice
    "com.google.inject" % "guice" % "3.0",
    "com.google.inject.extensions" % "guice-assistedinject" % "3.0",
    "com.google.inject.extensions" % "guice-multibindings" % "3.0",
    "com.google.inject.extensions" % "guice-throwingproviders" % "3.0"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    
  )
}