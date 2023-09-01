package com.example.demo.Service;



import com.example.demo.Entite.Appareil;
import com.example.demo.Entite.Client;
import com.example.demo.Entite.Vehicule;
import com.example.demo.Repository.ApparielRepository;
import com.example.demo.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private ApparielRepository apparielRepository;


    public Vehicule createVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public Optional<Vehicule> getVehiculeById(String id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule updateVehicule(String id, Vehicule vehicule) {
        vehicule.setId(id); // Assurez-vous que l'ID du véhicule est correctement défini
        return vehiculeRepository.save(vehicule);
    }

    public void deleteVehicule(String id) {
        vehiculeRepository.deleteById(id);
    }

    public void ajouterVehicule(Client client, Vehicule vehicule) {
        vehicule.setClient(client); // Associer le client au véhicule
        vehiculeRepository.save(vehicule);
    }

    public Vehicule ajouterAppareil(String idVehicule, Appareil appareil) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule).orElse(null);
        if (vehicule != null) {
            vehicule.getAppareils().add(appareil);
            vehiculeRepository.save(vehicule);
        }
        return vehicule;
    }







        }

