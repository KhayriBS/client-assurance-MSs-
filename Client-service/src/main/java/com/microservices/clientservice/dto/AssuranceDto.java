package com.microservices.clientservice.dto;
import lombok.Builder;
import java.time.LocalDate;
@Builder
public record AssuranceDto(
                           String assuranceType,
                           String clientId,
                           LocalDate startDate,
                           LocalDate endDate) {
}
