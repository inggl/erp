package com.github.inggl.erp.order.specifications

import org.springframework.data.jpa.domain.Specification

class SpecificationBuilder<T> {
    private val params: MutableList<FilterCriteria> = mutableListOf()
    private val specifications: MutableList<Specification<T>> = mutableListOf()

    /**
     * Convert each filter criteria params and  specification to combined specification
     * */
    fun build(): Specification<T>? {
        var result: Specification<T>? = null

        if (params.isNotEmpty()) {
            result = GenericSpecification(params[0])

            params.drop(1).forEach {
                result = Specification.where(result).and(GenericSpecification(it))
            }
        }

        if (specifications.isNotEmpty()) {
            if (result == null) {
                result = specifications[0]
            } else {
                specifications.forEach {
                    result = Specification.where(result).and(it)
                }
            }
        }

        return result
    }

    fun with(filterCriteria: FilterCriteria): SpecificationBuilder<T> {
        params.add(filterCriteria)
        return this
    }

    fun with(filterCriteria: List<FilterCriteria>): SpecificationBuilder<T> {
        if (filterCriteria.isNotEmpty()) {
            filterCriteria.forEach {
                params.add(it)
            }
        }
        return this
    }

    fun with(specification: Specification<T>): SpecificationBuilder<T> {
        specifications.add(specification)
        return this
    }
}