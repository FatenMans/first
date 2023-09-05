package com.example.demo.Repository;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.Vehicule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehiculeRepository extends MongoRepository<Vehicule, String> {

    Vehicule findByMarque(String marque);
    List<Vehicule> findByClientId(String clientid);


}
