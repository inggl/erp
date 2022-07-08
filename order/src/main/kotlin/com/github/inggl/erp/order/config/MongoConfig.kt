package com.github.inggl.erp.order.config

import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate

@Configuration
class MongoConfig(val mongoClient: MongoClient) {

    @Bean
    fun dashboardReactiveMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(mongoClient, "dashboard")
    }
}