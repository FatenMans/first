package com.example.demo.Service;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.Facture;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FactureService {
    @Autowired
    private  ClientRepository clientRepository;
    @Autowired

    private  FactureRepository factureRepository;



    public void creerFacture(String matriculeClient, String periode, double montant, int nbAppareils, String etatFacture) {
        // Récupérer le client en fonction du matricule
        Client client = clientRepository.findBymatriculeSociale(matriculeClient);

        if (client != null) {
            // Créer la facture et associer le client
            Facture facture = new Facture(periode, montant, nbAppareils, etatFacture);
            facture.setClient(client);

            // Enregistrer la facture dans la base de données
            factureRepository.save(facture);

        }
    }

    // Méthode pour rechercher des factures par matricule client
    public List<Facture> rechercherFacturesParMatriculeClient(String matriculeClient) {
        // Utilisez le FactureRepository pour rechercher des factures en fonction du matricule client
        return factureRepository.findByClientMatriculeSociale(matriculeClient);
    }
}

