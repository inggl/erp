package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.ProductDto
import com.github.inggl.erp.order.entities.Product
import com.github.inggl.erp.order.mappers.ProductMapper
import com.github.inggl.erp.order.repositories.ProductRepository
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
@CacheConfig(cacheNames = ["products"])
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        val productMapper: ProductMapper = Mappers.getMapper(ProductMapper::class.java)
    }

    override fun findAll(): List<ProductDto> {
        val products: List<Product> = productRepository.findAll()

        log.info("Found {} products", products.size)

        return productMapper.toDtos(products)
    }

    @Cacheable(key = "#productId")
    override fun findById(productId: UUID): Optional<ProductDto> {
        log.info("Getting product {}", productId)

        val productOptional: Optional<Product> = productRepository.findById(productId)

        if (productOptional.isPresent) {
            log.info("Found {} product", productOptional.get())

            return Optional.of(productMapper.toDto(productOptional.get()))
        }

        log.warn("Product {} not found", productId)

        return Optional.empty()
    }

    @CachePut
    override fun save(productDto: ProductDto): ProductDto {
        val product: Product = productRepository.save(productMapper.toEntity(productDto))

        log.info("Saved product {}", product)

        return productMapper.toDto(product)
    }

    @CachePut
    override fun update(productDto: ProductDto): ProductDto {
        val product: Product = productRepository.save(productMapper.toEntity(productDto))

        log.info("Updated product {}", product)

        return productMapper.toDto(product)
    }

    @CacheEvict(key = "#productId")
    override fun deleteById(productId: UUID) {
        productRepository.deleteById(productId)

        log.info("Deleted product {}", productId)
    }
}