package unit;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginServiceTest {
    private static final String CORRECT_PASSWORD = "correctPassword123";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final int MIN_USERNAME_LENGTH = 5;
    private static final int MAX_USERNAME_LENGTH = 20;

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        return CORRECT_PASSWORD.equals(password);
    }

    public boolean validateUsername(String username) {
        return username.length() >= MIN_USERNAME_LENGTH && username.length() <= MAX_USERNAME_LENGTH;
    }

    public boolean validateLogin(String username, String email, String password) {
        return validateUsername(username) && validateEmail(email) && validatePassword(password);
    }
}

