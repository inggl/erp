package com.github.inggl.erp.analysis.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AnalysisHandler {
    public Mono<ServerResponse> analysis(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.fromCallable(serverRequest::path), String.class);
    }
}
