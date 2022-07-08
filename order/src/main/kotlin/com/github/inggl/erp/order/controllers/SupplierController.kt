package com.github.inggl.erp.order.controllers

import com.github.inggl.erp.order.dtos.SupplierDto
import com.github.inggl.erp.order.services.SupplierService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Supplier API
 * */
@RestController
@RequestMapping("/suppliers")
class SupplierController(private val supplierService: SupplierService) {

    /**
     * Get all suppliers
     * */
    @GetMapping("/")
    fun findAll(): ResponseEntity<List<SupplierDto>> =
        ResponseEntity.ok().body(supplierService.findAll())

    /**
     * Get supplier by supplier id
     * */
    @GetMapping("/{supplierId}")
    fun findById(@PathVariable supplierId: UUID): ResponseEntity<SupplierDto> {
        return supplierService.findById(supplierId).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    /**
     * Create supplier
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun save(@Valid @RequestBody supplierDto: SupplierDto): ResponseEntity<SupplierDto> =
        ResponseEntity.ok(supplierService.save(supplierDto))

    /**
     * Update supplier
     * */
    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@Valid @RequestBody supplierDto: SupplierDto): ResponseEntity<SupplierDto> {
        return supplierService.findById(supplierDto.id).map {
            ResponseEntity.ok().body(supplierService.update(supplierDto))
        }.orElse(ResponseEntity.notFound().build())
    }

    /**
     * Delete supplier by id
     * */
    @DeleteMapping("/{supplierId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteById(@PathVariable supplierId: UUID): ResponseEntity<Void> {
        return supplierService.findById(supplierId).map {
            supplierService.deleteById(supplierId)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}