package com.foodmine.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class HomePageTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void homePageLoadsTest() throws InterruptedException {
        driver.get("http://localhost:3000");

        Thread.sleep(3000);

        assertTrue(driver.getPageSource().contains("Food Mine"));
        assertTrue(driver.findElements(By.tagName("input")).size() > 0);
        assertTrue(driver.findElements(By.xpath("//button[contains(text(),'Search')]")).size() > 0);
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}