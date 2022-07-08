package com.github.inggl.erp.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@ConfigurationPropertiesScan("com.github.inggl.erp.order.properties")
class OrderApplication

fun main(args: Array<String>) {
    runApplication<OrderApplication>(*args)
}
