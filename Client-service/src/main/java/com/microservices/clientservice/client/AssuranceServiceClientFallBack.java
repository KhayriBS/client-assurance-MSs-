package com.microservices.clientservice.client;

import com.microservices.clientservice.dto.AssuranceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class AssuranceServiceClientFallBack implements AssuranceClient {


    @Override
    public AssuranceDto getAssuranceById(Long id) {
        return AssuranceDto.builder()
                .assuranceType("SANTE")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .build();

    }
}

