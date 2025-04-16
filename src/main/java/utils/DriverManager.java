package utils;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utils.StringExtract.getString;


public class DriverManager {
    private static WebDriver driver;
    private final ConfigFileReader configFileReader = new ConfigFileReader();
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

    public WebDriver getDriver() {
        if (driver == null) {
            if (configFileReader.runTests().equalsIgnoreCase("remote")) {
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
        // WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        if (configFileReader.runTestMode().equalsIgnoreCase("true")) {
            logger.info("*********TESTS BEING RUN IN GITHUB ACTION*********");
            chromeOptions.addArguments("--headless");
        }
        logger.info("*********TESTS BEING RUN LOCALLY*********");
        driver = new ChromeDriver(chromeOptions);
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
