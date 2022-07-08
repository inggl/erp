package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.SupplierDto
import java.util.*

interface SupplierService {
    fun findAll(): List<SupplierDto>
    fun findById(supplierId: UUID): Optional<SupplierDto>
    fun save(supplierDto: SupplierDto): SupplierDto
    fun update(supplierDto: SupplierDto): SupplierDto
    fun deleteById(supplierId: UUID)
}