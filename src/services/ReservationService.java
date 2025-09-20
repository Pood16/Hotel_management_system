package services;

import models.Client;
import models.Reservation;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ReservationService {
    
    Reservation createReservation(Client client, String hotelId, int nights);
    
    void cancelReservation(Client client, UUID reservationId);

    List<Reservation> getClientReservations(UUID clientId);

    List<Reservation> getAllReservations();

    Reservation findReservationById(UUID reservationId);

    void updateReservation(Client client, UUID reservationId, int nights);

    void deleteReservation(UUID reservationId, UUID clientId);

}