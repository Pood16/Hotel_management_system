package repositories;

import java.util.List;

import models.Reservation;

public interface ReservationRepository {
    void saveReservation(Reservation reservation);
    List<Reservation> listAllReservations();
}