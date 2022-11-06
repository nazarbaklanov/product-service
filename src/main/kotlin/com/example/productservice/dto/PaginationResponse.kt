package com.example.productservice.dto

data class PaginationResponse(
    val data: List<Any>,
    val total: Int,
    val page: Int,
    val last_page: Int
)