package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.AddressDto
import com.github.inggl.erp.order.dtos.OrderDto
import com.github.inggl.erp.order.dtos.SupplierDto
import com.github.inggl.erp.order.entities.Address
import com.github.inggl.erp.order.entities.Order
import com.github.inggl.erp.order.entities.Supplier
import com.github.inggl.erp.order.enums.OrderStatus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.util.*

internal class OrderMapperTest {
    private lateinit var orderMapper: OrderMapper
    private lateinit var order: Order
    private lateinit var orderDto: OrderDto

    @BeforeEach
    fun init() {
        orderMapper = Mappers.getMapper(OrderMapper::class.java)
        order = Order(
            UUID.randomUUID(),
            Supplier(UUID.randomUUID(), "User", Address(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855")),
            OrderStatus.COMPLETED
        )

        orderDto = OrderDto(
            UUID.randomUUID(),
            SupplierDto(
                UUID.randomUUID(),
                "User",
                AddressDto(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855")
            ), OrderStatus.DRAFT
        )
    }

    @Test
    fun toEntity_WhenDto_ThenConvertToEntity() {
        val toOrderEntity: Order = orderMapper.toEntity(orderDto)

        Assertions.assertEquals(orderDto.id, toOrderEntity.id)
    }

    @Test
    fun toDto_WhenEntity_ThenConvertToDto() {
        val toOrderDto: OrderDto = orderMapper.toDto(order)

        Assertions.assertEquals(order.id, toOrderDto.id)
    }

    @Test
    fun toEntities_WhenDtos_ThenConvertToEntities() {
        val orderDtos: List<OrderDto> = listOf(orderDto, orderDto)
        val toOrderEntities: List<Order> = orderMapper.toEntities(orderDtos)

        Assertions.assertEquals(orderDtos.size, toOrderEntities.size)
    }

    @Test
    fun toDtos_WhenEntities_ThenConvertToDtos() {
        val orders: List<Order> = listOf(order, order)
        val toOrderDtos: List<OrderDto> = orderMapper.toDtos(orders)

        Assertions.assertEquals(orders.size, toOrderDtos.size)
    }
}