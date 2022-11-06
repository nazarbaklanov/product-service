package com.example.productservice.service.impl

import com.example.productservice.exception.EntityNotFoundException
import com.example.productservice.model.ShippingCountryEntity
import com.example.productservice.repository.ProductShippingCountryRepository
import com.example.productservice.service.ProductShippingCountiesService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductShippingCountiesServiceImpl(
    private val productShippingCountryRepository: ProductShippingCountryRepository
) : ProductShippingCountiesService {

    override fun findShippingCountryById(id: Long): ShippingCountryEntity {
        return productShippingCountryRepository.findById(id).orElseThrow {
            EntityNotFoundException("ShippingCountry with id=$id not found")
        }
    }

    override fun getAllShippingCountry(pageRequest: PageRequest): Set<ShippingCountryEntity> {
        return productShippingCountryRepository.findAll(pageRequest).toSet()
    }
}
