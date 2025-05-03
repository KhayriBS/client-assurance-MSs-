package com.microservices.clientservice.client;

import com.microservices.clientservice.dto.AssuranceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "Assurance-service",url = "http://localhost:9098",fallback= AssuranceServiceClientFallBack.class)
public interface AssuranceClient {
    @GetMapping("/assurances/{id}")
    AssuranceDto getAssuranceById(@PathVariable("id") Long id);
}
