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

    public ReservationServiceImplementation(ReservationRepository reservationRepository, HotelRepository hotelRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.clientRepository = clientRepository;
    }

    // TODO:FIX THE VUE ALL RESERVATIONS PERMISSION

    @Override
    public Reservation createReservation(Client client, String hotelId, int nights) {

        if (!client.isConnected()) {
            throw new SecurityException("You must be logged in to make a reservation");
        }

        if (nights <= 0) {
            throw new IllegalArgumentException("You should rserve at least one night");
        }

        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);

        if (hotelOpt.isEmpty()) {
            throw new IllegalArgumentException("No Hotel with this ID: " + hotelId);
        }

        Hotel hotel = hotelOpt.get();

        if (!hotel.isAvailable()) {
            throw new IllegalArgumentException("Hotel is not available for reservations");
        }

        if (hotel.getAvailableRooms() == 0) {
            throw new IllegalArgumentException("No rooms available at this hotel");
        }

        Reservation reservation = new Reservation(Instant.now(), hotelId, client.getid(), nights, false);

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotel.setHasReservation(true);
        hotelRepository.saveHotel(hotel);
        reservationRepository.saveReservation(reservation);
        return reservation;
    }

    @Override
    public void cancelReservation(Client client, UUID reservationId) {

        if (!client.isConnected()) {
            throw new SecurityException("You must be logged in to cancel a reservation");
        }

        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);

        if (reservationOpt.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found with ID: " + reservationId);
        }

        Reservation reservation = reservationOpt.get();


        if (!reservation.getClientId().equals(client.getid())) {
            throw new SecurityException("You can only cancel your own reservations");
        }


        Optional<Hotel> hotelOpt = hotelRepository.findById(reservation.getHotelId());

        if (hotelOpt.isPresent()) {
            Hotel hotel = hotelOpt.get();
            hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
            hotelRepository.saveHotel(hotel);
        }
        reservation.setIsCanceled(true);

        reservationRepository.saveReservation(reservation);
    }

    @Override
    public List<Reservation> getClientReservations(UUID clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        return reservationRepository.findByClientId(clientId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.listAllReservations();
    }

    @Override
    public Reservation findReservationById(UUID reservationId) {
        if (reservationId == null) {
            throw new IllegalArgumentException("Reservation ID cannot be null");
        }

        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found: " + reservationId));
    }

    @Override
    public void updateReservation(Client client, UUID reservationId, int nights) {

        if (!client.isConnected()) {
            throw new SecurityException("You must be logged in to update a reservation");
        }


        if (nights <= 0) {
            throw new IllegalArgumentException("Number of nights must be greater than 0");
        }


        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found with ID: " + reservationId);
        }

        Reservation reservation = reservationOpt.get();


        if (!reservation.getClientId().equals(client.getid())) {
            throw new SecurityException("You can only update your own reservations");
        }

        reservation.setNights(nights);
        reservationRepository.saveReservation(reservation);
    }
}