package models;
import java.time.Instant;
import java.util.UUID;


public class Reservation {
    private UUID id;
    private Instant timestamp;
    private String hotelId;
    private UUID clientId;
    private int nights;

    public Reservation(UUID id, Instant timestamp, String hotelId, UUID clientId, int nights) {
        this.id = id;
        this.timestamp = timestamp;
        this.hotelId = hotelId;
        this.clientId = clientId;
        this.nights = nights;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    @Override
    public String toString() {
        return "Reservation information : id=" + id + ", hotelId='" + hotelId + "', clientId=" + clientId +", nights=" + nights + ", date=" + timestamp;
    }
}
