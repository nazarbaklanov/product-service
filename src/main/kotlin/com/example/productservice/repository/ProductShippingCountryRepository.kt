package com.example.productservice.repository

import com.example.productservice.model.ShippingCountryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductShippingCountryRepository : JpaRepository<ShippingCountryEntity, Long>
