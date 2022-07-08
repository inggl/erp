package com.github.inggl.erp.order.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "ADDRESS")
class Address(
    @Id
    @Column(name = "ID")
    var id: UUID,

    @Column(name = "ADDRESS", length = 100)
    var address: String,

    @Column(name = "ZIP", length = 5)
    @Size(min = 5, max = 5)
    var zip: String

) : BaseEntity()
