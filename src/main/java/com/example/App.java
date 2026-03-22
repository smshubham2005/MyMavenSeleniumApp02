package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class App {
    public static void main(String[] args) throws InterruptedException {

        // Step 1: Open Chrome and maximize window
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2: Click on "Products" from navbar
        WebElement productsMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))
        );
        productsMenu.click();

        // Step 3: Find the search box and search for "t-shirt"
        WebElement searchBox = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("search_product"))
        );
        searchBox.sendKeys("t-shirt");

        // Step 4: Click the Search button
        WebElement searchBtn = driver.findElement(By.id("submit_search"));
        searchBtn.click();

        // Step 5: Wait for results and hover/click "Add to Cart" on the 1st product
        WebElement firstAddToCart = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='product-image-wrapper']//a[@data-product-id])[1]")
            )
        );
        firstAddToCart.click();

        // Step 6: Handle the popup - click "Continue Shopping" to stay or "View Cart"
        WebElement viewCartBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']")
            )
        );
        viewCartBtn.click();

        // Step 7: Cart page is now displayed
        System.out.println("Product added to cart successfully!");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Optional: pause to see the result before browser closes
        Thread.sleep(5000);

        driver.quit();
    }
}
