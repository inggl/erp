package com.github.inggl.erp.order.entities

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Version

/**
 * A base entity
 * */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedBy
    @Column(name = "CREATED_BY", length = 100)
    open var createdBy: String? = "SYSTEM"

    @CreatedDate
    @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP")
    open var createdDate: Instant? = Instant.now()

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", length = 100)
    open var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", columnDefinition = "TIMESTAMP")
    open var lastModifiedDate: Instant? = null

    @Version
    @Column(name = "VERSION")
    open var version: Int? = null
}