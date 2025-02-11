package unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {

    //@Mock private UserService userService;


    private LoginServiceTest loginServiceTest;

    @BeforeEach
    public void setUp() {
        loginServiceTest = new LoginServiceTest();
    }

    @Test
    public void testValidateLogin_validLogin() {
        boolean isValid = loginServiceTest.validateLogin("validUsername123", "user@example.com", "correctPassword123");
        assertTrue(isValid); // Verifica che il login sia valido
    }

    @Test
    public void testValidateLogin_invalidEmail() {
        boolean isValid = loginServiceTest.validateLogin("validUsername123", "user@example", "correctPassword123");
        assertFalse(isValid); // Verifica che un'email non valida renda il login invalido
    }

    @Test
    public void testValidateLogin_invalidUsername() {
        boolean isValid = loginServiceTest.validateLogin("usr", "user@example.com", "correctPassword123");
        assertFalse(isValid); // Verifica che uno username non valido renda il login invalido
    }

    @Test
    public void testValidateLogin_invalidPassword() {
        boolean isValid = loginServiceTest.validateLogin("validUsername123", "user@example.com", "wrongPassword");
        assertFalse(isValid); // Verifica che una password errata renda il login invalido
    }

}