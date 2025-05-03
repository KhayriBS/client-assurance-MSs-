package com.microservices.assuranceservice.repositories;

import com.microservices.assuranceservice.entities.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
}
