package repositories.memory;

import models.Reservation;
import repositories.ClientRepository;
import repositories.ReservationRepository;
import java.util.Map;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;



public class InMemoryReservationRepository implements ReservationRepository {
    private Map<UUID, Reservation> reservations = new HashMap<>();

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
        return reservations.values().stream().filter(reservation -> !reservation.getIsCanceled()).toList();
    }

    @Override
    public void delete(UUID id){
        reservations.remove(id);
    }





}