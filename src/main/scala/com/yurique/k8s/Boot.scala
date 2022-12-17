package com.yurique.k8s

import cats.data.OptionT
import cats.effect._
import com.monovore.decline.effect.CommandIOApp
import cats.syntax.all._
import com.goyeau.kubernetes.client.{KubeConfig, KubernetesClient}
import com.monovore.decline.Opts
import org.typelevel.log4cats.{Logger, SelfAwareStructuredLogger}

object Boot
    extends CommandIOApp(
      name = "kubernetes-client-test",
      header = "For testing the kubernetes client."
    ) {

  private val `namespace` =
    Opts
      .option[String](
        long = "namespace",
        short = "n",
        help = "Specify the namespace."
      )
      .map(_.some)
      .withDefault(none)

  private val listPodsCommand =
    Opts
      .subcommand(
        "pods",
        "list pods",
        helpFlag = true
      )(
        namespace
      )

  implicit private val logger: Logger[IO] = org.typelevel.log4cats.slf4j.Slf4jLogger.getLogger[IO]

  override def main: Opts[IO[ExitCode]] =
    listPodsCommand.map { namespace =>
      OptionT(KubeConfig.standard)
        .flatTapNone(logger.warn("failed to find the k8s configuration"))
        .semiflatMap { config =>
          KubernetesClient(config).use { client =>
            val podList = namespace match {
              case None     => client.pods.list()
              case Some(ns) => client.pods.namespace(ns).list()
            }
            podList.flatMap { pods =>
              logger.info(s"found ${pods.items.size} pods") >>
                pods.items.traverse_ { p =>
                  logger.info(s"pod: name: ${p.metadata.flatMap(_.name)}, phase: ${p.status.flatMap(_.phase)}")
                }
            }
          }
        }
        .value
        .as(ExitCode.Success)
    }

}
