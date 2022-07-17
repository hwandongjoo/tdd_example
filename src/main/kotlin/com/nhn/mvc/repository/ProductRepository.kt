package com.nhn.mvc.repository

import com.nhn.mvc.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int> {

    fun findByProductNo(productNo: Int): Product?

    fun deleteByProductNo(productNo: Int)

}
