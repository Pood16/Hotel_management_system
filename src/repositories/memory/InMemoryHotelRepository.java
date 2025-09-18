package repositories.memory;
import java.util.*;

import models.Hotel;
import repositories.HotelRepository;

public class InMemoryHotelRepository implements HotelRepository {
    Map<String, Hotel> hotels = new HashMap<>();

    @Override
    public void saveHotel(Hotel hotel){
        hotels.put(hotel.getId(), hotel);
    };

    @Override
    public Optional<Hotel> findById(String id){
        return hotels.values().stream().filter(h -> h.getId().equals(id)).findFirst();
    };

    @Override
    public List<Hotel> listAllHotels(){
        return new ArrayList<>(hotels.values());
    }

    @Override
    public void delete(String id){
        hotels.remove(id);
    }

}