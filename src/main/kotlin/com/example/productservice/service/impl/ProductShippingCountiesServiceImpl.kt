package com.example.productservice.service.impl

import com.example.productservice.model.ShippingCountryEntity
import com.example.productservice.repository.ProductShipingCountryRepository
import com.example.productservice.service.ProductShippingCountiesService
import org.springframework.stereotype.Service

@Service
class ProductShippingCountiesServiceImpl(
    private val productShippingCountryRepository: ProductShipingCountryRepository
) : ProductShippingCountiesService {

    override fun findShippingCountryById(id: Long): ShippingCountryEntity {
        return productShippingCountryRepository.findById(id).orElseThrow {
            RuntimeException("ShippingCountry with id=$id not found")
        }
    }

    override fun getAllShippingCountry(): Set<ShippingCountryEntity> {
        return productShippingCountryRepository.findAll().toSet()
    }
}