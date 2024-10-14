package com.eligibilitymicroservice.services;


import com.eligibilitymicroservice.common.GameCreatedEvent;
import com.eligibilitymicroservice.common.GameEligibleEvent;
import reactor.core.publisher.Mono;

public interface GameEligibleService {

    Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent);

}
