package com.microservices.clientservice.services;

import com.microservices.clientservice.client.AssuranceClient;
import com.microservices.clientservice.dto.AssuranceDto;
import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.dto.ClientWithAssuranceDto;
import com.microservices.clientservice.entities.Client;
import com.microservices.clientservice.mappers.ClientMapper;
import com.microservices.clientservice.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AssuranceClient assuranceClient;


    @Override
    public ClientDTO add(ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOToClient(clientDTO);
        clientRepository.save(client);
        return clientMapper.clientToClientDTO(client);
    }

    @Override
    public ClientDTO update(String idClient, Map<Object, Object> fields) {
        Optional<Client> clientOptional = clientRepository.findById(idClient);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Client.class, (String) key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, client, value);
                }
            });
            clientRepository.save(client);
            return clientMapper.clientToClientDTO(client);
        }
        return null;
    }

    @Override
    public boolean delete(String idClient) {
        if (clientRepository.existsById(idClient)) {
            clientRepository.deleteById(idClient);
            return true;
        }
        return false;
    }

    @Override
    public Page<ClientDTO> getClients(int pageNbr, int pageSize) {
        return clientRepository.findAll(PageRequest.of(pageNbr, pageSize))
                .map(clientMapper::clientToClientDTO);
    }

    @Override
    public ClientDTO getClient(String id) {
        return clientRepository.findById(id)
                .map(clientMapper::clientToClientDTO)
                .orElse(null);
    }

    @Override
    public ClientDTO getClientByFirstName(String firstName) {
        Client client = clientRepository.findByFirstName(firstName);
        return client != null ? clientMapper.clientToClientDTO(client) : null;
    }
    @Override
    public void addAssuranceIdToClient(String clientId, Long assuranceId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
         client.setAssuranceId(assuranceId);
            clientRepository.save(client);
        }

public ClientWithAssuranceDto getClientWithAssurance(Long assuranceId) {
    AssuranceDto assurance = assuranceClient.getAssuranceById(assuranceId);
    System.out.println("ASSURANCE FEIGN CLIENT ==> " + assurance);

    String firstName = "Foulen";
    String lastName = "BEN FOULEN";

    if (assurance.clientId() != null) {
        Client client = clientRepository.findById(assurance.clientId()).orElse(null);
        if (client != null) {
            firstName = client.getFirstName();
            lastName = client.getLastName();
        }
    }

    return ClientWithAssuranceDto.builder()
            .firstName(firstName)
            .lastName(lastName)
            .TypeAssurance(assurance.assuranceType())
            .startDate(assurance.startDate())
            .endDate(assurance.endDate())
            .build();}}

