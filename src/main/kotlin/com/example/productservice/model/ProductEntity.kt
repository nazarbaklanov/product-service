package com.example.productservice.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "products")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,
    val name: String,
    val price: BigDecimal,
    val cashback: BigDecimal,
    val image: String,
    val description: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "products_and_shipping_countries",
        joinColumns = [JoinColumn(name = "products_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "product_shipping_countries_id", referencedColumnName = "id")]
    )
    val shippingCountries: Set<ShippingCountryEntity>
)
