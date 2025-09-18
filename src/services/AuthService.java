package services;

import models.Client;

import java.util.Optional;

public interface AuthService {
    Client register(String firstName, String lastName, String email, String password, boolean isAdmin);
    Optional<Client> login(String email, String password);
    void logout();
}
