package com.eligibilitymicroservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {

    @Bean
    public Function<Flux<Game>, Flux<Game>> gameCreatedBinding() {

    }

}
