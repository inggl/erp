package com.github.inggl.erp.order.dtos

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProductDto(
    @NotNull(message = "Product ID is required")
    val id: UUID,

    @NotBlank(message = "Product name is required")
    val name: String,

    @NotBlank(message = "Product description is required")
    val description: String
) : BaseDto()
