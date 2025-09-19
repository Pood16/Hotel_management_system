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
        return Optional.ofNullable(hotels.get(id));
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