package com.microservices.clientservice.dto;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ClientWithAssuranceDto(String firstName, String lastName, String TypeAssurance , LocalDate startDate, LocalDate endDate) {
}
