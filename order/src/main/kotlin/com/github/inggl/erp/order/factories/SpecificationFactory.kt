package com.github.inggl.erp.order.factories

import com.github.inggl.erp.order.enums.FilterOperator
import com.github.inggl.erp.order.specifications.FilterCriteria
import com.github.inggl.erp.order.specifications.SpecificationBuilder
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class SpecificationFactory<T> {
    fun isEqual(key: String, value: Any): Specification<T>? {
        return SpecificationBuilder<T>().with(FilterCriteria(key, value, FilterOperator.EQ)).build()
    }

    fun contains(key: String, value: Any): Specification<T>? {
        return SpecificationBuilder<T>().with(FilterCriteria(key, value, FilterOperator.CONTAINS)).build()
    }
}