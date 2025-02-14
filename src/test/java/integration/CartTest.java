package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;


class CartTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    //Aggiunta al carrello
    @Test
    public void aggiuntaProdottoAlCarrello()  {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/a/img")).click(); //Premi prodotto
        driver.findElement(By.xpath("/html/body/div/section/div/div/form/button")).click(); //Premi aggiungi al carrello

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Aggiunt al carrello elementi superiore alla disponibilità
    @Test
    public void aggiuntaProdottoAlCarrello_QuantitaSuperiore() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/a/img")).click(); //Premi prodotto

        driver.findElement(By.xpath("/html/body/div/section/div/div/form/input[2]")).clear(); //Svuota input
        driver.findElement(By.xpath("/html/body/div/section/div/div/form/input[2]")).sendKeys("301"); //Aggiungi quantità
        driver.findElement(By.xpath("/html/body/div/section/div/div/form/button")).click(); //Premi aggiungi al carrello

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Aggiunta la carrello di quantità nulla o negativa
    @Test
    public void aggiuntaProdottoAlCarrello_QuantitaNullaNegativa() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/a/img")).click(); //Premi prodotto

        driver.findElement(By.xpath("/html/body/div/section/div/div/form/input[2]")).clear(); //Svuota input
        driver.findElement(By.xpath("/html/body/div/section/div/div/form/input[2]")).sendKeys("-1"); //Aggiungi quantità negativa
        driver.findElement(By.xpath("/html/body/div/section/div/div/form/button")).click(); //Premi aggiungi al carrello

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Modifica quantità dei prodotti nel carrello
    @Test
    public void modificaQuantitaProdotti() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/a/img")).click(); //Premi prodotto

        //Vai nel carrello
        driver.findElement(By.xpath("/html/body/div/section/div/div/form/button")).click(); //Premi aggiungi al carrello

        //driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[5]/form/button[1]")).click();

        // Modifica quantità pulendo l'input e aumentando a 2
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td/form[1]/input[1]")).clear();
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td/form[1]/input[1]")).sendKeys("2");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td/form[1]/button")).click();


        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }


}