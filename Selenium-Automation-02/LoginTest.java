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

public class LoginTest {

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
    public void validLoginTest() throws InterruptedException {
        driver.get("http://localhost:3000/login");

        driver.findElement(By.name("email")).sendKeys("mariam999@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Mariam999");

        driver.findElement(By.xpath("//button[contains(text(),'Login') or contains(text(),'Sign In')]")).click();

        Thread.sleep(3000);

        assertTrue(!driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void invalidLoginTest() throws InterruptedException {
        driver.get("http://localhost:3000/login");

        driver.findElement(By.name("email")).sendKeys("mariam6789@gmail.com");
        driver.findElement(By.name("password")).sendKeys("wrong123");

        driver.findElement(By.xpath("//button[contains(text(),'Login') or contains(text(),'Sign In')]")).click();

        Thread.sleep(3000);

        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}