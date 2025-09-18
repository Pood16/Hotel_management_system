package utils;

import java.util.regex.Pattern;

public class InputValidator {
    
    private static final Pattern VALIDATE_EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    private static final Pattern NAME_PATTERN = 
        Pattern.compile("^[A-Za-z\\s]{2,50}$");

    
    public static boolean isValidEmail(String email) {
        return email != null && VALIDATE_EMAIL_PATTERN.matcher(email.trim()).matches();
    }


    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

 
    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name.trim()).matches();
    }

  
    public static boolean isPositiveInteger(int value) {
        return value > 0;
    }

    public static boolean isPositiveDouble(double value) {
        return value > 0.0;
    }

   
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

  
    public static boolean isValidHotelName(String name) {
        return isNotEmpty(name) && name.trim().length() >= 2 && name.trim().length() <= 100;
    }

    public static boolean isValidAddress(String address) {
        return isNotEmpty(address) && address.trim().length() >= 5 && address.trim().length() <= 200;
    }
}
