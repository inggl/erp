package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.AddressDto
import com.github.inggl.erp.order.entities.Address
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.time.*
import java.util.*

internal class AddressMapperTest {
    private lateinit var addressMapper: AddressMapper
    private lateinit var address: Address
    private lateinit var addressDto: AddressDto

    @BeforeEach
    fun init() {
        addressMapper = Mappers.getMapper(AddressMapper::class.java)
        address = Address(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855")

        addressDto = AddressDto(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855")
    }

    @Test
    fun toEntity_WhenDto_ThenConvertToEntity() {
        addressDto.createdBy = "TEST"
        addressDto.createdDate = LocalDateTime.of(LocalDate.of(2022, 1, 3), LocalTime.MIDNIGHT).toInstant(ZoneOffset.UTC)

        val address: Address = addressMapper.toEntity(addressDto)

        assertEquals(addressDto.id, address.id)
        assertEquals(addressDto.address, address.address)
        assertEquals(addressDto.createdBy, address.createdBy)
        assertEquals(addressDto.createdDate, address.createdDate)
    }

    @Test
    fun toDto_WhenEntity_ThenConvertToDto() {
        address.lastModifiedBy = "TEST"
        address.lastModifiedDate = LocalDateTime.of(LocalDate.of(2022, 1, 3), LocalTime.MIDNIGHT).toInstant(ZoneOffset.UTC)
        val addressDto: AddressDto = addressMapper.toDto(address)

        assertEquals(address.id, addressDto.id)
        assertEquals(address.address, addressDto.address)
        assertEquals(address.createdBy, addressDto.createdBy)
        assertEquals(address.createdDate, addressDto.createdDate)
        assertEquals(address.lastModifiedBy, addressDto.lastModifiedBy)
        assertEquals(address.lastModifiedDate, addressDto.lastModifiedDate)
    }

    @Test
    fun toEntities_WhenDtos_ThenConvertToEntities() {
        val addressDtos: List<AddressDto> = listOf(addressDto, addressDto)
        val addressEntities: List<Address> = addressMapper.toEntities(addressDtos)

        assertEquals(addressDtos.size, addressEntities.size)
    }

    @Test
    fun toDtos_WhenEntities_ThenConvertToDtos() {
        val addressList: List<Address> = listOf(address, address)
        val addressDtos: List<AddressDto> = addressMapper.toDtos(addressList)

        assertEquals(addressList.size, addressDtos.size)
    }
}