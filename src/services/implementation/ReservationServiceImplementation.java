package services.implementation;

import models.Client;
import models.Hotel;
import models.Reservation;
import repositories.ClientRepository;
import repositories.HotelRepository;
import repositories.ReservationRepository;
import services.ReservationService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationServiceImplementation implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final ClientRepository clientRepository;

    public ReservationServiceImplementation(ReservationRepository reservationRepository, 
                                          HotelRepository hotelRepository, 
                                          ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Reservation createReservation(Client client, String hotelId, int nights) {
        if (!client.isConnected()) {
            throw new SecurityException("You must be logged in to make a reservation");
        }

        if (nights <= 0) {
            throw new IllegalArgumentException("Number of nights must be greater than 0");
        }


        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isEmpty()) {
            throw new IllegalArgumentException("No Hotel with this ID: " + hotelId);
        }

        Hotel hotel = hotelOpt.get();

        if (!hotel.isAvailable()) {
            throw new IllegalArgumentException("Hotel is not available for reservations");
        }

        if (hotel.getAvailableRooms() <= 0) {
            throw new IllegalArgumentException("No rooms available at this hotel");
        }


        Reservation reservation = new Reservation(
            Instant.now(),
            hotelId,
            client.getid(),
            nights
        );

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotelRepository.saveHotel(hotel);
        reservationRepository.saveReservation(reservation);

        return reservation;
    }
}