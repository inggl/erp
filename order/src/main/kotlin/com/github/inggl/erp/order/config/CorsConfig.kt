package com.github.inggl.erp.order.config

import com.github.inggl.erp.order.properties.CorsProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig(private val corsProperties: CorsProperties) {

    @Bean
    fun corsFilter(): CorsWebFilter {
        val config = CorsConfiguration()

        config.applyPermitDefaultValues()

        corsProperties.allowedOrigins.forEach {
            config.addAllowedOrigin(it)
        }

        corsProperties.allowedHeaders.forEach {
            config.addAllowedHeader(it)
        }

        corsProperties.allowedMethods.forEach {
            config.addAllowedMethod(it)
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", config)
        }
        return CorsWebFilter(source)
    }
}