package com.github.inggl.erp.order.entities

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PRODUCT")
class Product(
    @Id
    @Column(name = "ID")
    var id: UUID,

    @Column(name = "NAME", length = 100)
    var name: String,

    @Column(name = "DESCRIPTION", length = 250)
    var description: String
) : BaseEntity()
