package com.microservices.clientservice.controllers;

import com.microservices.clientservice.dto.AssuranceDto;
import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.dto.ClientWithAssuranceDto;
import com.microservices.clientservice.mappers.ClientMapper;
import com.microservices.clientservice.services.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.add(clientDTO);
        return ResponseEntity.ok(savedClient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable String id, @RequestBody Map<Object, Object> fields) {
        ClientDTO updatedClient = clientService.update(id, fields);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        boolean deleted = clientService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> getAllClients(
            @RequestParam(defaultValue = "0") int pageNbr,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(clientService.getClients(pageNbr, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable String id) {
        ClientDTO client = clientService.getClient(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ClientDTO> getClientByFirstName(@RequestParam String firstName) {
        ClientDTO client = clientService.getClientByFirstName(firstName);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{clientId}/assurances/{assuranceId}")
    public ResponseEntity<Void> addAssuranceToClient(
            @PathVariable String clientId,
            @PathVariable Long assuranceId) {
        clientService.addAssuranceIdToClient(clientId, assuranceId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/assurances/{assuranceId}")
    public ResponseEntity<ClientWithAssuranceDto> getClientWithAssurance(@PathVariable Long assuranceId) {
        ClientWithAssuranceDto clientWithAssurance = clientService.getClientWithAssurance(assuranceId);
        return ResponseEntity.ok(clientWithAssurance);
    }

}
