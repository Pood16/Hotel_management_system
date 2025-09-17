package repositories.memory;
import java.util.*;

import models.Hotel;
import repositories.HotelRepository;

public class InMemoryHotelRepository implements HotelRepository {
    Map<UUID, Hotel> hotels = new HashMap<>();

    @Override
    public void saveHotel(Hotel hotel){
        hotels.put(hotel.getid(), hotel);
    };

    @Override
    public Optional<Hotel> findById(UUID id){
        return hotels.values().stream().filter(h -> h.getid().equals(id)).findFirst();
    };

    @Override
    public List<Hotel> listAllHotels(){
        return new ArrayList<>(hotels.values());
    }

    @Override
    public void delete(UUID id){
        hotels.remove(id);
    }

}