package services;

import models.Client;
import models.Hotel;

import java.util.List;
import java.util.UUID;

public interface HotelService {

    Hotel createHotel(Client client, String name, String address, int availableRooms, double rating);

    void updateHotel(Client client, String hotelId, String name, String address, int availableRooms);

    void deleteHotel(Client client,String hotelId);

    List<Hotel> listHotels();

    Hotel findHotelById(String hotelId);
}
