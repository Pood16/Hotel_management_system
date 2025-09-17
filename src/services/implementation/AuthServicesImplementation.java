package services.implementation;

import models.Client;
import repositories.ClientRepository;
import services.AuthService;

import java.util.Optional;

public class AuthServicesImplementation implements AuthService {

    private final ClientRepository clientRepository;

    public AuthServicesImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client register(String firstName, String lastName, String email, String password, boolean isAdmin){
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email Field is required");
        }
        if (clientRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("This Email is taken");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password Field is required");
        }
        Client client = new Client(firstName, lastName, email, password, true);
        clientRepository.saveUser(client);
        return client;
    }


    @Override
    public Optional<Client> login(String email, String password) {

        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email Field is Required");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password Field is Required");
        }

        Optional<Client> optionalClient = clientRepository.findByEmail(email.trim().toLowerCase());

        if (optionalClient.isPresent()){
            Client client = optionalClient.get();
            if (client.getPassword().equals(password)) {
                client.setConnected(true);
                return Optional.of(client);
            }else {
                throw new IllegalArgumentException("miss match Credentials");
            }
        }
        return Optional.empty();
    }


}
