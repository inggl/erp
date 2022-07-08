package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.AddressDto
import com.github.inggl.erp.order.entities.Address
import com.github.inggl.erp.order.mappers.AddressMapper
import com.github.inggl.erp.order.repositories.AddressRepository
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddressServiceImpl(val addressRepository: AddressRepository) : AddressService {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        val addressMapper: AddressMapper = Mappers.getMapper(AddressMapper::class.java)
    }

    override fun findAll(): List<AddressDto> {
        val addressList: List<Address> = addressRepository.findAll()

        log.info("Found {} addresses", addressList.size)

        return addressMapper.toDtos(addressList)
    }
}