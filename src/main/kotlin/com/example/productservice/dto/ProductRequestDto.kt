package com.example.productservice.dto

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class ProductRequestDto(
    @field:NotBlank(message = "Name of product must not be blank")
    val name: String,
    @field:Min(value = 0, message = "Price of product must not be negative")
    val price: BigDecimal,
    @field:Min(value = 0, message = "Cashback must not be negative")
    val cashback: BigDecimal,
    @field: NotBlank(message = "Image of product must not be blank")
    val image: String,
    val shippingCountryIds: Set<Long>
)