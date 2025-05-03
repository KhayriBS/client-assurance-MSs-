package com.microservices.assuranceservice.dto;
import java.time.LocalDate;

public record AssuranceDTO(Long id,
                           String assuranceType,
                           String clientId,
                           LocalDate startDate,
                           LocalDate endDate) {
}
