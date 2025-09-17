package repositories;

import java.util.List;

import models.Hotel;

public interface HotelRepository {
    void saveHotel(Hotel hotel);
    List<Hotel> listAllHotels();
}