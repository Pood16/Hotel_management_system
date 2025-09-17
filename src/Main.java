import models.Client;
import repositories.ClientRepository;
import repositories.memory.InMemoryClientRepository;
import services.AuthService;
import services.implementation.AuthServicesImplementation;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ClientRepository clientRepository = new InMemoryClientRepository();
        AuthService authService = new AuthServicesImplementation(clientRepository);
        Client client = authService.register("Lahcen", "Ouirghane", "lahcen@gmail.com", "password 123", false);
        Optional<Client> optionalClient = authService.login("lahcen@gmail.com", "password 123");
        optionalClient.ifPresent(c -> System.out.println("Logged in as: " + c));
    }
}