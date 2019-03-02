package dao.schema

import models.{Product, User}
import slick.ast.ColumnOption.PrimaryKey
import slick.jdbc.MySQLProfile.api._

class ProductTable(tag: Tag) extends Table[Product](tag, "product") {

  def productId = column[Long]("product_id", PrimaryKey)
  def title = column[String]("title")
  def description = column[String]("description")
  def url = column[String]("url")

  def * = (productId, title, description, url) <> ((Product.apply _).tupled, Product.unapply)
}

class UserTable(tag: Tag) extends Table[User](tag, "user") {

  def username = column[String]("username", PrimaryKey)
  def password = column[String]("password")
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")

  override def * = (username, password, firstName, lastName) <> ((User.apply _).tupled, User.unapply)
}
