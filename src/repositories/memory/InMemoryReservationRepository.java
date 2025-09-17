package repositories.memory;

import models.Reservation;
import repositories.ReservationRepository;

import java.util.*;

public class InMemoryReservationRepository implements ReservationRepository {
    Map<UUID, Reservation> reservations = new HashMap<>();

    @Override
    public void saveReservation(Reservation reservation){
        reservations.put(reservation.getId(), reservation);
    }

    @Override
    public List<Reservation> listAllReservations(){
        return new ArrayList<>(reservations.values());
    }


}