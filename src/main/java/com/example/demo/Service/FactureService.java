package com.example.demo.Service;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.Facture;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class FactureService {
    @Autowired
    private  ClientRepository clientRepository;
    @Autowired

    private  FactureRepository factureRepository;

    public  String creerFacture(Facture facture , String matriculeClient ) {

         Client client =clientRepository.findBymatriculeSociale(matriculeClient);
         if (client != null)
         {
             facture.setClient(client);
             factureRepository.save(facture);
             return "facture cree avec success";
         }
        return "client not found";
    }



    // Méthode pour rechercher des factures par matricule client
    public List<Facture> rechercherFacturesParMatriculeClient(String matriculeClient) {
        Client client = clientRepository.findBymatriculeSociale(matriculeClient);
        return  factureRepository.findByClientId(client.getId());

    }


}

