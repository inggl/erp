package com.github.inggl.erp.order.controllers

import com.github.inggl.erp.order.dtos.CustomerDto
import com.github.inggl.erp.order.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Customer API
 * */
@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    /**
     * Get all customers
     * */
    @GetMapping("/")
    fun findAll(): ResponseEntity<List<CustomerDto>> =
        ResponseEntity.ok().body(customerService.findAll())

    /**
     * Get customer by customer id
     * */
    @GetMapping("/{customerId}")
    fun findById(@PathVariable customerId: UUID): ResponseEntity<CustomerDto> {
        return customerService.findById(customerId).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    /**
     * Create customer
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun save(@Valid @RequestBody customerDto: CustomerDto): ResponseEntity<CustomerDto> =
        ResponseEntity.ok(customerService.save(customerDto))

    /**
     * Update customer
     * */
    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@Valid @RequestBody customerDto: CustomerDto): ResponseEntity<CustomerDto> {
        return customerService.findById(customerDto.id).map {
            ResponseEntity.ok().body(customerService.update(customerDto))
        }.orElse(ResponseEntity.notFound().build())
    }

    /**
     * Delete customer by id
     * */
    @DeleteMapping("/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteById(@PathVariable customerId: UUID): ResponseEntity<Void> {
        return customerService.findById(customerId).map {
            customerService.deleteById(customerId)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}