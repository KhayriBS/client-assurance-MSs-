package com.microservices.clientservice.services;

import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.dto.ClientWithAssuranceDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IClientService {
    ClientDTO add(ClientDTO clientDTO);

    ClientDTO update(String idClient, Map<Object,Object> fields);

    boolean delete(String idClient);

    Page<ClientDTO> getClients(int pageNbr, int pageSize);

    ClientDTO getClient (String id);

    ClientDTO getClientByFirstName(String FirstName);
    void addAssuranceIdToClient(String clientId, Long assuranceId);
    ClientWithAssuranceDto getClientWithAssurance(Long assuranceId);

}
