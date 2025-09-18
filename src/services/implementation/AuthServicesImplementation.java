package services.implementation;

import models.Client;
import repositories.ClientRepository;
import services.AuthService;
import utils.InputValidator;

import java.util.Optional;

public class AuthServicesImplementation implements AuthService {

    private final ClientRepository clientRepository;
    private  Client currentClient;

    public AuthServicesImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client register(String firstName, String lastName, String email, String password, boolean isAdmin){
         
        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Please Respect the email format");
        }

        if (clientRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("This Email is taken");
        }
        if (!InputValidator.isValidPassword(password)) {
            throw new IllegalArgumentException("Password Field is required");
        }
        Client client = new Client(firstName, lastName, email, password, true);
        clientRepository.saveUser(client);
        this.currentClient = client;
        return client;
    }


    @Override
    public Optional<Client> login(String email, String password) {

        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Email Field is Required");
        }

        if (!InputValidator.isValidPassword(password)) {
            throw new IllegalArgumentException("Password Field is Required");
        }

        Optional<Client> optionalClient = clientRepository.findByEmail(email.trim().toLowerCase());

        if (optionalClient.isPresent()){
            Client client = optionalClient.get();
            if (client.getPassword().equals(password)) {
                client.setConnected(true);
                this.currentClient = client;
                return Optional.of(client);
            }else {
                throw new IllegalArgumentException("miss match Credentials");
            }
        }
        return Optional.empty();
    }

    @Override
    public void logout(){
        Optional<Client> target = clientRepository.findById(currentClient.getid());
        target.ifPresent(client -> client.setConnected(false));
    }
}
