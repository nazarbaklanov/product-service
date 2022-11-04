package com.example.productservice.dto

import java.math.BigDecimal

data class ProductResponseDto(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val cashback: BigDecimal,
    val image: String,
    val shippingCountryIds: Set<Long>
)
