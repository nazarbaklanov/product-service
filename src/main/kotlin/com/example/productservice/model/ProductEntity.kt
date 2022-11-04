package com.example.productservice.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    var name: String,
    var price: BigDecimal,
    var cashback: BigDecimal,
    var image: String
)
