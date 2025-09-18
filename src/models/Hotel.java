package models;

import java.util.UUID;

public class Hotel {
    private String id;
    private String name;
    private String address;
    private int availableRooms;
    private double rate;
    private boolean isAvailable = false;



    public Hotel(String name, String address, int availableRooms, boolean isAvailable, double rate ){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.availableRooms = availableRooms;
        this.isAvailable = isAvailable;
        this.rate = rate;
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
