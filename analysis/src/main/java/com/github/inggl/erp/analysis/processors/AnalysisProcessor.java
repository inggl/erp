package com.github.inggl.erp.analysis.processors;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnalysisProcessor {
    @Bean
    public Function<String, String> transform() {
        return String::toUpperCase;
    }
}
