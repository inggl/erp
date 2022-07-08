package com.github.inggl.erp.order.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Profile("!auth2")
class SecurityConfig {
    @Bean
    fun userDetailService(): MapReactiveUserDetailsService {
        val user = User("admin", "{noop}admin", listOf(SimpleGrantedAuthority("ADMIN")))
        return MapReactiveUserDetailsService(user)
    }

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.formLogin().disable().logout().disable()

        http.authorizeExchange { exchanges ->
            exchanges.anyExchange().permitAll().and().httpBasic(Customizer.withDefaults())
        }

        return http.build()
    }
}