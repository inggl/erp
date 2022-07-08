package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.OrderDto
import com.github.inggl.erp.order.entities.Order
import com.github.inggl.erp.order.factories.SpecificationFactory
import com.github.inggl.erp.order.mappers.OrderMapper
import com.github.inggl.erp.order.repositories.OrderRepository
import com.github.inggl.erp.order.specifications.FilterCriteria
import com.github.inggl.erp.order.specifications.SpecificationBuilder
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class OrderServiceImpl(
    val orderRepository: OrderRepository,
    val specificationFactory: SpecificationFactory<Order>
) : OrderService {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun findAll(): List<OrderDto> {
        val orderMapper = Mappers.getMapper(OrderMapper::class.java)

        val orders = orderRepository.findAll()

        log.info("Found {} orders", orders.size)

        return orderMapper.toDtos(orders)
    }

    override fun findPage(pageable: Pageable): Page<OrderDto> {
        val orderMapper = Mappers.getMapper(OrderMapper::class.java)

        val findPage = orderRepository.findAll(pageable)

        val findPageDto = findPage.map {
            orderMapper.toDto(it)
        }

        return findPageDto
    }

    override fun findPage(pageable: Pageable, filters: List<FilterCriteria>): Page<OrderDto> {
        val orderMapper = Mappers.getMapper(OrderMapper::class.java)

        val specification = SpecificationBuilder<Order>()
            .with(filters)
            .build()

        val findPage = orderRepository.findAll(specification, pageable)

        val findPageDto = findPage.map {
            orderMapper.toDto(it)
        }

        return findPageDto
    }

    override fun count(): Long {

        val count = orderRepository.count()

        log.info("Orders total count", count)

        return count
    }

    override fun findById(orderId: UUID): Optional<OrderDto> {
        val orderMapper = Mappers.getMapper(OrderMapper::class.java)

        val orderOptional = orderRepository.findById(orderId)

        if (orderOptional.isPresent) {
            log.info("Found {} order", orderOptional.get())

            return Optional.of(orderMapper.toDto(orderOptional.get()))
        }

        log.warn("Order {} not found", orderId)

        return Optional.empty()
    }

    override fun save(orderDto: OrderDto): OrderDto {
        val orderMapper = Mappers.getMapper(OrderMapper::class.java)

        val order: Order = orderRepository.save(orderMapper.toEntity(orderDto))

        log.info("Saved order {}", order)

        return orderMapper.toDto(order)
    }

    override fun update(orderDto: OrderDto): OrderDto {
        val orderMapper = Mappers.getMapper(OrderMapper::class.java)

        val order: Order = orderRepository.save(orderMapper.toEntity(orderDto))

        log.info("Updated order {}", order)

        return orderMapper.toDto(order)
    }

    override fun deleteById(orderId: UUID) {
        orderRepository.deleteById(orderId)

        log.info("Deleted order {}", orderId)
    }
}