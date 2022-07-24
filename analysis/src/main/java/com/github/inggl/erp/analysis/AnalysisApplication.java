package com.github.inggl.erp.analysis;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.github.inggl.erp.analysis.handlers.AnalysisHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class AnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalysisApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> analysis(AnalysisHandler analysisHandler) {
        return route().
                GET("/", accept(MediaType.APPLICATION_JSON), analysisHandler::analysis).build();
    }
}
