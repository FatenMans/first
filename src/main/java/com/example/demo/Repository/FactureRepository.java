package com.example.demo.Repository;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.Facture;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FactureRepository extends MongoRepository<Facture,String> {


        List<Facture> findByClientMatriculeSociale(String matriculeClient);
        List<Facture> findByClientId(String id);

}
