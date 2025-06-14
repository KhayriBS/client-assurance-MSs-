package com.microservices.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ClientAssignedToAssuranceEvent {
    private String clientId;
    private Long assuranceId;

    public ClientAssignedToAssuranceEvent(String clientId, Long assuranceId) {
        this.clientId = clientId;
        this.assuranceId = assuranceId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getAssuranceId() {
        return assuranceId;
    }

    public void setAssuranceId(Long assuranceId) {
        this.assuranceId = assuranceId;
    }
}
