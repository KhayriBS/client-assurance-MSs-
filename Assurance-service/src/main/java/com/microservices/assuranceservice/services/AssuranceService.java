package com.microservices.assuranceservice.services;

import com.microservices.assuranceservice.dto.AssuranceDTO;
import com.microservices.assuranceservice.entities.Assurance;
import com.microservices.assuranceservice.mappers.AssuranceMapper;
import com.microservices.assuranceservice.repositories.AssuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssuranceService implements IAssuranceService {
    private final AssuranceRepository assuranceRepository;
    private final AssuranceMapper assuranceMapper;

    @Override
    public AssuranceDTO add(AssuranceDTO assuranceDTO) {
        Assurance assurance = assuranceMapper.assuranceDTOToAssurance(assuranceDTO);
        Assurance saved = assuranceRepository.save(assurance);
        return assuranceMapper.assuranceToAssuranceDTO(saved);
    }

    @Override
    public AssuranceDTO update(Long id, AssuranceDTO assuranceDTO) {
        Optional<Assurance> optionalAssurance = assuranceRepository.findById(id);
        if (optionalAssurance.isPresent()) {
            Assurance assurance = assuranceMapper.assuranceDTOToAssurance(assuranceDTO);
            assurance.setId(id);
            Assurance updated = assuranceRepository.save(assurance);
            return assuranceMapper.assuranceToAssuranceDTO(updated);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (assuranceRepository.existsById(id)) {
            assuranceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AssuranceDTO getById(Long id) {
        return assuranceRepository.findById(id)
                .map(assuranceMapper::assuranceToAssuranceDTO)
                .orElse(null);
    }

    @Override
    public Page<AssuranceDTO> getAll(int page, int size) {
        return assuranceRepository.findAll(PageRequest.of(page, size))
                .map(assuranceMapper::assuranceToAssuranceDTO);
    }

    @Override
    @Transactional
    public void addClientIdToAssurance(Long assuranceId, String clientId) {
        Assurance assurance = assuranceRepository.findById(assuranceId)
                .orElseThrow(() -> new RuntimeException("Assurance not found"));
        log.info("Avant update : {}", assurance);
        assurance.setClientId(clientId);
        Assurance updated = assuranceRepository.save(assurance);
        log.info("Après update : {}", updated);
    }
}
