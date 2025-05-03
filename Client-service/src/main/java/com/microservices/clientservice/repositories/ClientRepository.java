package com.microservices.clientservice.repositories;

import com.microservices.clientservice.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByFirstName(String firstName);
}
