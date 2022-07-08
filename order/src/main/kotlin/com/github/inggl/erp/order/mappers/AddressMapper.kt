package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.AddressDto
import com.github.inggl.erp.order.entities.Address
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper

@Mapper
interface AddressMapper {
    fun toDto(address: Address): AddressDto

    fun toEntity(addressDto: AddressDto): Address

    @IterableMapping(elementTargetType = AddressDto::class)
    fun toDtos(addressList: List<Address>): List<AddressDto>

    @IterableMapping(elementTargetType = Address::class)
    fun toEntities(addressDtoList: List<AddressDto>): List<Address>
}