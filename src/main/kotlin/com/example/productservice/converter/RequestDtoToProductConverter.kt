package com.example.productservice.converter

import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.model.ProductEntity
import com.example.productservice.service.ProductShippingCountiesService
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class RequestDtoToProductConverter(
    private val productShippingCountiesService: ProductShippingCountiesService
) : Converter<ProductRequestDto, ProductEntity> {

    private val logger = LoggerFactory.getLogger(this::class.java)
    override fun convert(source: ProductRequestDto): ProductEntity {
        logger.info("[CONVERTER_RequestDtoToProductConverter] was called")
        return ProductEntity(
            name = source.name,
            price = source.price,
            cashback = source.cashback,
            image = source.image,
            shippingCountries = source.shippingCountryIds
                .map { productShippingCountiesService.findShippingCountryById(it) }
                .toSet()
        )
    }
}
