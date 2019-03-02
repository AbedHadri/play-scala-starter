import akka.actor.ActorSystem
import controllers.StatusController
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test.FakeRequest

class UnitSpec extends PlaySpec {

  "AsyncController" should {

    "return a valid result on action.async" in {
      val actorSystem = ActorSystem("test")

      implicit val system = actorSystem.dispatcher
      val controller = new StatusController(stubControllerComponents())
      val resultFuture = controller.status(FakeRequest())
      contentAsString(resultFuture) must be("OK")

    }
  }
}
