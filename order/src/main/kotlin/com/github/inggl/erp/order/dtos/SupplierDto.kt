package com.github.inggl.erp.order.dtos

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SupplierDto(
    @NotNull(message = "Supplier ID is required")
    val id: UUID,

    @NotBlank(message = "Supplier name is required")
    val name: String,

    @NotNull(message = "Supplier address is required")
    val address: AddressDto
) : BaseDto()
