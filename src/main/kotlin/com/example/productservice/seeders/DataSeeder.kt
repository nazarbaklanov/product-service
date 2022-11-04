package com.example.productservice.seeders

import com.example.productservice.model.ProductEntity
import com.example.productservice.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class DataSeeder(private val productRepository: ProductRepository): ApplicationRunner {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun run(args: ApplicationArguments?) {
        logger.info("[DATA_SEEDER] added 50 products to DB")
        for (i in 1..50) {
            val product = ProductEntity(
            name = "Apple iPhone #" + i,
            price = BigDecimal(Random().nextInt(25000, 100000)),
            cashback = BigDecimal(Random().nextInt(0, 1000)),
            image = "http://lorempixel.com/200/200?" + Random().nextInt(10000)
            )
            productRepository.save(product)
        }
    }
}
