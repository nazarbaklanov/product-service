package com.example.productservice.service.impl

import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import com.example.productservice.model.ProductEntity
import com.example.productservice.repository.ProductRepository
import com.example.productservice.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val conversionService: ConversionService
) : ProductService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun save(requestDto: ProductRequestDto): ProductResponseDto? {
        try {
            logger.info("[SERVICE] save product to DB, requestDto: $requestDto")
            val product: ProductEntity = conversionService.convert(requestDto, ProductEntity::class.java)
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Product doesn't save")
            val productResponseDto = conversionService.convert(product, ProductResponseDto::class.java)
            ResponseEntity.status(HttpStatus.OK)
                .body(productRepository.save(product))
            return productResponseDto
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Input is not valid")
        }
    }

    override fun getAll(): List<ProductResponseDto> {
        logger.info("[SERVICE] findAll")
        val listProducts: List<ProductEntity> = productRepository.findAll()
        return listProducts.map {
            conversionService.convert(it, ProductResponseDto::class.java)
            ?: throw RuntimeException("Error convert")
        }
    }

    override fun findOneById(id: Long): ProductResponseDto? {
        logger.info("[SERVICE] findOneById, ID: $id")
        val product = productRepository.findOneById(id)
        return conversionService.convert(product, ProductResponseDto::class.java)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "This product does not exist"
            )
    }

    override fun deleteById(id: Long): ProductResponseDto {
        logger.info("[SERVICE] deleteById, ID: $id")
        try {
            val product = productRepository.findOneById(id)
            productRepository.deleteById(id)
            return conversionService.convert(product, ProductResponseDto::class.java)
                ?: throw NoSuchElementException("No products to delete")
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No products to delete")
        }
    }

    override fun search(s: String): List<ProductResponseDto> {
        val listProducts = productRepository.search(s)
        return listProducts.map { conversionService.convert(it, ProductResponseDto::class.java)
            ?: throw RuntimeException("Error convert")}

    }
}
