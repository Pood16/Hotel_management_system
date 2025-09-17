package repositories;


import java.util.List;

import models.Client;

public interface ClientRepository {
    void saveUser(Client client);
    List<Client> listAllUsers();
}


