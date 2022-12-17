import Dependencies._

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.yurique"
ThisBuild / organizationName := "yurique"

lazy val baseDockerImage = "--platform=linux/amd64 eclipse-temurin:17-jre"

lazy val root = (project in file("."))
  .enablePlugins(
    JavaAppPackaging,
    DockerPlugin
  )
  .settings(
    name := "kubernetes-client-test",
    libraryDependencies ++= Seq(
      kubernetesClient,
      decline,
      logback,
      scalaTest % Test
    ),
    Docker / packageName := "yurique/kubernetes-client-test",
    dockerBaseImage      := baseDockerImage
  )
