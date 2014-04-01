import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName         = "payzen-module"
  val appVersion      = "1.1-SNAPSHOT"

  val appDependencies = Seq(
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.0"
  )



  val main = play.Project(appName, appVersion, appDependencies).settings(
    organization := "fr.valwin",
    publishMavenStyle := true,
    publishTo := Some("valwin-snapshots" at "http://nexus.valwin.fr/nexus/content/repositories/valwin-snapshots"),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )

}
