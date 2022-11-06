package com.example.productservice.service

import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import org.springframework.data.domain.Pageable

interface ProductService {
    fun save(requestDto: ProductRequestDto): ProductResponseDto

    fun getAll(pageable: Pageable): List<ProductResponseDto>

    fun findOneById(id: Long): ProductResponseDto

    fun deleteById(id: Long)

    fun search(s: String, pageable: Pageable): List<ProductResponseDto>

    fun countSearch(s: String): Int

    fun countAll(): Int
}
