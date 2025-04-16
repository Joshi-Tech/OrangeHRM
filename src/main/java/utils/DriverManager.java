package utils;

import dataProvider.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static utils.StringExtract.getString;


public class DriverManager {
    private static WebDriver driver;
    private final ConfigFileReader configFileReader = new ConfigFileReader();

    public WebDriver getDriver() {
        if (driver == null) {
            if (configFileReader.runTestMode().equalsIgnoreCase("remote")) {
                initializeRemoteDriver();
            } else {
                initializeLocalDriver();
            }
        }
        return driver;
    }

    private void initializeRemoteDriver() {
        try {
            driver = LambdaTest.setCapability();
            driver.get(configFileReader.getApplicationUrl());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize remote driver: " + e.getMessage(), e);
        }
    }

    private void initializeLocalDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getString("homePage.url"));
    }

    public  void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
