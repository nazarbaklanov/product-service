package com.example.productservice.service

import com.example.productservice.model.ShippingCountryEntity

interface ProductShippingCountiesService {

    fun findShippingCountryById(id: Long): ShippingCountryEntity

    fun getAllShippingCountry(): Set<ShippingCountryEntity>
}