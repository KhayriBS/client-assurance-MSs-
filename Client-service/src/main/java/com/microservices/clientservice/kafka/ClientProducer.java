package com.microservices.clientservice.kafka;

import com.microservices.events.ClientAssignedToAssuranceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientProducer {
    private final KafkaTemplate<String, ClientAssignedToAssuranceEvent> kafkaTemplate;

    public void sendClientAssignedToAssurance(String clientId, Long assuranceId) {
        ClientAssignedToAssuranceEvent event = new ClientAssignedToAssuranceEvent(clientId, assuranceId);
        log.info("[KAFKA PRODUCER] Envoi de l'événement : clientId={}, assuranceId={}", clientId, assuranceId);
        kafkaTemplate.send("client.assigned.assurance", event);
    }
}
