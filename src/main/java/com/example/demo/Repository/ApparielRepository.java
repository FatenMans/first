package com.example.demo.Repository;

import com.example.demo.Entite.Appareil;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApparielRepository extends MongoRepository<Appareil,String> {
}
