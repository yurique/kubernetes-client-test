import Dependencies._

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / organization     := "com.yurique"
ThisBuild / organizationName := "yurique"
ThisBuild / git.baseVersion  := "0.1.0"

lazy val baseDockerImage = "--platform=linux/amd64 eclipse-temurin:17-jre"

lazy val root = (project in file("."))
  .enablePlugins(
    JavaAppPackaging,
    DockerPlugin,
    GitVersioning
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
