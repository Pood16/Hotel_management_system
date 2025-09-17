package repositories.memory;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import models.Client;
import repositories.ClientRepository;

public class InMemoryClientRepository implements ClientRepository {
    Map<UUID, Client> users = new HashMap<>();
    
    @Override
    public void saveUser(Client client){
        users.put(client.getid(), client);
    };

    @Override
    public List<Client> listAllUsers(){
        return new ArrayList<>(users.values());
    }


   

}