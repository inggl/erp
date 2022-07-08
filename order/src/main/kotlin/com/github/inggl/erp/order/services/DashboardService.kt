package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.DashboardDto
import reactor.core.publisher.Flux

interface DashboardService {
    fun findAll(): Flux<DashboardDto>
}