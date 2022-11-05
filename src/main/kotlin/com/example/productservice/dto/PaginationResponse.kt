package com.example.productservice.dto

class PaginationResponse(
    val data: List<Any>,
    val total: Int,
    val page: Int,
    val last_page: Int
) {
}