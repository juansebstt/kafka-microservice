package com.eligibilitymicroservice.processors;

import com.eligibilitymicroservice.common.GameCreatedEvent;
import com.eligibilitymicroservice.common.GameEligibleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityGameProcessor {

    public Flux<GameEligibleEvent> process(Flux<GameCreatedEvent> gameCreatedEventFlux) {
        return gameCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .map()
    }

}
