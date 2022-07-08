package com.github.inggl.erp.order.entities

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document
class Dashboard(
    @Id
    var id: String
)