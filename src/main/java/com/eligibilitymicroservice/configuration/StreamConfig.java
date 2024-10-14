package com.eligibilitymicroservice.configuration;

import com.eligibilitymicroservice.common.GameCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {

    @Bean
    public Function<Flux<GameCreatedEvent>, Flux<GameCreatedEvent>> gameCreatedBinding() {
        return flux -> flux;
    }

}
