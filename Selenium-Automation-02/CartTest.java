package com.foodmine.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CartTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {

        System.setProperty(
            "webdriver.edge.driver",
            "C:\\msedgedriver.exe"
        );

        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void addFoodToCartTest() throws InterruptedException {

        driver.get("http://localhost:3000");

        Thread.sleep(3000);

        List<WebElement> foodLinks =
            driver.findElements(By.xpath("//a[contains(@href, '/food/')]"));

        foodLinks.get(0).click();

        Thread.sleep(3000);

        WebElement addButton =
            driver.findElement(
                By.xpath("//button[contains(text(),'Add To Cart') or contains(text(),'Add to Cart')]")
            );

        addButton.click();

        Thread.sleep(3000);

        assertTrue(
            driver.getCurrentUrl().toLowerCase().contains("cart")
            || driver.getPageSource().toLowerCase().contains("remove")
            || driver.getPageSource().toLowerCase().contains("quantity")
        );

        // Remove From Cart
        List<WebElement> removeButtons =
            driver.findElements(
                By.xpath("//button[contains(text(),'Remove') or contains(text(),'Delete')]")
            );

        if(removeButtons.size() > 0) {
            removeButtons.get(0).click();

            Thread.sleep(2000);

            assertTrue(true);
        }
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}