package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.CustomerDto
import com.github.inggl.erp.order.entities.Customer
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper

@Mapper
interface CustomerMapper {
    fun toDto(customer: Customer): CustomerDto

    fun toEntity(customerDto: CustomerDto): Customer

    @IterableMapping(elementTargetType = CustomerDto::class)
    fun toDtos(customers: List<Customer>): List<CustomerDto>

    @IterableMapping(elementTargetType = Customer::class)
    fun toEntities(customerDtos: List<CustomerDto>): List<Customer>
}