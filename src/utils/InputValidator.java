package utils;

import java.util.regex.Pattern;

public class InputValidator {
    

    private static final Pattern VALIDATE_NAME_PATTERN = Pattern.compile("^[A-Za-z0-9'-_\\s]{3,100}$");
    private static final Pattern VALIDATE_EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+._-]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,}$");

    
    public static boolean isValidEmail(String email) {
        return email != null && VALIDATE_EMAIL_PATTERN.matcher(email.trim()).matches();
    }
   


    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

 
    public static boolean isValidName(String name) {
        return name != null && VALIDATE_NAME_PATTERN.matcher(name.trim()).matches();
    }


    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
