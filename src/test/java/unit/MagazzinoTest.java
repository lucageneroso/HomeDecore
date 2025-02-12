package unit;

import control.CatalogoServlet;
import control.LoginServlet;
import enumerativeTypes.Categoria;
import model.OrderManagement.Prodotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import service.Catalogo;

public class MagazzinoTest {

    @Mock private Catalogo catalogo;

    @BeforeEach
    public void setUp() {
        catalogo = new Catalogo();
    }

    Prodotto prodotto= new Prodotto("Bel Test", "Prodotto per effettuare testing", 30.0, null, Categoria.CUCINA, 300, true, true);

    /* Test su nome  */

    // Nome nullo
    @Test public void testNameChange_Wrong() {
        boolean isValid= catalogo.validateNameChange("");
        assertFalse(isValid);
    }

    // Nome non nullo
    @Test public void testNameChange_Right() {
        boolean isValid= catalogo.validateNameChange("Nuovo prodotto test");
        assertTrue(isValid);
    }



    /* Test su prezzo  */

    // Prezzo <=0
    @Test public void testPricehange_Wrong() {
        boolean isValid= catalogo.validatePriceChange(-2.0);
        assertFalse(isValid);
    }

    // Prezzo >0
    @Test public void testPricehange_Right() {
        boolean isValid= catalogo.validatePriceChange(2.0);
        assertTrue(isValid);
    }



    /* Test su addtoMagazzino  */

    //Supponiamo che il prodotto sia in magazzino
    @Test public void testAddToMagazzino_Wrong() {
        boolean isValid= catalogo.validateAddToMagazzino(prodotto);
        assertFalse(isValid);
    }

    //Supponiamo che il prodotto non sia in magazzino
    @Test public void testAddToMagazzino_Right() {
        prodotto.setInMagazzino(false); //eliminiamo il prodotto dal magazzino per comodità
        boolean isValid= catalogo.validateAddToMagazzino(prodotto);
        assertTrue(isValid);
    }



    /* Test su addtoCatalogo  */

    //Supponiamo che il prodotto sia in catalogo
    @Test public void testAddToCatalogo_Wrong() {
        boolean isValid= catalogo.validateAddToCatalogo(prodotto);
        assertFalse(isValid);
    }

    //Supponiamo che il prodotto non sia in catalogo
    @Test public void testAddToCatalogo_Right() {
        prodotto.setInCatalogo(false); //eliminiamo il prodotto dal magazzino per comodità
        boolean isValid= catalogo.validateAddToCatalogo(prodotto);
        assertTrue(isValid);
    }






    /* Test su RemoveFromMagazzino  */

    //Supponiamo che il prodotto sia in catalogo
    @Test public void testRemoveFromMagazzino_Right() {
        boolean isValid= catalogo.validateRemoveFromCatalogo(prodotto);
        assertTrue(isValid);
    }

    //Supponiamo che il prodotto non sia in catalogo
    @Test public void testRemoveFromMagazzino_Wrong() {
        prodotto.setInMagazzino(false); //eliminiamo il prodotto dal magazzino per comodità
        boolean isValid= catalogo.validateRemoveFromMagazzino(prodotto);
        assertFalse(isValid);
    }





    /* Test su RemoveFromCatalogo  */

    //Supponiamo che il prodotto sia in catalogo
    @Test public void testRemoveFromCatalogo_Right() {
        boolean isValid= catalogo.validateRemoveFromCatalogo(prodotto);
        assertTrue(isValid);
    }

    //Supponiamo che il prodotto non sia in catalogo
    @Test public void testRemoveFromCatalogo_Wrong() {
        prodotto.setInCatalogo(false); //eliminiamo il prodotto dal magazzino per comodità
        boolean isValid= catalogo.validateRemoveFromCatalogo(prodotto);
        assertFalse(isValid);
    }



}
