package com.example.demo.Repository;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.Vehicule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculeRepository extends MongoRepository<Vehicule, String> {

    Vehicule findByMarque(String marque);


}
