package com.nhn.mvc.model

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "product")
@DynamicUpdate
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productNo: Int = 0,
    var productName: String,
    var registerYmdt: LocalDateTime? = LocalDateTime.now(),
    var salePrice: Int,
    var updateYmdt: LocalDateTime? = null,
) {
    fun update(productUpdateVo: ProductUpdateVo) {
        with(productUpdateVo) {
            this.productName?.let { this@Product.productName = it }
            this.salePrice?.let { this@Product.salePrice = it }
        }
        this.updateYmdt = LocalDateTime.now()
    }
}
