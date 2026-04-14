package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class App {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        // Conditional headless setup
        String headless = System.getProperty("headless");

        if ("true".equals(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2: Click on "Products"
        WebElement productsMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))
        );
        productsMenu.click();

        // Step 3: Search "t-shirt"
        WebElement searchBox = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("search_product"))
        );
        searchBox.sendKeys("t-shirt");

        // Step 4: Click Search
        WebElement searchBtn = driver.findElement(By.id("submit_search"));
        searchBtn.click();

        // Step 5: Scroll and click Add to Cart properly
        WebElement firstProduct = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='product-image-wrapper'])[1]")
            )
        );

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", firstProduct);

        // Find Add to Cart button inside product
        WebElement addToCartBtn = firstProduct.findElement(
            By.xpath(".//a[@data-product-id]")
        );

        // Click using JS (bypass hover issues)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", addToCartBtn);

        // Step 6: Click View Cart
        WebElement viewCartBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//u[text()='View Cart']")
            )
        );
        viewCartBtn.click();

        // Step 7: Output
        System.out.println("Product added to cart successfully!");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        Thread.sleep(3000); // fine for demo

        driver.quit();
    }
}
