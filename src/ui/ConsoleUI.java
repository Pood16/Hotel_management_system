package ui;
// models
import models.Hotel;
import models.Client;
import models.Reservation;
// repositories
import repositories.ClientRepository;
import repositories.HotelRepository;
import repositories.ReservationRepository;
// services
import services.AuthService;
import services.HotelService;
import services.ReservationService;
// repositories implementation
import repositories.memory.InMemoryClientRepository;
import repositories.memory.InMemoryHotelRepository;
import repositories.memory.InMemoryReservationRepository;
// services implementation
import services.implementation.AuthServicesImplementation;
import services.implementation.HotelServiceImplementation;
import services.implementation.ReservationServiceImplementation;

import utils.ConsoleInput;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class ConsoleUI {
  
    private final AuthService authService;
    private final HotelService hotelService;
    private final ReservationService reservationService;
    private Client currentUser;

    public ConsoleUI() {

        ClientRepository clientRepository = new InMemoryClientRepository();
        HotelRepository hotelRepository = new InMemoryHotelRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();

        this.authService = new AuthServicesImplementation(clientRepository);
        this.hotelService = new HotelServiceImplementation(hotelRepository);
        this.reservationService = new ReservationServiceImplementation(reservationRepository, hotelRepository, clientRepository);
        createAdminHotels();

    }

    private void createAdminHotels() {
        try {
            currentUser = authService.register("admin", "admin", "admin@admin.com", "admin123", true);
            hotelService.createHotel(currentUser,"Youcode Nador", "Nador", 2, 2 );
            hotelService.createHotel(currentUser,"Youcode Youssoufia", "Youssoufia", 10, 5 );
            hotelService.createHotel(currentUser,"Youcode Safi", "Safi", 10, 5 );
            currentUser = authService.logout();
        } catch (Exception e) {
            System.out.println("Failed To Create The Admin and Hotels: " + e.getMessage());
        }
    }


    public void run() {

        ConsoleInput.printHeader("WELCOME TO HOTEL RESERVATION SYSTEM");

        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showAuthMenu() {
        ConsoleInput.printHeader("AUTHENTICATION MENU");

        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");

        int choice = ConsoleInput.readInt("\nEnter your choice [1-3]: ");

        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleRegister();
                break;
            case 3:
                handleExit();
                break;
            default:
                ConsoleInput.printErrorMessage("Invalid choice: Please select a number from the menu.");
                ConsoleInput.endOfOperation();
        }
    }

    private void handleLogin() {
        ConsoleInput.printHeader("LOGIN");

        try {

            String email = ConsoleInput.readEmail("Email: ");
            String password = ConsoleInput.readPassword("Password: ");

            Optional<Client> loggedInUser = authService.login(email, password);

            if (loggedInUser.isPresent()) {
                currentUser = loggedInUser.get();
                ConsoleInput.printSuccessMessage("Login successful! Welcome, " + currentUser.getFirstName() + (currentUser.isAdmin() ? "(Admin)" : "(User)" ) + "!");
                ConsoleInput.endOfOperation();
            } else {
                ConsoleInput.printErrorMessage("Invalid credentials. Please try again.");
                ConsoleInput.endOfOperation();
            }
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Login failed: " + e.getMessage());
            ConsoleInput.endOfOperation();
        }
    }

    private void handleRegister() {
        ConsoleInput.printHeader("REGISTER NEW ACCOUNT");

        try {
            String firstName = ConsoleInput.readName("First Name: ");
            String lastName = ConsoleInput.readName("Last Name: ");
            String email = ConsoleInput.readEmail("Email: ");
            String password = ConsoleInput.readPassword("Password: ");

            currentUser = authService.register(firstName, lastName, email, password, false);


            ConsoleInput.printSuccessMessage("Registration successful! Welcome, " + firstName + "!");
            ConsoleInput.endOfOperation();

        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Registration failed: " + e.getMessage());
            ConsoleInput.endOfOperation();
        }
    }

    private void handleExit() {
        System.out.println("Thank you for using Hotel Reservation System.");
        System.exit(0);
    }

    private void showMainMenu() {
        ConsoleInput.printHeader("MAIN MENU - Welcome " + currentUser.getFirstName() +
                                 (currentUser.isAdmin() ? " (Admin)" : " (User)"));

        System.out.println("0.  Profile");
        System.out.println("HOTEL MANAGEMENT:");
        System.out.println("1.  View All Hotels");
        System.out.println("2.  Find Hotel by ID");

        if (currentUser.isAdmin()) {
            System.out.println("3.  Create Hotel");
            System.out.println("4.  Update Hotel");
            System.out.println("5.  Delete Hotel");
        }

        System.out.println("RESERVATION MANAGEMENT:");
        System.out.println("6.  Make Reservation");
        System.out.println("7.  View My Reservations");
        System.out.println("8.  Update Reservation");
        System.out.println("9.  Cancel Reservation");

        if (currentUser.isAdmin()) {
            System.out.println("10.  View All Reservations");
        }

        System.out.println("11. Logout");

        int choice = ConsoleInput.readInt("Enter your choice: ");

        switch (choice) {
            case 0 -> showProfile();
            case 1 -> viewAllHotels();
            case 2 -> findHotelById();
            case 3 -> {
                if (currentUser.isAdmin()) createHotel();
                else ConsoleInput.printErrorMessage("Admin access required.");
            }
            case 4 -> {
                if (currentUser.isAdmin()) updateHotel();
                else ConsoleInput.printErrorMessage("Admin access required.");
            }
            case 5 -> {
                if (currentUser.isAdmin()) deleteHotel();
                else ConsoleInput.printErrorMessage("Admin access required.");
            }
            case 6 -> makeReservation();
            case 7 -> viewMyReservations();
            case 8 -> updateReservation();
            case 9 -> cancelReservation();
            case 10 -> {
                if (currentUser.isAdmin()) viewAllReservations();
                ConsoleInput.printErrorMessage("Admin access required.");
            }
            case 11 -> logout();
            default -> {
                ConsoleInput.printErrorMessage("Invalid choice.");
//                ConsoleInput.endOfOperation();
            }
        }
    }

    private void showProfile() {
        ConsoleInput.printHeader("WELCOME TO YOUR PROFILE");
        System.out.println(currentUser);
        System.out.println("Update Profile");
        System.out.println("[1]. Update Email");
        System.out.println("[2]. Update Password");
        System.out.println("[3]. Back to main menu");
        int choice = ConsoleInput.readInt("Choose a number: ");
        switch (choice) {
            case 1 -> {
                String email = ConsoleInput.readEmail("Enter the new Email: ");
                authService.updateEmail(currentUser, email);
                ConsoleInput.printSuccessMessage("Email changed successfully");
            }
            case 2 -> {
                String password = ConsoleInput.readPassword("Enter the new Password");
                authService.updatePassword(currentUser, password);
                ConsoleInput.printSuccessMessage("Password Changed Successfully");
            }
            case 3 -> showMainMenu();
            default -> System.out.println("Invalid choice, choose numbers from the menu");
        }
        ConsoleInput.endOfOperation();
    }

    private void viewAllHotels() {
        ConsoleInput.printHeader("ALL HOTELS");

        try {
            List<Hotel> hotels = hotelService.listHotels();

            if (hotels.isEmpty()) {
                System.out.println("No hotels available.");
            } else {
                for (Hotel hotel : hotels) {
                    System.out.println(hotel);
                }
            }
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error retrieving hotels: " + e.getMessage());
        }
        ConsoleInput.endOfOperation();
    }

    private void findHotelById() {
        ConsoleInput.printHeader("FIND HOTEL BY ID");

        try {
            String hotelId = ConsoleInput.readString("Enter Hotel ID: ");
            Hotel hotel = hotelService.findHotelById(hotelId);
            System.out.println("\n" + hotel);
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error: " + e.getMessage());
        }
        ConsoleInput.endOfOperation();
    }

    private void createHotel() {
        ConsoleInput.printHeader("CREATE NEW HOTEL");

        try {
            String name = ConsoleInput.readString("Hotel Name: ");
            String address = ConsoleInput.readString("Address: ");
            int rooms = ConsoleInput.readPositiveInt("Number of Rooms: ");
            double rate = ConsoleInput.readPositiveDouble("Rating (0.0-5.0): ");

            Hotel hotel = hotelService.createHotel(currentUser, name, address, rooms, rate);
            ConsoleInput.printSuccessMessage("Hotel created successfully!");
            System.out.println(hotel);
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error creating hotel: " + e.getMessage());
        }
        ConsoleInput.endOfOperation();
    }

    private void updateHotel() {
        ConsoleInput.printHeader("UPDATE HOTEL");

        try {
            String hotelId = ConsoleInput.readString("Enter Hotel ID to update: ");
            String name = ConsoleInput.readString("New Hotel Name: ");
            String address = ConsoleInput.readString("New Address: ");
            int rooms = ConsoleInput.readPositiveInt("New Number of Rooms: ");

            hotelService.updateHotel(currentUser, hotelId, name, address, rooms);
            ConsoleInput.printSuccessMessage("Hotel updated successfully!");
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error updating hotel: " + e.getMessage());
        }

//        ConsoleInput.endOfOperation();
    }

    private void deleteHotel() {
        ConsoleInput.printHeader("DELETE HOTEL");
        try {
            String hotelId = ConsoleInput.readString("Enter Hotel ID to delete: ");
            String confirmation = ConsoleInput.readString("Type 'DELETE' to delete: ");

            if ("DELETE".equals(confirmation)) {
                hotelService.deleteHotel(currentUser, hotelId);
                ConsoleInput.printSuccessMessage("Hotel deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            ConsoleInput.printErrorMessage(e.getMessage());
        }
        ConsoleInput.endOfOperation();
    }

    private void makeReservation() {
        ConsoleInput.printHeader("MAKE RESERVATION");

        try {
            // Show available hotels first
            List<Hotel> hotels = hotelService.listHotels();
            if (hotels.isEmpty()) {
                ConsoleInput.printErrorMessage("No hotels available for reservation.");
//                ConsoleInput.endOfOperation();
                return;
            }

            System.out.println("Available Hotels:");
            for (Hotel hotel : hotels) {
                if (hotel.isAvailable() && hotel.getAvailableRooms() > 0) {
                    System.out.println(hotel);
                }
            }

            String hotelId = ConsoleInput.readString("\nEnter Hotel ID: ");
            int nights = ConsoleInput.readPositiveInt("Number of nights: ");

            Reservation reservation = reservationService.createReservation(currentUser, hotelId, nights);
            ConsoleInput.printSuccessMessage("Reservation created successfully!");
            System.out.println(reservation);
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error creating reservation: " + e.getMessage());
        }

//        ConsoleInput.endOfOperation();
    }

    private void viewMyReservations() {
        ConsoleInput.printHeader("MY RESERVATIONS");

        try {
            List<Reservation> reservations = reservationService.getClientReservations(currentUser.getid());

            if (reservations.isEmpty()) {
                System.out.println("You have no reservations.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error retrieving reservations: " + e.getMessage());
        }
        ConsoleInput.endOfOperation();
    }

    private void updateReservation() {
        ConsoleInput.printHeader("UPDATE RESERVATION");
        try {
            String reservationId = ConsoleInput.readString("Enter Reservation ID: ");
            int nights = ConsoleInput.readPositiveInt("New number of nights: ");

            reservationService.updateReservation(currentUser, UUID.fromString(reservationId), nights);
            ConsoleInput.printSuccessMessage("Reservation updated successfully!");
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error updating reservation: " + e.getMessage());
        }

        ConsoleInput.endOfOperation();
    }

    private void cancelReservation() {
        ConsoleInput.printHeader("CANCEL RESERVATION");

        try {
            String reservationId = ConsoleInput.readString("Enter Reservation ID: ");
            String confirmation = ConsoleInput.readString("Type 'CONFIRM' to cancel: ");

            if ("CONFIRM".equals(confirmation)) {
                reservationService.cancelReservation(currentUser, UUID.fromString(reservationId));
                ConsoleInput.printSuccessMessage("Reservation cancelled successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error cancelling reservation: " + e.getMessage());
        }

        ConsoleInput.endOfOperation();
    }

    private void viewAllReservations() {
        ConsoleInput.printHeader("ALL RESERVATIONS (ADMIN)");

        try {
            List<Reservation> reservations = reservationService.getAllReservations();

            if (reservations.isEmpty()) {
                System.out.println("No reservations found.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error retrieving reservations: " + e.getMessage());
        }

        ConsoleInput.endOfOperation();
    }

    private void logout() {
        try {
            authService.logout();
            currentUser = null;
            ConsoleInput.printSuccessMessage("Logged out successfully!");
            ConsoleInput.endOfOperation();
        } catch (Exception e) {
            ConsoleInput.printErrorMessage("Error during logout: " + e.getMessage());
            ConsoleInput.endOfOperation();
        }
    }
}