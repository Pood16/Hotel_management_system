package repositories.memory;
import models.Client;
import repositories.ClientRepository;
import java.util.Map;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class InMemoryClientRepository implements ClientRepository {
    private Map<UUID, Client> users = new HashMap<>();
    
    @Override
    public void saveUser(Client client){
        users.put(client.getid(), client);
    };

    @Override
    public Optional<Client> findById(UUID id){
        return users.values().stream().filter(c -> c.getid().equals(id)).findFirst();
    }

    @Override
    public Optional<Client> findByEmail(String email){
        return users.values().stream().filter(c -> c.getEmail().equals(email)).findFirst();
    }

    @Override
    public List<Client> listAllUsers(){
        return new ArrayList<>(users.values());
    }


}