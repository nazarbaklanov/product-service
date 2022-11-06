package com.example.productservice.controller

import com.example.productservice.dto.PaginationResponse
import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import com.example.productservice.service.ProductService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
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
        @ApiParam(value = "ASC or DESC")
        @RequestParam("sort", defaultValue = "") sort: String,
        @ApiParam(value = "Number page for view, from 1")
        @RequestParam("page", defaultValue = "1") page: Int,
        @ApiParam(value = "Quantity items for view in one page, from 1")
        @RequestParam("perPage", defaultValue = "10") perPage: Int
    ): ResponseEntity<Any> {
        logger.info("[CONTROLLER] Get all product from DB")
        val direction = sort(sort)
        val total = productService.countAll()
        return ResponseEntity.ok(
            PaginationResponse(
                data = productService.getAll(PageRequest.of(page - 1, perPage, direction)),
                total,
                page,
                last_page = (total / perPage)
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
        @ApiParam(value = "String to filter products by name and description")
        @RequestParam("s", defaultValue = "") s: String,
        @ApiParam(value = "ASC or DESC")
        @RequestParam("sort", defaultValue = "") sort: String,
        @ApiParam(value = "Number page for view, from 1")
        @RequestParam("page", defaultValue = "1") page: Int,
        @ApiParam(value = "Quantity items for view in one page, from 1")
        @RequestParam("perPage", defaultValue = "10") perPage: Int
    ): ResponseEntity<Any> {
        logger.info("[CONTROLLER] Search product from DB")
        val direction = sort(sort)
        val total = productService.countSearch(s)
        return ResponseEntity.ok(
            PaginationResponse(
                data = productService.search(s, PageRequest.of(page - 1, perPage, direction)),
                total,
                page,
                last_page = (total / perPage)
            )
        )
    }

    private fun sort(sort: String): Sort {
        var direction = Sort.unsorted()
        if (sort == "asc") {
            direction = Sort.by(Sort.Direction.ASC, "price")
        } else if (sort == "desc") {
            direction = Sort.by(Sort.Direction.DESC, "price")
        }
        return direction
    }
}
