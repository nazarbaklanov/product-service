package com.example.productservice.controller

import com.example.productservice.dto.ShippingCountryResponseDto
import com.example.productservice.service.ProductShippingCountiesService
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products/shipping-country")
class ShippingCountryController(
    private val productShippingCountiesService: ProductShippingCountiesService,
    private val conversionService: ConversionService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    @ApiOperation("Get all all shipping countries from DB")
    fun findAll(): Set<ShippingCountryResponseDto?> {
        logger.info("[CONTROLLER] Get all shipping countries from DB")
        return productShippingCountiesService.getAllShippingCountry()
            .map { conversionService.convert(
                it, ShippingCountryResponseDto::class.java
            ) }.toSet()
    }
}
