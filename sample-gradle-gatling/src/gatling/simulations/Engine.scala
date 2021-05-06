
import com.typesafe.config.ConfigFactory
import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

/**
 * This object simply provides a `main` method that wraps
 * [[io.gatling.app.Gatling]].main, which
 * allows us to do some configuration and setup before
 * Gatling launches.
 */
object Engine extends App {

  val conf = ConfigFactory.load()
  val identityHost = conf.getString("identity.dns")

  // This sets the class for the simulation we want to run.
  val simClass = classOf[SampleRestApiSimulation].getName

  val props = new GatlingPropertiesBuilder
  props.simulationClass(simClass)
  props.resultsDirectory("./build/reports/gatling")

  Gatling.fromMap(props.build)
}