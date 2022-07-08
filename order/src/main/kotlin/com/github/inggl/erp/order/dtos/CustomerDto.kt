package com.github.inggl.erp.order.dtos

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CustomerDto(
    @NotNull(message = "Customer ID is required")
    var id: UUID,

    @NotBlank(message = "Customer name is required")
    var name: String,

    @NotNull(message = "Customer address is required")
    var address: AddressDto
) : BaseDto()
