package com.example.demo.Service;

import com.example.demo.Entite.Client;
import com.example.demo.Entite.Vehicule;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private VehiculeRepository vehiculeRepository;




    public Client createClient(Client client)
    {
        System.out.println(client.getEmail());
 return clientRepository.save(client);
    }
    public  Client getclient( String id)
    {

        return  clientRepository.findById(id).get();
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(String id) {
        return clientRepository.findById(id);
    }
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }



    public void ajouterVehiculeAuClient(String clientId, String vehiculeId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId).orElse(null);

        if (client != null && vehicule != null) {
            vehicule.setClient(client);
            client.getVehicules().add(vehicule);
            vehiculeRepository.save(vehicule);
            clientRepository.save(client);
        }
    }





}
