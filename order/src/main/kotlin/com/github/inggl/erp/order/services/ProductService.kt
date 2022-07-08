package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.ProductDto
import java.util.*

interface ProductService {
    fun findAll(): List<ProductDto>
    fun findById(productId: UUID): Optional<ProductDto>
    fun save(productDto: ProductDto): ProductDto
    fun update(productDto: ProductDto): ProductDto
    fun deleteById(productId: UUID)
}