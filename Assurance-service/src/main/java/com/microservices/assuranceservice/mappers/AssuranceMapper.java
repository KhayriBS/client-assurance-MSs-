package com.microservices.assuranceservice.mappers;

import com.microservices.assuranceservice.dto.AssuranceDTO;
import com.microservices.assuranceservice.entities.Assurance;
import com.microservices.assuranceservice.enumm.AssuranceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AssuranceMapper {
    @Mapping(target = "assuranceType", source = "assuranceType", qualifiedByName = "mapEnumToString")
    AssuranceDTO assuranceToAssuranceDTO(Assurance assurance);

    @Mapping(target = "assuranceType", source = "assuranceType", qualifiedByName = "mapStringToEnum")
    Assurance assuranceDTOToAssurance(AssuranceDTO assuranceDTO);

    @Named("mapEnumToString")
    default String mapEnumToString(AssuranceType assuranceType) {
        return assuranceType != null ? assuranceType.name() : null;
    }

    @Named("mapStringToEnum")
    default AssuranceType mapStringToEnum(String assuranceType) {
        return assuranceType != null ? AssuranceType.valueOf(assuranceType) : null;
    }
}
