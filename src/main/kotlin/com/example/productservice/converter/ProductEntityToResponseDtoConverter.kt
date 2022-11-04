package com.example.productservice.converter

import com.example.productservice.model.ProductEntity
import com.example.productservice.dto.ProductResponseDto
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ProductEntityToResponseDtoConverter : Converter<ProductEntity, ProductResponseDto> {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun convert(source: ProductEntity): ProductResponseDto {
        logger.info("[CONVERTER_ProductEntityToResponseDtoConverter] was called")
        return ProductResponseDto(
            id = source.id ?: -1,
            name = source.name,
            price = source.price,
            cashback = source.cashback,
            image = source.image
        )
    }
}
