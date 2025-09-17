package models;

import java.util.UUID;

public class Hotel {
    private UUID id;
    private String name;
    private String address;
    private int availableRooms = 0;
    private double rate;
    private boolean isAvailable = false;

    public Hotel(String name, String address, int availableRooms, boolean isAvailable, double rate ){
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.availableRooms = availableRooms;
        this.isAvailable = isAvailable;
        this.rate = rate;
    };

    public UUID getid() {
        return id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setRate(double rate){
        this.rate = rate;
    }

    public double getRate(){
        return rate;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Hotel{id='" + id + "', name='" + name + "', availableRooms=" + availableRooms + ", rating=" + rate + "}";
    }
}
