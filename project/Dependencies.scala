import sbt._

object Dependencies {
  lazy val scalaTest        = "org.scalatest" %% "scalatest"         % "3.2.11"
  lazy val kubernetesClient = "com.goyeau"    %% "kubernetes-client" % "0.9.0-23-2643343-SNAPSHOT"
  lazy val decline          = "com.monovore"  %% "decline-effect"    % "2.3.1"
  lazy val logback          = "ch.qos.logback" % "logback-classic"   % "1.2.11"
}
