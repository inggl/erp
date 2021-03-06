package com.github.inggl.erp.order.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    @LoadBalanced
    @Profile("discovery")
    fun webClientBuilder(): WebClient.Builder? {
        return WebClient.builder()
    }

    @Bean
    fun webClient(webClientBuilder: WebClient.Builder): WebClient? {
        return webClientBuilder.build()
    }
}