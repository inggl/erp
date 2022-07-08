package com.github.inggl.erp.order.dtos

import com.github.inggl.erp.order.enums.OrderStatus
import java.util.*
import javax.validation.constraints.NotNull

data class OrderDto(
    @NotNull(message = "Order ID is required")
    val id: UUID,

    @NotNull(message = "Order supplier is required")
    val supplier: SupplierDto,

    @NotNull(message = "Order status is required")
    val status: OrderStatus
) : BaseDto()
