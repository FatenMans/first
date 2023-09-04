package com.example.demo.Controller;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.EmailDetails;
import com.example.demo.Entite.Vehicule;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.VehiculeRepository;
import com.example.demo.Service.ClientService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VehiculeService vehiculeService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.ok(createdClient);
    }

    @GetMapping("/Allid")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Client updateTutorial(@PathVariable("id") String id, @RequestBody Client client) {
        Optional<Client> clientData = clientRepository.findById(id);

        if (clientData.isPresent()) {
            Client existingClient = clientData.get();
            existingClient.setRaisonSociale(client.getRaisonSociale());
            existingClient.setEmail(client.getEmail());
            existingClient.setNomComplet(client.getNomComplet());
            return clientRepository.save(existingClient);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sendActivationEmail/{id}")
    public String sendActivationEmail(@PathVariable String id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client != null) {
            // Activer le compte du client
            client.setEstActif(true);
            clientRepository.save(client);

            // Préparer les détails de l'email d'activation
            // Préparer les détails de l'email d'activation
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(client.getEmail()); // Adresse email du client
            emailDetails.setSubject("Activation du compte");
            emailDetails.setMsgBody("Votre compte a été activé avec succès.");

            // Envoyer l'email au client
            String emailStatus = emailService.sendSimpleMail(emailDetails);

            return "Le compte client a été activé et un email d'activation a été envoyé.";
        } else {
            return "Le compte client n'existe pas.";
        }
    }

    @PostMapping("/archiver/{id}")
    public String archiverClient(@PathVariable String id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client != null) {
            client.setEstArchive(true);
            clientRepository.save(client); // Cette ligne mettra à jour le compte dans la base de données
            return "Le compte client a été archivé avec succès.";
        } else {
            return "Le compte client n'existe pas.";
        }
    }

    @PostMapping("/generateCredentials/{id}")
    public String generateCredentials(@PathVariable String id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client != null) {
            // Générer le nom d'utilisateur et le mot de passe
            String generatedUsername = generateUsernameFromEmail(client.getEmail());
            String generatedPassword = generateRandomPassword();

            // Mettre à jour le client avec les nouvelles informations
            client.setUsername(generatedUsername);
            client.setPassword(generatedPassword);
            clientService.updateClient(client);

            return "Les informations d'identification ont été générées et mises à jour.";
        } else {
            return "Le compte client n'existe pas.";
        }

    }

    private String generateUsernameFromEmail(String email) {
        // Logique pour générer le nom d'utilisateur à partir de l'email
        // Par exemple, prenez le nom avant "@" dans l'adresse email
        String[] parts = email.split("@");
        if (parts.length > 0) {
            return parts[0];
        } else {
            return "defaultUsername";
        }
    }

    private String generateRandomPassword() {
        // Logique pour générer un mot de passe aléatoire sécurisé
        // Utilisez une bibliothèque de génération de mots de passe sécurisés
        // Par exemple, Spring Security propose BCrypt pour hacher les mots de passe
        // Voici un exemple simple (NE PAS utiliser en production) :
        return "GeneratedPassword123"; // Remplacez ceci par la logique réelle
    }




//    @PostMapping("/{VehiculeId}/clients")
//    public void ajouterVehiculeAuClient(@PathVariable String VehiculeId, @RequestBody Client client) {
//
//            clientService.ajouterClientAuVehicule(VehiculeId, client);
//
//
//    }


    @PostMapping("/{clientId}/add-vehicule/{vehiculeId}")
    public ResponseEntity<String> ajouterVehiculeAuClient(@PathVariable String clientId, @PathVariable String vehiculeId) {
        clientService.ajouterVehiculeAuClient(clientId, vehiculeId);
        return ResponseEntity.ok("Véhicule ajouté avec succès au client.");
    }




}
