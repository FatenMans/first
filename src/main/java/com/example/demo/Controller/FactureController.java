package com.example.demo.Controller;

import com.example.demo.Entite.Facture;
import com.example.demo.Service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void creerFacture(@RequestParam String matriculeClient, @RequestParam String periode,
                             @RequestParam double montant, @RequestParam int nbAppareils,
                             @RequestParam String etatFacture) {
        factureService.creerFacture(matriculeClient, periode, montant, nbAppareils, etatFacture);
    }

    // Endpoint pour rechercher des factures par matricule client
    @GetMapping("/rechercher")
    public List<Facture> rechercherFacturesParMatriculeClient(@RequestParam String matriculeClient) {
        return factureService.rechercherFacturesParMatriculeClient(matriculeClient);
    }
}

