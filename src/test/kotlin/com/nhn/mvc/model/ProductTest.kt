package com.nhn.mvc.model

import io.mockk.*
import io.mockk.junit5.*
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

/**
 * @author Hwandong Joo
 */
@ExtendWith(MockKExtension::class)
internal class ProductTest {

    @Test
    fun `수정시 업데이트 날짜 갱신`() {
        val productNo = 1
        // mock
        val nowDateTime = LocalDateTime.of(2020, 7, 14, 12, 4, 15)
        mockkStatic(LocalDateTime::class)
        every { LocalDateTime.now() } returns nowDateTime


        val product = Product(productNo, "TEST", salePrice = 10000)
        val update = ProductUpdateVo(productNo, "update", 1000)
        product.update(update)

        assertEquals(nowDateTime, product.updateYmdt)
        assertEquals(update.productName, product.productName)
        assertEquals(update.salePrice, product.salePrice)
    }
}
