package models;
import java.time.Instant;
import java.util.UUID;


public class Reservation {
    private UUID id;
    private Instant timestamp;
    private String hotelId;
    private UUID clientId;
    private int nights;
    private boolean isCanceled = false;

    // TODO:DISPLAY RESERVATIONS IN A TABLE FORMAT

    public Reservation(Instant timestamp, String hotelId, UUID clientId, int nights, boolean isCanceled) {
        this.id = UUID.randomUUID();
        this.timestamp = timestamp;
        this.hotelId = hotelId;
        this.clientId = clientId;
        this.nights = nights;
        this.isCanceled = isCanceled;
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

    public boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    @Override
    public String toString() {
        return "Reservation: id=" + id + ", hotelId='" + hotelId + "', clientId=" + clientId +", nights=" + nights + ", Status: " + (isCanceled ? "Canceled" : "Active") + ", date=" + timestamp;
    }
}
