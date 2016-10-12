import org.scalafmt.sbt.ScalaFmtPlugin
import sbt.Keys._
import sbt._

object Build extends AutoPlugin {

  override def requires = ScalaFmtPlugin

  override def trigger = allRequirements

  override def projectSettings =
    ScalaFmtPlugin.autoImport.reformatOnCompileSettings ++
    Vector(
      resolvers ++= Seq {
        "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
      },
      publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository"))),
      fork := true,
      organization := "de.codepitbull.scala.onthefly",
      version := version.in(ThisBuild).value, 
      scalaVersion := Version.Scala,
      crossScalaVersions := Vector(scalaVersion.value),
      scalacOptions ++= Vector(
        "-unchecked",
        "-deprecation",
        "-language:_",
        "-target:jvm-1.8",
        "-encoding", "UTF-8"
      ),
      unmanagedSourceDirectories.in(Compile) := Vector(scalaSource.in(Compile).value),
      unmanagedSourceDirectories.in(Test) := Vector(scalaSource.in(Test).value),

      ScalaFmtPlugin.autoImport.scalafmtConfig := Some(baseDirectory.in(ThisBuild).value / ".scalafmt")
    )
}
