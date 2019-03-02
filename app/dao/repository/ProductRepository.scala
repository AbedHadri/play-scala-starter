package dao.repository
import dao.schema.ProductTable
import javax.inject.{Inject, Singleton}
import models.Product
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductRepository @Inject()(db: Database)(implicit ec: ExecutionContext) {

  lazy val productQuery = TableQuery[ProductTable]

  def getProductById(productId: Long): Future[Option[Product]] = {
    val query = productQuery
      .filter(_.productId === productId)
      .result
      .map(_.map(res => Product(res.productId, res.title, res.description, res.url)))
    db.run(query).map(_.headOption)
  }

  def deleteProductById(productId: Long): Future[Int] = {
    val query = productQuery
      .filter(_.productId === productId)
      .delete

    db.run(query)
  }

  def updateProductById(productId: Long, product: Product): Future[Int] = {
    val query = productQuery
      .filter(_.productId === productId)
      .map(p => (p.title, p.description, p.url))
      .update(product.title, product.description, product.url)

    db.run(query)

  }

  def insertProduct(product: Product): Future[Int] = {
    val query = productQuery
      .map(p => (p.productId , p.title , p.description , p.url)) += (product.productId, product.title , product.description , product.url)
    db.run(query)
  }

}
