package com.example.demo.Controller;

import com.example.demo.Entite.Appareil;
import com.example.demo.Entite.Vehicule;
import com.example.demo.Repository.ApparielRepository;
import com.example.demo.Service.AppareilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appareils")
public class AppareilController {
@Autowired
    private ApparielRepository apparielRepository;
@Autowired
private AppareilService appareilService;


    @PostMapping("/createap")
    public Appareil createAppareil(@RequestBody Appareil appareil) {
        return apparielRepository.save(appareil); // Assurez-vous que l'appel à "save" est dans une méthode non statique
    }
    @GetMapping("/get/{id}")
    public Appareil getid(@PathVariable("id") String id)
    {
        return apparielRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public Appareil updateAppareil(@PathVariable String id, @RequestBody Appareil appareil) {
        Appareil existingAppareil = apparielRepository.findById(id).orElse(null);
        if (existingAppareil != null) {
            existingAppareil.setNumeroSerie(appareil.getNumeroSerie());
            existingAppareil.setType(appareil.getType());
            existingAppareil.setQuantiteStock(appareil.getQuantiteStock());
            return apparielRepository.save(existingAppareil);
        }
        return null;
    }
    @PutMapping("/{id}/archive")
    public Appareil archiveAppareil(@PathVariable String id) {
        Appareil appareil = apparielRepository.findById(id).orElse(null);
        if (appareil != null) {
            appareil.setArchived(true);
            return apparielRepository.save(appareil);
        }
        return null;
    }
    @PutMapping("/addstock/{id}")
    public Appareil addStock(@PathVariable String id, @RequestParam int quantity) {
        Appareil appareil = apparielRepository.findById(id).orElse(null);
        if (appareil != null) {
            appareil.setQuantiteEnStock(appareil.getQuantiteEnStock() + quantity);
            return apparielRepository.save(appareil);
        }
        return null;
    }

    @PutMapping("/removestock/{id}")
    public Appareil removeStock(@PathVariable String id, @RequestParam int quantity) {
        Appareil appareil = apparielRepository.findById(id).orElse(null);
        if (appareil != null) {
            int newStock = appareil.getQuantiteEnStock() - quantity;
            if (newStock >= 0) {
                appareil.setQuantiteEnStock(newStock);
                return apparielRepository.save(appareil);
            }
        }
        return null;
    }
    @PostMapping("/{idVehicule}/ajouter-appareil")
    public ResponseEntity<Vehicule> ajouterAppareil(@PathVariable String idVehicule, @RequestBody Appareil appareil) {
        Vehicule vehicule = appareilService.ajouterAppareil(idVehicule, appareil);
        if (vehicule != null) {
            return new ResponseEntity<>(vehicule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAppareilByclients/{idClient}")
public List<Appareil>getAppareilsByClient(@PathVariable String idClient)
    {
        return  appareilService.getAppareilsByClients(idClient);
    }
}



