package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.SupplierDto
import com.github.inggl.erp.order.entities.Supplier
import com.github.inggl.erp.order.mappers.SupplierMapper
import com.github.inggl.erp.order.repositories.SupplierRepository
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class SupplierServiceImpl(private val supplierRepository: SupplierRepository) : SupplierService {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        val supplierMapper: SupplierMapper = Mappers.getMapper(SupplierMapper::class.java)
    }

    override fun findAll(): List<SupplierDto> {
        val suppliers: List<Supplier> = supplierRepository.findAll()

        log.info("Found {} supplier", suppliers.size)

        return supplierMapper.toDtos(suppliers)
    }

    override fun findById(supplierId: UUID): Optional<SupplierDto> {
        val supplierOptional: Optional<Supplier> = supplierRepository.findById(supplierId)

        if (supplierOptional.isPresent) {
            log.info("Found {} supplier", supplierOptional.get())

            return Optional.of(supplierMapper.toDto(supplierOptional.get()))
        }

        log.warn("Supplier {} not found", supplierId)

        return Optional.empty()
    }

    override fun save(supplierDto: SupplierDto): SupplierDto {
        val supplier: Supplier = supplierRepository.save(supplierMapper.toEntity(supplierDto))

        log.info("Saved supplier {}", supplier)

        return supplierMapper.toDto(supplier)
    }

    override fun update(supplierDto: SupplierDto): SupplierDto {
        val supplier: Supplier = supplierRepository.save(supplierMapper.toEntity(supplierDto))

        log.info("Updated supplier {}", supplier)

        return supplierMapper.toDto(supplier)
    }

    override fun deleteById(supplierId: UUID) {
        supplierRepository.deleteById(supplierId)

        log.info("Deleted supplier {}", supplierId)
    }
}