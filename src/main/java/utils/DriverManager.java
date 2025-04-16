package utils;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
        switch (configFileReader.browserType()) {
            case "chrome" -> {
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
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                if (configFileReader.runTestMode().equalsIgnoreCase("true")) {
                    logger.info("*********TESTS BEING RUN IN GITHUB ACTION*********");
                    edgeOptions.addArguments("--headless");
                }
                logger.info("*********TESTS BEING RUN LOCALLY*********");
                driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
                driver.get(getString("homePage.url"));
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (configFileReader.runTestMode().equalsIgnoreCase("true")) {
                    logger.info("*********TESTS BEING RUN IN GITHUB ACTION*********");
                    firefoxOptions.addArguments("--headless");
                }
                logger.info("*********TESTS BEING RUN LOCALLY*********");
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                driver.get(getString("homePage.url"));
            }
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
