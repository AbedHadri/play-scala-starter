package controllers

import javax.inject._
import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import models.JsonProtocols._
import models.Product
import play.api.libs.json.Json
import services.ProductService

import scala.concurrent.ExecutionContext

class ProductController @Inject()(
  val cc: ControllerComponents,
  val productService: ProductService,
  actorSystem: ActorSystem
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  def getProductById(productId: Long): Action[AnyContent] = Action.async {
    productService.getProductByProductId(productId).map {
      case Some(result) => Ok(Json.toJson(result))
      case None => NotFound
    }
  }

  def deleteProductByProductId(productId: Long): Action[AnyContent] = Action.async {
    productService.deleteProductByProductId(productId).map {
      case result if result > 0 => Ok
      case _ => NotFound
    }
  }

  def addProduct(): Action[Product] = Action(parse.json[Product]) { request =>
    productService.addProduct(request.body)
    NoContent
  }

}
