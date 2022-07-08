package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.NotificationDto
import reactor.core.publisher.Mono

interface NotificationService {
    fun send(title: String, message: String): Mono<NotificationDto>
}