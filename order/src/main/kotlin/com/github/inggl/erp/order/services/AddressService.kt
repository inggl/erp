package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.AddressDto

interface AddressService {
    fun findAll(): List<AddressDto>
}