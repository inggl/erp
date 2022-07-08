package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.CustomerDto
import com.github.inggl.erp.order.entities.Customer
import com.github.inggl.erp.order.mappers.CustomerMapper
import com.github.inggl.erp.order.repositories.CustomerRepository
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        val customerMapper: CustomerMapper = Mappers.getMapper(CustomerMapper::class.java)
    }

    override fun findAll(): List<CustomerDto> {
        val customers: List<Customer> = customerRepository.findAll()

        log.info("Found {} customers", customers.size)

        return customerMapper.toDtos(customers)
    }

    override fun findById(customerId: UUID): Optional<CustomerDto> {
        val customerOptional: Optional<Customer> = customerRepository.findById(customerId)

        if (customerOptional.isPresent) {
            log.info("Found {} customer", customerOptional.get())

            return Optional.of(customerMapper.toDto(customerOptional.get()))
        }

        log.warn("Customer {} not found", customerId)

        return Optional.empty()
    }

    override fun save(customerDto: CustomerDto): CustomerDto {
        val customer: Customer = customerRepository.save(customerMapper.toEntity(customerDto))

        log.info("Saved customer {}", customer)

        return customerMapper.toDto(customer)
    }

    override fun update(customerDto: CustomerDto): CustomerDto {
        val customer: Customer = customerRepository.save(customerMapper.toEntity(customerDto))

        log.info("Updated customer {}", customer)

        return customerMapper.toDto(customer)
    }

    override fun deleteById(customerId: UUID) {
        customerRepository.deleteById(customerId)

        log.info("Deleted customer {}", customerId)
    }
}