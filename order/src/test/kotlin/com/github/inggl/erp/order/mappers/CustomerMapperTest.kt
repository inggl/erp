package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.AddressDto
import com.github.inggl.erp.order.dtos.CustomerDto
import com.github.inggl.erp.order.entities.Address
import com.github.inggl.erp.order.entities.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

internal class CustomerMapperTest {
    private lateinit var customerMapper: CustomerMapper
    private lateinit var customer: Customer
    private lateinit var customerDto: CustomerDto

    @BeforeEach
    fun init() {
        customerMapper = Mappers.getMapper(CustomerMapper::class.java)
        customer = Customer(UUID.randomUUID(), "User", Address(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855"))
        customerDto = CustomerDto(UUID.randomUUID(), "User", AddressDto(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855"))
    }

    @Test
    fun toEntity_WhenDto_ThenConvertToEntity() {
        val toCustomerEntity: Customer = customerMapper.toEntity(customerDto)

        assertEquals(customerDto.id, toCustomerEntity.id)
        assertEquals(customerDto.name, toCustomerEntity.name)
    }

    @Test
    fun toDto_WhenEntity_ThenConvertToDto() {
        val toCustomerDto: CustomerDto = customerMapper.toDto(customer)

        assertEquals(customer.id, toCustomerDto.id)
        assertEquals(customer.name, toCustomerDto.name)
    }

    @Test
    fun toEntities_WhenDtos_ThenConvertToEntities() {
        val customerDtos: List<CustomerDto> = listOf(customerDto, customerDto)
        val toCustomerEntities: List<Customer> = customerMapper.toEntities(customerDtos)

        assertEquals(customerDtos.size, toCustomerEntities.size)
    }

    @Test
    fun toDtos_WhenEntities_ThenConvertToDtos() {
        val customers: List<Customer> = listOf(customer, customer)
        val toCustomerDtos: List<CustomerDto> = customerMapper.toDtos(customers)

        assertEquals(customers.size, toCustomerDtos.size)
    }
}