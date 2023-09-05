package com.example.demo.Controller;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.Facture;
import com.example.demo.Service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {
@Autowired

    private final FactureService factureService;

    @Autowired
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    // Endpoint pour créer une nouvelle facture pour un client



    @PostMapping("/creer")
    public ResponseEntity<String> creerFacture(@RequestBody Facture facture, @RequestParam String matriculeSociale) {
        factureService.creerFacture(facture, matriculeSociale);
        return new ResponseEntity<>("Facture créée avec succès", HttpStatus.CREATED);
    }


    // Endpoint pour rechercher des factures par matricule client
    @GetMapping("/rechercher")
    public List<Facture> rechercherFacturesParMatriculeClient(@RequestParam String matriculeClient) {
        return factureService.rechercherFacturesParMatriculeClient(matriculeClient);
    }


}

