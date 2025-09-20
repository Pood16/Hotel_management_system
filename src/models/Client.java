package models;

import java.util.UUID;

public class Client {


    private final UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isConnected = false;
    private boolean isAdmin = false;

    public Client(String firstName, String lastName, String email, String password, boolean isConnected){
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isConnected = isConnected;
    }
    public Client(String firstName, String lastName, String email, String password, boolean isConnected, boolean isAdmin) {
        this(firstName, lastName, email, password, isConnected);
        this.isAdmin = isAdmin;

    }

    public UUID getid(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

   @Override
   public String toString() {
       return "Id: " + id + "\n" +
               "Full Name: " + firstName + " " + lastName + "\n" +
               "Email: " + email;
   }

}
