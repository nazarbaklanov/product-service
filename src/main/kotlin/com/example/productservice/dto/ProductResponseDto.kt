package com.example.productservice.dto

import java.math.BigDecimal

data class ProductResponseDto(
    var id: Long,
    var name: String,
    var price: BigDecimal,
    var cashback: BigDecimal,
    var image: String
)
