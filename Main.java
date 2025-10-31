package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//created by: youssef mohamed mahmoud
public class Main {
    public static void main(String[] args) {
        // Set path to ChromeDriver (optional if it's already in PATH)
        System.setProperty("webdriver.chrome.driver", "C:\\Browser Drivers\\chromedriver.exe");

        // 1️⃣ Open Chrome
        WebDriver driver = new ChromeDriver();

        try {
            // 2️⃣ Navigate to the website
            driver.get("https://www.saucedemo.com/v1/index.html");
            driver.manage().window().maximize();
            System.out.println("Opened SauceDemo website");

            // 3️⃣ Enter Username
            WebElement usernameField = driver.findElement(By.id("user-name"));
            usernameField.sendKeys("standard_user");

            // 4️⃣ Enter Password
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("secret_sauce");

            // 5️⃣ Click Login Button
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            // 6️⃣ Verify successful login (check URL)
            String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
            String actualUrl = driver.getCurrentUrl();

            if (actualUrl.equals(expectedUrl)) {
                System.out.println("✅ Login successful. URL is correct.");
            } else {
                System.out.println("❌ Login failed. Expected URL: " + expectedUrl + " but got: " + actualUrl);
            }

            // 7️⃣ Verify Page Title
            WebElement pageTitle = driver.findElement(By.className("product_label"));
            String actualTitle = pageTitle.getText();

            if (actualTitle.equals("Products")) {
                System.out.println("✅ Page title is correct: " + actualTitle);
            } else {
                System.out.println("❌ Page title is incorrect: " + actualTitle);
            }

            // 8️⃣ Click "Add to Cart" for the first product
            WebElement addToCartButton = driver.findElement(By.xpath("(//button[@class='btn_primary btn_inventory'])[1]"));
            addToCartButton.click();
            System.out.println("Clicked on 'Add to cart' for the first product");

            // 9️⃣ Click on the Cart icon (top right)
            WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
            cartIcon.click();
            System.out.println("Clicked on Cart icon");

            // 🔟 Assert that you navigated to "Your Cart" page
            String expectedCartUrl = "https://www.saucedemo.com/v1/cart.html";
            String actualCartUrl = driver.getCurrentUrl();

            if (actualCartUrl.equals(expectedCartUrl)) {
                System.out.println("✅ Navigated to Your Cart page successfully.");
            } else {
                System.out.println("❌ Failed to navigate to Your Cart. Current URL: " + actualCartUrl);
            }

            // 11️⃣ Check the Page Title of the Cart
            WebElement cartTitle = driver.findElement(By.className("subheader"));
            String actualCartTitle = cartTitle.getText();

            if (actualCartTitle.equals("Your Cart")) {
                System.out.println("✅ Page title after click is correct: " + actualCartTitle);
            } else {
                System.out.println("❌ Page title after click is incorrect: " + actualCartTitle);
            }

        } catch (Exception e) {
            System.out.println("❌ Test failed due to exception: " + e.getMessage());
        } finally {
            // 12️⃣ Close the browser
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}