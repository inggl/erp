package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.SupplierDto
import com.github.inggl.erp.order.entities.Supplier
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper

@Mapper
interface SupplierMapper {
    fun toDto(supplier: Supplier): SupplierDto

    fun toEntity(supplierDto: SupplierDto): Supplier

    @IterableMapping(elementTargetType = SupplierDto::class)
    fun toDtos(supplierList: List<Supplier>): List<SupplierDto>

    @IterableMapping(elementTargetType = Supplier::class)
    fun toEntities(supplierDtoList: List<SupplierDto>): List<Supplier>
}