package unit;

import control.LoginServlet;
import control.RegisterServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {

    @Mock
    private RegisterServlet registerServlet;

    @BeforeEach
    public void setUp() {
        registerServlet = new RegisterServlet();
    }

    @Test
    public void validatePassword_Right() {
        boolean isValid = registerServlet.validatePassword("CiaoComeStai2000");
        assertTrue(isValid); // Verifica che il login sia valido
    }

    @Test
    public void validatePassword_Wrong_NoMaiuscola() {
        boolean isValid = registerServlet.validatePassword("ciaoometai200");
        assertFalse(isValid);
    }

    @Test
    public void validatePassword_Wrong_NoNumero() {
        boolean isValid = registerServlet.validatePassword("CiaoComeStai");
        assertFalse(isValid);
    }

    @Test
    public void validatePassword_Wrong_Corta() {
        boolean isValid = registerServlet.validatePassword("C2");
        assertFalse(isValid);
    }

    @Test
    public void validateEmail_Right() {
        boolean isValid = registerServlet.validateEmail("ric.alfa.2003@gmail.com");
        assertTrue(isValid); // Verifica che una password errata renda il login invalido
    }

    @Test
    public void validateEmail_Wrong_CaratteriSpeciali() {
        boolean isValid = registerServlet.validateEmail("ric.al,fa.2003@gmail.com");
        assertFalse(isValid); // Verifica che uno username non valido renda il login invalido
    }

    @Test
    public void validateEmail_Wrong_Corta() {
        boolean isValid = registerServlet.validateEmail("r@g.a");
        assertFalse(isValid); // Verifica che uno username non valido renda il login invalido
    }

    @Test
    public void validateEmail_Wrong_PuntiDiFila() {
        boolean isValid = registerServlet.validateEmail("ric..alfa.2003@gmail.com");
        assertFalse(isValid); // Verifica che uno username non valido renda il login invalido
    }


}
