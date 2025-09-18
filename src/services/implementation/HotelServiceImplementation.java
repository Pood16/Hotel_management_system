package services.implementation;

import models.Client;
import models.Hotel;
import repositories.ClientRepository;
import repositories.HotelRepository;
import repositories.ReservationRepository;
import services.HotelService;

import java.util.List;
import java.util.Optional;

public class HotelServiceImplementation  implements HotelService {

    private final HotelRepository hotelRepository;



    public HotelServiceImplementation(HotelRepository hotelRepository, ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel createHotel(Client client, String name, String address, int availableRooms, double rate) {

        if (!client.isAdmin()) {
            throw new SecurityException("You need admin permissions for this operation");
        }

        if (availableRooms <= 0) {
            throw new IllegalArgumentException("Number of rooms must be greater than 0.");
        }
        Hotel hotel = new Hotel(
                name,
                address,
                availableRooms,
                true,
                rate

        );
        hotelRepository.saveHotel(hotel);
        return hotel;
    }

    @Override
    public void updateHotel(Client client,String hotelId, String name, String address, int availableRooms) {

        if (!client.isAdmin()) {
            throw new SecurityException("You need admin permissions for this operation");
        }

        Optional<Hotel> targetHotel = hotelRepository.findById(hotelId);

        if (targetHotel.isEmpty()) {
            throw new IllegalArgumentException("Hotel not found: " + name);
        }

        if (availableRooms <= 0) {
            throw new IllegalArgumentException("Available rooms cannot be negative or zero.");
        }

        Hotel updated = targetHotel.get();

        updated.setName(name);
        updated.setAddress(address);
        updated.setAvailableRooms(availableRooms);

        hotelRepository.saveHotel(updated);

    }

    @Override
    public void deleteHotel(Client client, String hotelId) {

        if (!client.isAdmin()) {
            throw new SecurityException("You need admin permissions for this operation");
        }

        Hotel hotel = hotelRepository.findById(hotelId).get();
        if (hotel.getAvailableRooms() > 0) {
            throw new IllegalArgumentException("You cant delete hotels with reservations");
        }
        hotelRepository.delete(hotelId);
    }


    @Override
    public List<Hotel> listHotels() {
        return hotelRepository.listAllHotels();
    }


    @Override
    public Hotel findHotelById(String hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found: " + hotelId));
    }
}

