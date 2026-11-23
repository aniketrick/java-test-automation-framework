package com.aniket.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

abstract class BaseUiTest {

    protected WebDriver driver;
    protected static final String BASE_URL =
        System.getProperty("uiBaseUrl", "https://www.saucedemo.com");

    @BeforeEach
    void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1280,800");
        options.setExperimentalOption("prefs", java.util.Map.of(
            "credentials_enable_service", false,
            "profile.password_manager_enabled", false,
            "profile.password_manager_leak_detection", false
        ));
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}