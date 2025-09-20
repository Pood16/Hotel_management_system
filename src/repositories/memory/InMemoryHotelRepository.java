package repositories.memory;


import models.Hotel;
import repositories.HotelRepository;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class InMemoryHotelRepository implements HotelRepository {
    private Map<String, Hotel> hotels = new HashMap<>();

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