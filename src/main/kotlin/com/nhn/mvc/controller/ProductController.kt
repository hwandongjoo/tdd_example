package com.nhn.mvc.controller

import com.nhn.mvc.model.*
import com.nhn.mvc.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping("/")
    fun getHome(): String = "main"

    @GetMapping("/product")
    fun getProductList(model: Model): String {
        model.addAttribute("productList", productService.findProductList())
        return "product"
    }

    @GetMapping("/product/{productNo}")
    fun getProductDetail(@PathVariable productNo: Int, model: Model): String {
        model.addAttribute("product", productService.findProductDetail(productNo))
        return "productDetail"
    }

    @GetMapping("/product/add")
    fun directAddProduct(): String = "addProduct"

    @PostMapping("/product/add")
    fun addProduct(productName: String, salePrice: Int): String {
        productService.addProduct(productName, salePrice)
        return "redirect:/product"
    }

    @GetMapping("/product/{productNo}/edit")
    fun directUpdatePage(@PathVariable productNo: Int, model: Model): String {
        model.addAttribute("product", productService.findProductDetail(productNo))
        return "editProduct"
    }

    @PutMapping("/product/edit")
    fun updateProduct(product: ProductUpdateVo): String {
        productService.updateProduct(product)
        return "redirect:/product"
    }

    @DeleteMapping("/product/{productNo}")
    fun deleteProduct(@PathVariable productNo: Int): String {
        productService.deleteProduct(productNo)
        return "redirect:/product"
    }
}
