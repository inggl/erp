package com.github.inggl.erp.order.entities

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class OrderItemId(
    var orderId: UUID,
    var productId: UUID


) : Serializable {
    override fun equals(other: Any?): Boolean {
        return Objects.equals(this, other)
    }

    override fun hashCode(): Int {
        return Objects.hash(orderId, productId)
    }
}