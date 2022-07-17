package com.nhn.mvc.service

import com.nhn.mvc.exception.*
import com.nhn.mvc.model.*
import com.nhn.mvc.repository.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findProductList(): List<Product> = productRepository.findAll()

    fun findProductDetail(productNo: Int): Product {
        return productRepository.findByProductNo(productNo) ?: throw NotExistProduct(ProductErrorCode.NOT_EXIST)
    }

    @Transactional
    fun deleteProduct(productNo: Int) {
        productRepository.deleteByProductNo(productNo)
    }

    fun addProduct(productName: String, salePrice: Int): Product {
        val product = Product(
            productName = productName,
            salePrice = salePrice,
        )
        return productRepository.save(product)
    }

    @Transactional
    fun updateProduct(product: ProductUpdateVo) {
        productRepository.findByProductNo(product.productNo)?.run {
            this.update(product)
        }
    }
}
