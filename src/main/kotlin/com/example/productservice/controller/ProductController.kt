package com.example.productservice.controller

import com.example.productservice.dto.PaginationResponse
import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import com.example.productservice.service.ProductService
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
    @ApiOperation("Get list of all product from DB and sorting by sort: ASC or DESC")
    fun getAllProducts(
        @RequestParam("sort", defaultValue = "asc") sort: String,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("perPage", defaultValue = "10") perPage: Int
    ): ResponseEntity<Any> {
        var direction = Sort.unsorted()
        if (sort == "asc") {
            direction = Sort.by(Sort.Direction.ASC, "price")
        } else if (sort == "desc") {
            direction = Sort.by(Sort.Direction.DESC, "price")
        }
        logger.info("[CONTROLLER] Get all product from DB")
        val total = productService.countAll()
        return ResponseEntity.ok(
            PaginationResponse(
                data = productService.getAll(PageRequest.of(page, perPage, direction)),
                total,
                page,
                last_page = total / perPage
            )
        )
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
        @RequestParam("sort", defaultValue = "asc") sort: String,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("perPage", defaultValue = "10") perPage: Int
    ): ResponseEntity<Any> {
        var direction = Sort.unsorted()
        if (sort == "asc") {
            direction = Sort.by(Sort.Direction.ASC, "price")
        } else if (sort == "desc") {
            direction = Sort.by(Sort.Direction.DESC, "price")
        }
        val total = productService.countSearch(s)
        return ResponseEntity.ok(
            PaginationResponse(
                data = productService.search(s, PageRequest.of(page, perPage, direction)),
                total,
                page,
                last_page = total / perPage
            )
        )
    }
}
