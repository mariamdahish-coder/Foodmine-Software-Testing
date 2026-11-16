package com.foodmine.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class SearchTest {

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
    public void searchExistingFoodTest() throws InterruptedException {
        driver.get("http://localhost:3000");

        driver.findElement(By.tagName("input")).sendKeys("Pizza");
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(3000);

        assertTrue(driver.getPageSource().contains("Pizza"));
    }

    @Test
    public void searchInvalidFoodTest() throws InterruptedException {
        driver.get("http://localhost:3000");

        driver.findElement(By.tagName("input")).sendKeys("ssssss");
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(3000);

        assertTrue(driver.getPageSource().toLowerCase().contains("nothing found"));
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}