package utils;

import java.util.Scanner;

public class ConsoleInput {
    
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String userInput) {
        System.out.print(userInput);
        return scanner.nextLine().trim();
    }

    public static int readInt(String userInput) {
        while (true) {
            try {
                System.out.print(userInput);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    


    public static int readPositiveInt(String userInput) {
        while (true) {
            int value = readInt(userInput);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a positive integer.");
        }
    }

   
    public static double readDouble(String userInput) {
        while (true) {
            try {
                System.out.print(userInput);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }


    public static double readPositiveDouble(String userInput) {
        while (true) {
            double value = readDouble(userInput);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a positive number.");
        }
    }

    public static String readEmail(String userInput) {
        while (true) {
            String email = readString(userInput);
            if (InputValidator.isValidEmail(email)) {
                return email.trim().toLowerCase();
            }
            System.out.println("Please enter a valid email address.");
        }
    }


    public static String readPassword(String userInput) {
        while (true) {
            String password = readString(userInput);
            if (InputValidator.isValidPassword(password)) {
                return password;
            }
            System.out.println("Password must be at least 6 characters long.");
        }
    }


    public static String readName(String userInput) {
        while (true) {
            String name = readString(userInput);
            if (InputValidator.isValidName(name)) {
                return name.trim();
            }
            System.out.println("Name must contain only letters.");
        }
    }

  
    public static void endOfOperation() {
        System.out.print("\nPAUSE:PRESS ENTER TO CONTINUE...");
        scanner.nextLine();
    }

 
    
    public static void printHeader(String header) {
        System.out.println("\n" + "=".repeat(20));
        System.out.println(header);
        System.out.println("=".repeat(20));
    }


    public static void printSuccessMessage(String message) {
        System.out.println("Success Operation: " + message);
    }

    public static void printErrorMessage(String message) {
        System.out.println("Error Operation: " + message);
    }
}