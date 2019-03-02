import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class FunctionalSpec extends PlaySpec with GuiceOneAppPerSuite {

  "Routes" should {

    "send a health check" in  {
      route(app, FakeRequest(GET, "/status")).map(status(_)) mustBe Some(OK)
    }
  }

}
