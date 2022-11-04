package com.example.productservice.converter

import com.example.productservice.model.ProductEntity
import com.example.productservice.dto.ProductRequestDto
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class RequestDtoToProductEntityConverter : Converter<ProductRequestDto, ProductEntity> {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun convert(source: ProductRequestDto): ProductEntity {
        logger.info("[CONVERTER_RequestDtoToProductEntityConverter] was called")
        return ProductEntity(
            name = source.name,
            price = source.price,
            cashback = source.cashback,
            image = source.image
        )
    }
}
