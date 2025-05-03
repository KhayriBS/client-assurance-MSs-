package com.microservices.assuranceservice.controllers;

import com.microservices.assuranceservice.dto.AssuranceDTO;
import com.microservices.assuranceservice.services.IAssuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assurances")
@RequiredArgsConstructor
public class AssuranceController {
    private final IAssuranceService assuranceService;

    @PostMapping
    public ResponseEntity<AssuranceDTO> createAssurance(@RequestBody AssuranceDTO assuranceDTO) {
        AssuranceDTO saved = assuranceService.add(assuranceDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssuranceDTO> updateAssurance(@PathVariable Long id, @RequestBody AssuranceDTO assuranceDTO) {
        AssuranceDTO updated = assuranceService.update(id, assuranceDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssurance(@PathVariable Long id) {
        boolean deleted = assuranceService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssuranceDTO> getAssuranceById(@PathVariable Long id) {
        AssuranceDTO assurance = assuranceService.getById(id);
        if (assurance != null) {
            return ResponseEntity.ok(assurance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<AssuranceDTO>> getAllAssurances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(assuranceService.getAll(page, size));
    }
    @PostMapping("/{assuranceId}/clients/{clientId}")
    public ResponseEntity<Void> addClientToAssurance(
            @PathVariable Long assuranceId,
            @PathVariable String clientId) {
        assuranceService.addClientIdToAssurance(assuranceId, clientId);
        return ResponseEntity.ok().build();
    }
}
