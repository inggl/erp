package com.github.inggl.erp.order.config

import com.github.inggl.erp.order.constants.Queue.QUEUE_REPORT_FANOUT
import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpConfig {
    @Bean
    fun reportFanoutExchange(): FanoutExchange {
        return FanoutExchange(QUEUE_REPORT_FANOUT)
    }

    @Bean
    fun reportAutoDeleteQueue(): Queue {
        return AnonymousQueue()
    }

    @Bean
    fun reportFanoutBinding(): Binding {
        return BindingBuilder.bind(reportAutoDeleteQueue()).to(reportFanoutExchange())
    }
}