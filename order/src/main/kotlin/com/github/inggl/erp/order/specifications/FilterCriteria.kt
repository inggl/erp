package com.github.inggl.erp.order.specifications

import com.github.inggl.erp.order.enums.FilterOperator

data class FilterCriteria(
    val key: String,
    val value: Any?,
    val operator: FilterOperator
)
