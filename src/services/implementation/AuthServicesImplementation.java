package services.implementation;

import models.Client;
import repositories.ClientRepository;
import services.AuthService;


import java.util.Optional;



public class AuthServicesImplementation implements AuthService {

    private final ClientRepository clientRepository;
    private  Client currentClient;

    public AuthServicesImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client register(String firstName, String lastName, String email, String password, boolean isAdmin){

        if (clientRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("This Email is taken");
        }

        Client client = new Client(firstName, lastName, email, password, true, isAdmin);
        clientRepository.saveUser(client);
        this.currentClient = client;
        return client;
    }


    @Override
    public Optional<Client> login(String email, String password) {
        Optional<Client> optionalClient = clientRepository.findByEmail(email);
        return optionalClient.filter(client -> client.getPassword().equals(password)).map(client -> {
            client.setConnected(true);
            return client;
        });

    }

    @Override
    public void logout(){
        Optional<Client> target = clientRepository.findById(currentClient.getid());
        target.ifPresent(client -> client.setConnected(false));
    }

    public void updatePassword(Client client, String password) {
        client.setPassword(password);
    }
    public void updateEmail(Client client, String email) {
        client.setEmail(email);
    }
}
