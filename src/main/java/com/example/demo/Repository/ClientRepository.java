package com.example.demo.Repository;

import com.example.demo.Entite.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client,String> {



    Optional<Client> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Client findBymatriculeSociale(String matriculeClient);
}


