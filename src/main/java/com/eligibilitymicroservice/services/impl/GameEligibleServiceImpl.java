package com.eligibilitymicroservice.services.impl;

import com.eligibilitymicroservice.common.GameCreatedEvent;
import com.eligibilitymicroservice.common.GameEligibleEvent;
import com.eligibilitymicroservice.services.GameEligibleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GameEligibleServiceImpl implements GameEligibleService {

    @Override
    public Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent) {
        return null;

    }
}
