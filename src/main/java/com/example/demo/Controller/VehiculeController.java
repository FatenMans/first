package com.example.demo.Controller;
import com.example.demo.Entite.Appareil;
import com.example.demo.Entite.Vehicule;
import com.example.demo.Repository.ApparielRepository;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.VehiculeRepository;

import com.example.demo.Service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Vehicules")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;
    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private ApparielRepository appareilRepository;


    @PostMapping("/create")
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule) {
        Vehicule createdVehicule = vehiculeService.createVehicule(vehicule);
        return ResponseEntity.ok(createdVehicule);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        return ResponseEntity.ok(vehicules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable String id) {
        Optional<Vehicule> vehicule = vehiculeService.getVehiculeById(id);
        return vehicule.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Vehicule updateTutorial(@PathVariable("id") String id, @RequestBody Vehicule vehicule) {
        Optional<Vehicule> VehiculeData = vehiculeRepository.findById(id);

        if (VehiculeData.isPresent()) {
            Vehicule existingVehicule = VehiculeData.get();
            existingVehicule.setMarque(vehicule.getMarque());
            existingVehicule.setModele(vehicule.getModele());
            existingVehicule.setType(vehicule.getType());
            return vehiculeRepository.save(existingVehicule);
        } else {
            return null;
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable String id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/archiver/{id}")
    public String archiverVehicule(@PathVariable String id) {
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);

        if (vehicule != null) {
            vehicule.setEstArchive(true);
            vehiculeRepository.save(vehicule); // Cette ligne mettra à jour le compte dans la base de données
            return "Le compte client a été archivé avec succès.";
        } else {
            return "Le compte client n'existe pas.";
        }
    }





}



