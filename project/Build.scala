import sbt._
import Keys._
import sbtscalaxb.Plugin._
import ScalaxbKeys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "payzen-module"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.0"
  )



  val main = play.Project(appName, appVersion, appDependencies, settings = Defaults.defaultSettings ++ scalaxbSettings).settings(
    organization := "fr.valwin",
    publishMavenStyle := true,
    publishTo := Some("valwin-snapshots" at "http://nexus.valwin.fr/nexus/content/repositories/valwin-snapshots"),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    packageName in scalaxb in Compile := "fr.valwin.payzen.soap",
    sourceGenerators in Compile <+= scalaxb in Compile
  )

}
