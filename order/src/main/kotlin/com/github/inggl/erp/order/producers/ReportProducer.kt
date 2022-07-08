package com.github.inggl.erp.order.producers

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.stereotype.Component

@Component
class ReportProducer(
    private val environment: Environment,
    private val rabbitTemplate: RabbitTemplate,
    val reportFanoutExchange: FanoutExchange
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun send(message: Any) {
        if (environment.acceptsProfiles(Profiles.of("amqp"))) {
            rabbitTemplate.convertAndSend(reportFanoutExchange.name, "", message)
        } else {
            log.warn("Amqp profile not active")
        }
    }
}