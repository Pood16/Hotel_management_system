package repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import models.Hotel;

public interface HotelRepository {
    void saveHotel(Hotel hotel);
    Optional<Hotel> findById(UUID id);
    List<Hotel> listAllHotels();
    void delete(UUID id);
}