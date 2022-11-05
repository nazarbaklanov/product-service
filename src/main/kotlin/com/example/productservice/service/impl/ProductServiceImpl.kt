package com.example.productservice.service.impl

import com.example.productservice.dto.ProductRequestDto
import com.example.productservice.dto.ProductResponseDto
import com.example.productservice.model.ProductEntity
import com.example.productservice.repository.ProductRepository
import com.example.productservice.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val conversionService: ConversionService
) : ProductService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    override fun save(requestDto: ProductRequestDto): ProductResponseDto {
        logger.info("[SERVICE] save product to DB, requestDto: $requestDto")
        val product: ProductEntity = conversionService.convert(requestDto, ProductEntity::class.java)
            ?: throw RuntimeException("Error convert ProductResponseDto to Product")
        val saveProduct = productRepository.save(product)
        return conversionService.convert(saveProduct, ProductResponseDto::class.java)
            ?: throw RuntimeException("Error convert Product to ProductResponseDto")
    }

    override fun getAll(): List<ProductResponseDto> {
        logger.info("[SERVICE] findAll")
        val listProducts: List<ProductEntity> = productRepository.findAll()
        return listProducts.map {
            conversionService.convert(it, ProductResponseDto::class.java)
                ?: throw RuntimeException("Error convert")
        }
    }

    override fun findOneById(id: Long): ProductResponseDto {
        logger.info("[SERVICE] findOneById, ID: $id")
        val product: ProductEntity = productRepository.findById(id).orElseThrow {
            NoSuchElementException("Product with id=$id not found")
        }
        return conversionService.convert(product, ProductResponseDto::class.java)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "This product does not exist"
            )
    }

    override fun deleteById(id: Long) =
        productRepository.deleteById(id)

    override fun search(s: String, sort: Sort): List<ProductResponseDto> {
        val listProducts = productRepository.search(s, sort)
        return listProducts.map {
            conversionService.convert(it, ProductResponseDto::class.java)
                ?: throw RuntimeException("Error convert")
        }
    }
}
