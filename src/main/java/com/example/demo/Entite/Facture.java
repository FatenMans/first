package com.example.demo.Entite;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "factures")
public class Facture {

    @Id
    private String id;
    private String periode;
    private double montant;

    private String etatFacture;

    private  int numberOfDevices;

    @DBRef
    private Client client; // Lien vers le client associé à cette facture

    // Les constructeurs, getters et setters

    public Facture() {
    }

    public Facture(String periode, double montant,  String etatFacture) {
        this.periode = periode;
        this.montant = montant;

        this.etatFacture = etatFacture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }



    public String getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

