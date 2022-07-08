package com.github.inggl.erp.order.dtos

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class AddressDto(
    @NotNull(message = "Address ID is required")
    var id: UUID,

    @NotBlank(message = "Address is required")
    var address: String,

    @Size(min = 5, max = 5, message = "ZIP code must be 5 numerical digits long")
    var zip: String
) : BaseDto()
