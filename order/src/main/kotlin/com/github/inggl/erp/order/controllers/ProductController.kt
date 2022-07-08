package com.github.inggl.erp.order.controllers

import com.github.inggl.erp.order.dtos.ProductDto
import com.github.inggl.erp.order.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Product API
 * */
@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    /**
     * Get all products
     * */
    @GetMapping("/")
    fun findAll(): ResponseEntity<List<ProductDto>> =
        ResponseEntity.ok().body(productService.findAll())

    /**
     * Get product by product id
     * */
    @GetMapping("/{productId}")
    fun findById(@PathVariable productId: UUID): ResponseEntity<ProductDto> {
        return productService.findById(productId).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    /**
     * Create product
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun save(@Valid @RequestBody productDto: ProductDto): ResponseEntity<ProductDto> =
        ResponseEntity.ok(productService.save(productDto))

    /**
     * Update product
     * */
    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@Valid @RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        return productService.findById(productDto.id).map {
            ResponseEntity.ok().body(productService.update(productDto))
        }.orElse(ResponseEntity.notFound().build())
    }

    /**
     * Delete product by id
     * */
    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteById(@PathVariable productId: UUID): ResponseEntity<Void> {
        return productService.findById(productId).map {
            productService.deleteById(productId)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}