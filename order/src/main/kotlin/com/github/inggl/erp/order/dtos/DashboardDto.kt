package com.github.inggl.erp.order.dtos

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class DashboardDto @JsonCreator constructor(
    @JsonProperty("total") var total: Int? = 0
)
