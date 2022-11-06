package com.example.productservice.model

import javax.persistence.*

@Entity
@Table(name = "shipping_countries")
class ShippingCountryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,
    val name: String
)
