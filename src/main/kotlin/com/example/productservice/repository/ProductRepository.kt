package com.example.productservice.repository

import com.example.productservice.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {

    @Query("select p from ProductEntity p where p.name like %?1% or p.description like %?1%")
    fun search(s: String, sort: org.springframework.data.domain.Sort): List<ProductEntity>
}
