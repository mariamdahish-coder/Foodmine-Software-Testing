package com.foodmine.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FoodDetailsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");

        driver = new EdgeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void openFoodDetailsTest() throws InterruptedException {
        driver.get("http://localhost:3000");

        Thread.sleep(3000);

        List<WebElement> foodLinks = driver.findElements(By.xpath("//a[contains(@href, '/food/')]"));

        assertTrue(foodLinks.size() > 0);

        foodLinks.get(0).click();

        Thread.sleep(3000);

        assertTrue(
            driver.getCurrentUrl().toLowerCase().contains("/food/")
            || driver.getPageSource().toLowerCase().contains("add to cart")
            || driver.getPageSource().toLowerCase().contains("price")
        );
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}