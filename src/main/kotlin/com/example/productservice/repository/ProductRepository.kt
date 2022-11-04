package com.example.productservice.repository

import com.example.productservice.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {

    @Query("select * from products where name like %?1%", nativeQuery = true)
    fun search(s: String): List<ProductEntity>
}
