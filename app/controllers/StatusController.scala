package controllers

import java.io.StringWriter

import javax.inject._
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.common.TextFormat
import play.api.mvc._
import scala.concurrent.Future

class StatusController @Inject()(val cc: ControllerComponents)
    extends AbstractController(cc) {

  // a health check endpoint
  def status: Action[AnyContent] = Action.async {
    Future.successful(Ok("OK"))
  }

  // an endpoint in order to be queried by prometheus to obtain metrics
  def metrics: Action[AnyContent] = Action.async {
    Future.successful({
      val sw = new StringWriter()
      TextFormat.write004(sw, CollectorRegistry.defaultRegistry.metricFamilySamples())
      sw.flush()
      Ok(sw.toString)
    })
  }

}
