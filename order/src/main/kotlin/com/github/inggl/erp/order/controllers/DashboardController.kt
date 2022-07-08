package com.github.inggl.erp.order.controllers

import com.github.inggl.erp.order.dtos.DashboardDto
import com.github.inggl.erp.order.services.DashboardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/dashboard")
class DashboardController(val dashboardService: DashboardService) {
    @GetMapping("/")
    fun findAll(): ResponseEntity<Flux<DashboardDto>> {
        return ResponseEntity.ok(dashboardService.findAll())
    }
}