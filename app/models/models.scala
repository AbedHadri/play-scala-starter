package models

import play.api.libs.json.Json

case class Product(productId: Long, title: String, description: String, url: String)

case class User(username: String, password: String, firstName: String, lastName: String)

/*
 * define implicits to be imported wherever Object -> Json (or vice versa) transformation needed
 * */
object JsonProtocols {
  implicit val productFormat = Json.format[Product]

  implicit val userFormat = Json.format[User]
}
