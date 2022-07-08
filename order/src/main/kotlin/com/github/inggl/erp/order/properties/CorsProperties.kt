package com.github.inggl.erp.order.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cors")
data class CorsProperties (
    val allowedOrigins: List<String> = listOf("http://localhost:3000", "http://localhost:4200"),
    val allowedHeaders: List<String> = listOf("*"),
    val allowedMethods: List<String> = listOf("*"),
)