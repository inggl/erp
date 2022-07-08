package com.github.inggl.erp.order.dtos

import java.io.Serializable
import java.time.LocalDateTime

data class NotificationDto(
    val title: String,
    val message: String,
    val createdAt: LocalDateTime
) : Serializable
