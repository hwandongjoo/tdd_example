package com.nhn.mvc.service

import com.nhn.mvc.exception.*
import com.nhn.mvc.model.*
import com.nhn.mvc.repository.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author Hwandong Joo
 */
@ExtendWith(MockKExtension::class)
internal class ProductServiceTest {
    @MockK
    private lateinit var productRepository: ProductRepository

    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        productService = ProductService(
            productRepository = productRepository
        )
    }

    @Test
    fun `상품 리스트 조회하기`(){
        val product = mockk<Product>()
        every { productRepository.findAll() } returns listOf(product)

        val actual = productService.findProductList()

        assertEquals(1, actual.count())
    }



    @Test
    fun `상품 상세 조회하기`(){
        val productNo = 1
        val productName ="test"
        val product = mockk<Product>() {
            every { this@mockk.productNo } returns productNo
            every { this@mockk.productName } returns  productName
        }

        every { productRepository.findByProductNo(productNo) } returns product

        val actual = productService.findProductDetail(productNo)!!

        assertEquals(product.productName, actual.productName)

    }

    @Test
    fun `상품 상세 조회하기 - 실패`(){
        every { productRepository.findByProductNo(any()) } returns null

        val exception = assertThrows<NotExistProduct> {
            productService.findProductDetail(1)
        }

        assertEquals(exception.errorCode, ProductErrorCode.NOT_EXIST)

    }

    @Test
    fun `상품 삭제하기`(){
        val productNo = 1

        every { productRepository.deleteByProductNo(productNo) } just runs

        productService.deleteProduct(productNo)

        verify(exactly = 1){
            productRepository.deleteByProductNo(productNo)
        }
    }

    @Test
    fun `상품 등록하기`(){
        val productName = "test"
        val salePrice = 1000
        val product = mockk<Product>(){
            every { this@mockk.productName } returns productName
        }

        every { productRepository.save(any()) } returns product

        val actual = productService.addProduct(productName, salePrice)

        verify {
            productRepository.save(any())
        }
        assertEquals(actual.productName, product.productName)
    }

    @Test
    fun `상품 수정하기`(){
        val productNo = 1
        val productName = "Test"
        val product = mockk<Product>(){
            every { this@mockk.productNo } returns productNo
        }
        val updatedProduct = mockk<ProductUpdateVo>(){
            every { this@mockk.productNo } returns productNo
            every { this@mockk.productName } returns productName
        }


        every { productRepository.findByProductNo(productNo) } returns product
        every { product.update(updatedProduct)  } just runs


        productService.updateProduct(updatedProduct)

        verify {
            productRepository.findByProductNo(productNo)
            product.update(updatedProduct)
        }
        assertEquals(updatedProduct.productName, productName)
    }
}
