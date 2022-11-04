package com.example.productservice.converter

import com.example.productservice.dto.ShippingCountryResponseDto
import com.example.productservice.model.ShippingCountryEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ShippingCountryToShippingCountryResponseDto
    : Converter<ShippingCountryEntity, ShippingCountryResponseDto> {

    override fun convert(source: ShippingCountryEntity): ShippingCountryResponseDto? {
        return ShippingCountryResponseDto(
            id = source.id!!,
            name = source.name
        )
    }
}