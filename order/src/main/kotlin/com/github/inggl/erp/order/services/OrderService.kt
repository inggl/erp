package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.OrderDto
import com.github.inggl.erp.order.specifications.FilterCriteria
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface OrderService {
    fun findAll(): List<OrderDto>
    fun findPage(pageable: Pageable): Page<OrderDto>
    fun findPage(pageable: Pageable, filters: List<FilterCriteria>): Page<OrderDto>
    fun findById(orderId: UUID): Optional<OrderDto>
    fun count(): Long
    fun save(orderDto: OrderDto): OrderDto
    fun update(orderDto: OrderDto): OrderDto
    fun deleteById(orderId: UUID)
}