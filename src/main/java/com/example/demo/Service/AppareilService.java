package com.example.demo.Service;

import com.example.demo.Entite.Appareil;
import com.example.demo.Entite.Vehicule;
import com.example.demo.Repository.ApparielRepository;
import com.example.demo.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppareilService {

@Autowired
private ApparielRepository apparielRepository;
@Autowired
private VehiculeRepository vehiculeRepository;



    public Vehicule ajouterAppareil(String idVehicule, Appareil appareil) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule).orElse(null);
        if (vehicule != null) {
            vehicule.setAppareil(appareil);
            apparielRepository.save(appareil);
            vehiculeRepository.save(vehicule);
        }
        return vehicule;
    }
public List<Appareil> getAppareilsByClients(String idClient)
{
    List<Vehicule> vehicules=vehiculeRepository.findByClientId(idClient);
    List<Appareil> appareils=new ArrayList<>();
    for (Vehicule vehicule : vehicules)
    {
        appareils.add(vehicule.getAppareil());

    }
    return appareils;
}

}
