package repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import models.Reservation;

public interface ReservationRepository {
    void saveReservation(Reservation reservation);
    List<Reservation> listAllReservations();
    Optional<Reservation> findById(UUID id);
    List<Reservation> findByClientId(UUID clientId);
    void delete(UUID id);
}