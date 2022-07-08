package com.github.inggl.erp.order.dtos

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class DashboardFilterDto @JsonCreator constructor(
    @JsonProperty("filter") var filter: String? = null
)
