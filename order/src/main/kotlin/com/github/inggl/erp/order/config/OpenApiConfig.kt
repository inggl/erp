package com.github.inggl.erp.order.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.OAuthFlow
import io.swagger.v3.oas.annotations.security.OAuthFlows
import io.swagger.v3.oas.annotations.security.OAuthScope
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "ERP Order",
        version = "1.0",
        description = "ERP Order documentation APIs v.1.0"
    )
)
@SecurityScheme(
    name = "security_auth",
    type = SecuritySchemeType.OAUTH2,
    flows = OAuthFlows(
        authorizationCode = OAuthFlow(
            authorizationUrl = "\${spring.security.oauth2.resourceserver.jwt.issuer-uri:http://localhost:8080/auth/realms/erp}/protocol/openid-connect/auth",
            tokenUrl = "\${spring.security.oauth2.resourceserver.jwt.issuer-uri:http://localhost:8080/auth/realms/erp}/protocol/openid-connect/token",
            scopes = [OAuthScope(name = "openid")]
        )
    )
)
@SecurityScheme(name = "basic_auth", type = SecuritySchemeType.HTTP, scheme = "basic")
class OpenApiConfig {
}