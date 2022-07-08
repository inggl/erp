package com.github.inggl.erp.order.entities

import javax.persistence.*

@Entity
@Table(name = "ORDER_ITEM")
class OrderItem(
    @EmbeddedId
    @AttributeOverrides(
        AttributeOverride(name = "orderId", column = Column(name = "ID")),
        AttributeOverride(name = "productId", column = Column(name = "ID"))
    )
    var id: OrderItemId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    var order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    var product: Product,

    @Column(name = "QUANTITY")
    var quantity: Int
) : BaseEntity()