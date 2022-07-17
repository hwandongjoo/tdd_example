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

    }



    @Test
    fun `상품 상세 조회하기`(){


    }

    @Test
    fun `상품 상세 조회하기 - 실패`(){


    }

    @Test
    fun `상품 삭제하기`(){

    }

    @Test
    fun `상품 등록하기`(){

    }

    @Test
    fun `상품 수정하기`(){

    }
}
