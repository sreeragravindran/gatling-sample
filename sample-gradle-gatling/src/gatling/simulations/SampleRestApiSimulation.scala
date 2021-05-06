import io.gatling.core.Predef.{Simulation, _}
import io.gatling.http.Predef._

class SampleRestApiSimulation extends Simulation {

  val host = System.getenv("IDENTITY_CLIENT_ID")

  val httpProtocol2 = http
    .baseUrl("http://computer-database.gatling.io")
    .inferHtmlResources()
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")


  val scn = scenario("MyComputerTest")
    .exec(http("request_0")
      .get("/computers")
      .headers(headers_0))
    .pause(9)
    .exec(http("request_1")
      .get("/computers?f=amstrad")
      .headers(headers_0))
    .pause(4)
    .exec(http("request_2")
      .get("/assets/stylesheets/bootstrap.min.css")
      .resources(http("request_3")
        .get("/assets/stylesheets/main.css")))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol2)
    .assertions(global.responseTime.max.lt(1000))
}
