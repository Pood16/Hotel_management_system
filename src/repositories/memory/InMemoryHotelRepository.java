package repositories.memory;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import models.Hotel;
import repositories.HotelRepository;

public class InMemoryHotelRepository implements HotelRepository {
    Map<UUID, Hotel> hotels = new HashMap<>();

    @Override
    public void saveHotel(Hotel hotel){
        hotels.put(hotel.getid(), hotel);
    };

    @Override
    public List<Hotel> listAllHotels(){
        return new ArrayList(hotels.values());
    }





}