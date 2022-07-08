package com.github.inggl.erp.order.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.inggl.erp.order.dtos.DashboardDto
import com.github.inggl.erp.order.dtos.DashboardFilterDto
import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import java.time.Duration

class DashboardHandler(private val objectMapper: ObjectMapper): WebSocketHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    private val sink = Sinks.many().replay().limit<DashboardDto>(100)
    private val response: Flux<DashboardDto> = sink.asFlux()

    override fun handle(session: WebSocketSession): Mono<Void> {
        log.info("Dashboard Web Socket handler")

        session.receive()
            .map { it.payloadAsText }
            .map {
                val dashboardFilterDto = Gson().fromJson(it, DashboardFilterDto::class.java)
                log.debug("Dashboard filter {}", dashboardFilterDto.toString())
                DashboardDto()
            }.subscribe({dashboard: DashboardDto -> sink.emitNext(dashboard, Sinks.EmitFailureHandler.FAIL_FAST)}, { error: Throwable -> sink.emitError(error, Sinks.EmitFailureHandler.FAIL_FAST)})

        return session.send(Mono.delay(Duration.ZERO).thenMany(response.map {
            session.textMessage(objectMapper.writeValueAsString(it))
        }))
    }
}