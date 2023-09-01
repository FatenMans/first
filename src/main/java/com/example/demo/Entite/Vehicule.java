package com.example.demo.Entite;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "vehicules")

public class Vehicule {
    @Id
    private String id;
    private String matricule;
    private String marque;
    private String modele;
    private String type;
    private int puissanceFiscale;
    private int anneeMiseEnCirculation;
    private String typeCarburant;
    private double qteCarburantEnLitre;
    private boolean estArchive;
    // Getters, setters, constructeurs


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public void setPuissanceFiscale(int puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }

    public int getAnneeMiseEnCirculation() {
        return anneeMiseEnCirculation;
    }

    public void setAnneeMiseEnCirculation(int anneeMiseEnCirculation) {
        this.anneeMiseEnCirculation = anneeMiseEnCirculation;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    public double getQteCarburantEnLitre() {
        return qteCarburantEnLitre;
    }

    public void setQteCarburantEnLitre(double qteCarburantEnLitre) {
        this.qteCarburantEnLitre = qteCarburantEnLitre;
    }

    public boolean isEstArchive() {
        return estArchive;
    }

    public void setEstArchive(boolean estArchive) {
        this.estArchive = estArchive;
    }

    @DBRef
    @JsonBackReference
    Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @DBRef
    private List<Appareil> appareils=new ArrayList<>();

    public List<Appareil> getAppareils() {
        return appareils;
    }

    public void setAppareils(List<Appareil> appareils) {
        this.appareils = appareils;
    }
}


