package com.example.productservice.service

import com.example.productservice.model.ShippingCountryEntity
import org.springframework.data.domain.PageRequest

interface ProductShippingCountiesService {

    fun findShippingCountryById(id: Long): ShippingCountryEntity

    fun getAllShippingCountry(pageRequest: PageRequest): Set<ShippingCountryEntity>
}