package services

import com.typesafe.scalalogging.StrictLogging
import dao.repository.ProductRepository
import javax.inject.{Inject, Singleton}
import models.Product

import scala.concurrent.Future

@Singleton
class ProductService @Inject()(productRepository: ProductRepository) extends StrictLogging {

  def getProductByProductId(productId: Long): Future[Option[Product]] = {
    productRepository.getProductById(productId)
  }

  def deleteProductByProductId(productId: Long): Future[Int] = {
    productRepository.deleteProductById(productId)
  }

  def updateProductByProductId(productId: Long, product: Product): Future[Int] = {

    if (productId == product.productId) {
      productRepository.updateProductById(productId, product)
    } else {
      Future.failed(new RuntimeException("input productIds should be equal."))
    }
  }

  def addProduct(product: Product): Future[Int] = {
    productRepository.insertProduct(product)
  }

}
