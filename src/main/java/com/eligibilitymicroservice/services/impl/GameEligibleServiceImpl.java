package com.eligibilitymicroservice.services.impl;

import com.eligibilitymicroservice.common.GameCreatedEvent;
import com.eligibilitymicroservice.common.GameEligibleEvent;
import com.eligibilitymicroservice.services.GameEligibleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class GameEligibleServiceImpl implements GameEligibleService {

    @Override
    public Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> GameEligibleEvent.builder()
                        .id(givenCreated.getId())
                        .name(givenCreated.getName())
                        .userId(givenCreated.getUserId())
                        .isEligible(true)
                        .build());

    }

    private Mono<GameCreatedEvent> checkIsEligible(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .filter(Objects::nonNull)
                .map(given -> gameCreatedEvent);
    }
}
