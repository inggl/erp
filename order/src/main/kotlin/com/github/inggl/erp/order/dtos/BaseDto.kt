package com.github.inggl.erp.order.dtos

import java.io.Serializable
import java.time.Instant
import java.time.LocalDateTime

/**
 * A base DTO
 * */
abstract class BaseDto : Serializable {
    open var createdBy: String? = null
    open var createdDate: Instant? = null
    open var lastModifiedBy: String? = null
    open var lastModifiedDate: Instant? = null
    open var version: Int? = null
}