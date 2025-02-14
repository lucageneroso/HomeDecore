package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

class LoginTest {
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

    //Login Valido
    @Test
    public void LoginValido()  {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[4]/a")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("mario.rossi@example.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("password");

        driver.findElement(By.xpath("/html/body/div/form/div[3]/button")).click();

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Password non inserita
    @Test
    public void PasswordNonInserita() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[4]/a")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("mario.rossi@example.com");
        driver.findElement(By.id("password")).click();
        //driver.findElement(By.id("password")).sendKeys("2222WxY$p");

        driver.findElement(By.xpath("/html/body/div/form/div[3]/button")).click();

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Password non valida
    @Test
    public void PasswordNonValida() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[4]/a")).click();

        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("mario.rossi@example.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("Jackson");

        driver.findElement(By.xpath("/html/body/div/form/div[3]/button")).click();

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Email non presente
    @Test
    public void EmailNonPresente() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[4]/a")).click();

        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("giacomporetti@unisa.it");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("yaya");

        driver.findElement(By.xpath("/html/body/div/form/div[3]/button")).click();

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    /*
    // Formato password non valido
    @Test
    public void FormatoPasswordNonValido() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[4]/a")).click();

        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("giacomo@unisa.it");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("2212");

        driver.findElement(By.xpath("/html/body/div/form/div[3]/button")).click();

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    //Formato email non valida
    @Test
    public void FormatoEmailNonValido() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[4]/a")).click();

        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("giacunsa..@s.it");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("2212XyA$");

        driver.findElement(By.xpath("/html/body/div/form/div[3]/button")).click();

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

     */

    //Email e password non validi
    @Test
    public void NonValido() {
        driver.get("http://localhost:8080/HomeDecore/home.jsp");
        driver.findElement(By.xpath("/html/body/header/ul/li[4]/a")).click();

        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("giacunsa.it");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("221");

        driver.findElement(By.xpath("/html/body/div/form/div[3]/button")).click();

        // Ferma l'esecuzione per 5 secondi (5000 millisecondi)
        try {Thread.sleep(5000);}
        catch (InterruptedException e) { e.printStackTrace();}
    }

}