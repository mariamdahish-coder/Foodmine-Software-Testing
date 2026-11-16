package com.foodmine.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {

        System.setProperty(
            "webdriver.edge.driver",
            "C:\\msedgedriver.exe"
        );

        driver = new EdgeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void validRegisterTest() throws InterruptedException {

        driver.get("http://localhost:3000/register");

        String email = "mariamtest" + System.currentTimeMillis() + "@gmail.com";

        driver.findElement(By.name("name")).sendKeys("Mariam Test");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Mariam123");
        driver.findElement(By.name("confirmPassword")).sendKeys("Mariam123");

        driver.findElement(
            By.xpath("//button[contains(text(),'Register') or contains(text(),'Sign Up')]")
        ).click();

        Thread.sleep(4000);

        assertTrue(
            !driver.getCurrentUrl().contains("register")
            || driver.getPageSource().toLowerCase().contains("login")
            || driver.getPageSource().toLowerCase().contains("mariam")
        );
    }

    @Test
    public void invalidRegisterTest() throws InterruptedException {

        driver.get("http://localhost:3000/register");

        driver.findElement(By.name("name"))
              .sendKeys("Test User");

        driver.findElement(By.name("email"))
              .sendKeys("wrongemail");

        driver.findElement(By.name("password"))
              .sendKeys("123");

        driver.findElement(By.name("confirmPassword"))
              .sendKeys("456");

        driver.findElement(
            By.xpath("//button[contains(text(),'Register') or contains(text(),'Sign Up')]")
        ).click();

        Thread.sleep(3000);

        assertTrue(
            driver.getCurrentUrl().contains("register")
        );
    }

    @AfterEach
    public void closeBrowser() {

        if (driver != null) {
            driver.quit();
        }
    }
}