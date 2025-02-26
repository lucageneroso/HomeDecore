package unit;

import control.LoginServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

public class LoginTest {

    @Mock private LoginServlet loginServlet;

    @BeforeEach
    public void setUp() {
        loginServlet = new LoginServlet();
    }

    @Test
    public void testValidateLogin_validLogin() {
        boolean isValid = loginServlet.validateLogin("user@example.com", "correctPassword123");
        assertTrue(isValid);
    }

    @Test
    public void testValidateLogin_invalidEmail() {
        boolean isValid = loginServlet.validateLogin( "user@example", "correctPassword123");
        assertFalse(isValid); // Verifica che un'email non valida renda il login invalido
    }


    @Test
    public void testValidateLogin_invalidPassword() {
        boolean isValid = loginServlet.validateLogin("user@example.com", "wrongPassword");
        assertFalse(isValid); // Verifica che una password errata renda il login invalido
    }

}