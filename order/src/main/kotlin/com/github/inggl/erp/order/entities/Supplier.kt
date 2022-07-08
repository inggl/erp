package com.github.inggl.erp.order.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "SUPPLIER")
class Supplier(
    @Id
    @Column(name = "ID")
    var id: UUID,

    @Column(name = "NAME", length = 100)
    var name: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ADDRESS_ID", foreignKey = ForeignKey(name = "SUPPLIER_ADDRESS_ADDRESS_ID_FK"))
    var address: Address
) : BaseEntity()