package com.microservices.clientservice.mappers;

import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.dto.ClientWithAssuranceDto;
import com.microservices.clientservice.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    Client clientDTOToClient(ClientDTO clientDTO);
    ClientDTO clientToClientDTO(Client client);

}
