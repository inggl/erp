package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.ProductDto
import com.github.inggl.erp.order.entities.Product
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper

@Mapper
interface ProductMapper {
    fun toDto(product: Product): ProductDto

    fun toEntity(productDto: ProductDto): Product

    @IterableMapping(elementTargetType = ProductDto::class)
    fun toDtos(productList: List<Product>): List<ProductDto>

    @IterableMapping(elementTargetType = Product::class)
    fun toEntities(productDtoList: List<ProductDto>): List<Product>
}