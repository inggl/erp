package com.github.inggl.erp.order.controllers

import com.github.inggl.erp.order.dtos.AddressDto
import com.github.inggl.erp.order.services.AddressService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Address API
 * */
@RestController
class AddressController(private val addressService: AddressService) {
    /**
     * Get all addresses
     * */
    @GetMapping("/addresses")
    fun findAll(): ResponseEntity<List<AddressDto>> {
        return ResponseEntity.ok().body(addressService.findAll())
    }
}