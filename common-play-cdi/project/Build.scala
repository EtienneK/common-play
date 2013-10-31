import sbt._

import Keys._

import play.Project._

object Build extends sbt.Build {

  val appName = "common-play-cdi"

  val appVersion = "1.0.0-SNAPSHOT"

  val appDependencies = Seq(

    javaCore,

    // CDI
    "org.jboss.weld.se" % "weld-se" % "2.1.0.Final",
    
    "org.apache.deltaspike.core" % "deltaspike-core-api" % "0.5",
    "org.apache.deltaspike.core" % "deltaspike-core-impl" % "0.5",
    
    "org.apache.deltaspike.cdictrl" % "deltaspike-cdictrl-api" % "0.5",
    "org.apache.deltaspike.cdictrl" % "deltaspike-cdictrl-weld" % "0.5",

    // Google Guava
    "com.google.guava" % "guava" % "15.0")

  val main = play.Project(appName, appVersion, appDependencies).settings()

}