package com.example.productservice.controller

import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import com.example.productservice.service.ProductService
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
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
    fun getProductById(@PathVariable id: Long): ProductResponseDto {
        logger.info("[CONTROLLER] Get product from DB by product ID = $id")
        return productService.findOneById(id)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete product from DM by product ID")
    fun deleteProductById(@PathVariable id: Long) {
        logger.info("[CONTROLLER] Delete product from DM by product ID = $id")
        productService.deleteById(id)
    }

    @GetMapping("/search")
    @ApiOperation(
        "Get list products, which name or description contains string: s, "
                + "and sorting by sort: ASC or DESC"
    )
    fun search(
        @RequestParam("s", defaultValue = "") s: String,
        @RequestParam("sort", defaultValue = "") sort: String
    ): List<ProductResponseDto> {
        var direction = Sort.unsorted()
        if (sort == "asc") {
            direction = Sort.by(Sort.Direction.ASC, "price")
        } else if (sort == "desc") {
            direction = Sort.by(Sort.Direction.DESC, "price")
        }
        return productService.search(s, direction)
    }
}
