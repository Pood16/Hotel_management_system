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
    public Optional<Reservation> findById(UUID id){
        return reservations.values().stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    @Override
    public List<Reservation> findByClientId(UUID clientId){
        return reservations.values().stream().filter(r -> r.getClientId().equals(clientId)).toList();
    }

    @Override
    public List<Reservation> listAllReservations(){
        return new ArrayList<>(reservations.values());
    }

    @Override
    public void delete(UUID id){
        reservations.remove(id);
    }


}