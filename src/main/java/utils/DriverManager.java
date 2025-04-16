package utils;

import dataProvider.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utils.StringExtract.getString;


public class DriverManager {
    private static WebDriver driver;
    private final ConfigFileReader configFileReader = new ConfigFileReader();
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

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
    logger.info("*********TESTS BEING RUN REMOTELY*********");
        try {
            driver = LambdaTest.setCapability();
            driver.get(configFileReader.getApplicationUrl());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize remote driver: " + e.getMessage(), e);
        }
    }

    private void initializeLocalDriver() {
        logger.info("*********TESTS BEING RUN LOCALLY*********");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getString("homePage.url"));
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
