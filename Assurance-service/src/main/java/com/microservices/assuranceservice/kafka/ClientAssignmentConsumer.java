package com.microservices.assuranceservice.kafka;

import com.microservices.events.ClientAssignedToAssuranceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientAssignmentConsumer {
    private final com.microservices.assuranceservice.services.AssuranceService assuranceService;

    @KafkaListener(topics = "client.assigned.assurance", groupId = "assurance-service")
    public void consume(ClientAssignedToAssuranceEvent event) {
        log.info("Méthode consume appelée !");
        log.info("[KAFKA] clientId reçu = {}, assuranceId reçu = {}", event.getClientId(), event.getAssuranceId());
    }
}
