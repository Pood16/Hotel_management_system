package services;

import models.Client;

import java.util.Optional;

public interface AuthService {
    Client register(String firstName, String lastName, String email, String password, boolean isAdmin);
    Optional<Client> login(String email, String password);
    Client logout();
    void updatePassword(Client client, String password);
    void updateEmail(Client client, String email);
}
