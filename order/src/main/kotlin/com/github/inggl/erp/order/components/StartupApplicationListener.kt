package com.github.inggl.erp.order.components

import com.github.inggl.erp.order.services.NotificationService
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class StartupApplicationListener(private val notificationService: NotificationService) {
    @EventListener
    fun onApplicationEvent(event: ContextRefreshedEvent) {
        notificationService.send("Order Startup", "Order module started").subscribe()
    }
}