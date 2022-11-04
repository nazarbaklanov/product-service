package com.example.productservice.dto

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class ProductRequestDto(
    @field:NotBlank(message = "Name of product must not be blank")
    var name: String,
    @field:Min(value = 0, message = "Price of product must not be negative")
    var price: BigDecimal,
    @field:Min(value = 0, message = "Cashback must not be negative")
    var cashback: BigDecimal,
    @field: NotBlank(message = "Image of product must not be blank")
    var image: String
)