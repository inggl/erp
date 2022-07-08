package com.github.inggl.erp.order.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.inggl.erp.order.handlers.DashboardHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Configuration
class WebSocketConfig {
    @Bean
    fun webSocketHandlerAdapter(): WebSocketHandlerAdapter = WebSocketHandlerAdapter()

    @Bean
    fun webSocketHandlerMapping(objectMapper: ObjectMapper): HandlerMapping? {
        val urlMapHandlers = mapOf("/ws/dashboard" to DashboardHandler(objectMapper))

        val simpleUrlHandlerMapping: SimpleUrlHandlerMapping = SimpleUrlHandlerMapping().apply {
            urlMap = urlMapHandlers
            order = 1
        }

        return simpleUrlHandlerMapping
    }
}