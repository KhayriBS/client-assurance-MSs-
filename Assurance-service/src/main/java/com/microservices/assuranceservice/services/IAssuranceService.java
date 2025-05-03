package com.microservices.assuranceservice.services;

import com.microservices.assuranceservice.dto.AssuranceDTO;
import org.springframework.data.domain.Page;

public interface IAssuranceService {
    AssuranceDTO add(AssuranceDTO assuranceDTO);

    AssuranceDTO update(Long id, AssuranceDTO assuranceDTO);

    boolean delete(Long id);

    AssuranceDTO getById(Long id);

    Page<AssuranceDTO> getAll(int page, int size);
    void addClientIdToAssurance(Long assuranceId, String clientId);

}
