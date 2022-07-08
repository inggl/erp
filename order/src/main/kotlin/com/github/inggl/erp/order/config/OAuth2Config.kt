package com.github.inggl.erp.order.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Profile("oauth2")
class OAuth2Config {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.httpBasic().disable()
            .formLogin().disable()
            .logout().disable()

        http.authorizeExchange { exchanges ->
            exchanges
                .pathMatchers(
                    "/actuator/**",
                    "/health",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/webjars/**",
                    "/graphiql",
                    "/vendor/graphiql/**"
                )
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer { customizer ->
                    customizer.jwt()
                }
        }

        return http.build()
    }
}