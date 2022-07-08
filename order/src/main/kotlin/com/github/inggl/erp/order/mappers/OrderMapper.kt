package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.OrderDto
import com.github.inggl.erp.order.entities.Order
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper

@Mapper
interface OrderMapper {
    fun toDto(order: Order): OrderDto

    fun toEntity(orderDto: OrderDto): Order

    @IterableMapping(elementTargetType = OrderDto::class)
    fun toDtos(orderList: List<Order>): List<OrderDto>

    @IterableMapping(elementTargetType = Order::class)
    fun toEntities(orderDtoList: List<OrderDto>): List<Order>
}