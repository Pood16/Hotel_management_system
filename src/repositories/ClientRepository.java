package repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import models.Client;

public interface ClientRepository {
    void saveUser(Client client);
    Optional<Client> findById(UUID id);
    Optional<Client> findByEmail(String email);
    List<Client> listAllUsers();

}


