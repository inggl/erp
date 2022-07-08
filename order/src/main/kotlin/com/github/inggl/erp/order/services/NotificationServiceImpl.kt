package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.NotificationDto
import com.github.inggl.erp.order.producers.ReportProducer
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class NotificationServiceImpl(private val webClient: WebClient) :
    NotificationService {

    override fun send(title: String, message: String): Mono<NotificationDto> {
        require(title.isNotBlank()) {
            "Notification title must be not blank"
        }

        require(message.isNotBlank()) {
            "Notification message must be not blank"
        }

        return webClient.post().uri("http://localhost:8083/notifications").body(BodyInserters.fromValue(NotificationDto(title, message, LocalDateTime.now()))).exchangeToMono {
            it.bodyToMono(NotificationDto::class.java)
        }
    }
}