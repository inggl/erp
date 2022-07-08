package com.github.inggl.erp.order.specifications

import com.github.inggl.erp.order.enums.FilterOperator
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class GenericSpecification<T>(private val filterCriteria: FilterCriteria) : Specification<T> {
    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        return when (filterCriteria.operator) {
            FilterOperator.EQ -> criteriaBuilder.equal(root.get<T>(filterCriteria.key), filterCriteria.value)
            else -> null
        }
    }
}