package com.example.productservice.seeders

import com.example.productservice.model.ProductEntity
import com.example.productservice.model.ShippingCountryEntity
import com.example.productservice.repository.ProductRepository
import com.example.productservice.repository.ProductShippingCountryRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class DataSeeder(
    private val productRepository: ProductRepository,
    private val shippingRepository: ProductShippingCountryRepository
) : ApplicationRunner {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun run(args: ApplicationArguments?) {
        logger.info("[DATA_SEEDER] added 50 products to DB")

        for (i in 1..5) {
            val country = ShippingCountryEntity(
                name = "USA" + i
            )
            shippingRepository.save(country)
        }

        for (i in 1..5) {
            val country = ShippingCountryEntity(
                name = "Germany" + i
            )
            shippingRepository.save(country)
        }

        for (i in 1..5) {
            val country = ShippingCountryEntity(
                name = "Ukraine" + i
            )
            shippingRepository.save(country)
        }

        for (i in 1..50) {
            val usa = Random().nextLong(1, 5)
            val ger = Random().nextLong(1, 5)
            val ua = Random().nextLong(1, 5)
            val product = ProductEntity(
                name = "Apple iPhone #" + i,
                price = BigDecimal(Random().nextInt(25000, 100000)),
                cashback = BigDecimal(Random().nextInt(0, 1000)),
                image = "http://lorempixel.com/200/200?" + Random().nextInt(10000),
                description = "Description #" + i + 1,
                shippingCountries = listOf(
                    ShippingCountryEntity(id = usa, name = shippingRepository.findById(usa).get().name),
                    ShippingCountryEntity(id = ger, name = shippingRepository.findById(ger).get().name),
                    ShippingCountryEntity(id = ua, name = shippingRepository.findById(ua).get().name)
                ).toSet()
            )
            productRepository.save(product)
        }
    }
}
