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
    public void tC1ValidLogin()  {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div")).click(); //Premi prodotto

        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/button")).click(); //Premi aggiungi al carrello

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Aggiunt al carrello elementi superiore alla disponibilità
    @Test
    public void tC2NotPresentPassword() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div")).click(); //Premi prodotto

        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/input[2]")).clear(); //Svuota input
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/input[2]")).sendKeys("301"); //Aggiungi quantità
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/button")).click(); //Aggiungi al carrello

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Aggiunta la carrello di quantità nulla o negativa
    @Test
    public void tC3NotValidPassword() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div")).click(); //Premi prodotto

        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/input[2]")).clear(); //Svuota input
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/input[2]")).sendKeys("-1"); //Aggiungi -1
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/button")).click(); //Aggiungio al carrello

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Modifica quantità dei prodotti nel carrello
    @Test
    public void tC4NotPresentEmail() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[3]/a")).click(); //Vai al catalogo
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div")).click(); //Premi prodotto

        //Vai nel carrello
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/form/button")).click();
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[5]/form/button[1]")).click();

        // Modifica quantità pulendo l'input e aumentando a 2
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[5]/form/div/input[3]")).clear();
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[5]/form/div/input[3]")).sendKeys("2");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[5]/form/button[1]")).click();


        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }


}