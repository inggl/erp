package com.github.inggl.erp.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
@Profile("oauth2")
class OAuth2Config {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.httpBasic().disable()

        http.authorizeExchange { exchanges ->
            exchanges.pathMatchers("/api").authenticated().anyExchange().permitAll().and().oauth2Login(Customizer.withDefaults())
        }

        http.csrf().disable()
        return http.build()
    }
}