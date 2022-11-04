package com.example.productservice.controller

import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import com.example.productservice.model.ProductEntity
import com.example.productservice.service.ProductService
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/products")

class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping()
    @ApiOperation("Add a new product to DB")
    fun saveNewProduct(@RequestBody @Valid product: ProductRequestDto): ProductResponseDto? {
        logger.info("[CONTROLLER] Add a new product to DB, product: $product")
        return productService.save(product)
    }

    @GetMapping()
    @ApiOperation("Get list of all product from DB")
    fun getAllProducts(): List<ProductResponseDto> {
        logger.info("[CONTROLLER] Get all product from DB")
        return productService.getAll()
    }

    @GetMapping("{id}")
    @ApiOperation("Get product from DB by product ID")
    fun getProductById(@PathVariable id: Long): ProductResponseDto? {
        logger.info("[CONTROLLER] Get product from DB by product ID = $id")
        return productService.findOneById(id)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete product from DM by product ID")
    fun deleteProductById(@PathVariable id: Long): ProductResponseDto {
        logger.info("[CONTROLLER] Delete product from DM by product ID = $id")
        return productService.deleteById(id)
    }

    @GetMapping("/search")
    @ApiOperation("Get list products, which name contains string: s")
    fun search(
        @RequestParam("s", defaultValue = "") s: String
    ): List<ProductResponseDto> {
        return productService.search(s)
    }
}
