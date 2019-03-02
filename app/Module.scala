import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import play.api.{Configuration, Environment}
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.hotspot.DefaultExports
import slick.jdbc.MySQLProfile.api._

class Module(environment: Environment, configuration: Configuration) extends AbstractModule
  with ScalaModule  {

  override def configure = {
    // prometheus metrics collector initialization
    CollectorRegistry.defaultRegistry.clear()
    DefaultExports.initialize()

    bind(classOf[CollectorRegistry]).toInstance(CollectorRegistry.defaultRegistry)

    val db = Database.forConfig("mainDB")

    bind(classOf[Database]).toInstance(db)


 }

}
