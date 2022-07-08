package com.github.inggl.erp.order.entities

import com.github.inggl.erp.order.enums.OrderStatus
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ORDER")
class Order(
    @Id
    @Column(name = "ID")
    var id: UUID,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "SUPPLIER_ID", foreignKey = ForeignKey(name = "ORDER_SUPPLIER_SUPPLIER_ID_FK"))
    var supplier: Supplier,

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    var status: OrderStatus
) : BaseEntity()