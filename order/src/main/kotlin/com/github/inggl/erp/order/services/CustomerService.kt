package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.CustomerDto
import java.util.*

interface CustomerService {
    fun findAll(): List<CustomerDto>
    fun findById(customerId: UUID): Optional<CustomerDto>
    fun save(customerDto: CustomerDto): CustomerDto
    fun update(customerDto: CustomerDto): CustomerDto
    fun deleteById(customerId: UUID)
}