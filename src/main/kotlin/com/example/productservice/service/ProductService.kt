package com.example.productservice.service

import com.example.productservice.model.ProductEntity
import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import org.springframework.http.ResponseEntity

interface ProductService {
    fun save(requestDto: ProductRequestDto): ProductResponseDto?

    fun getAll(): List<ProductResponseDto>

    fun findOneById(id: Long): ProductResponseDto?

    fun deleteById(id: Long): ProductResponseDto

    fun search(s: String): List<ProductResponseDto>
}
