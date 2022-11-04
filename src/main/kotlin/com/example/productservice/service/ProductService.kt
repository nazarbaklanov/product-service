package com.example.productservice.service

import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto

interface ProductService {
    fun save(requestDto: ProductRequestDto): ProductResponseDto

    fun getAll(): List<ProductResponseDto>

    fun findOneById(id: Long): ProductResponseDto

    fun deleteById(id: Long)

    fun search(s: String): List<ProductResponseDto>
}
