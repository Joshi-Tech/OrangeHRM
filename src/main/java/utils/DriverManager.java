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
        logger.info("*********TESTS BEING RUN REMOTELY IN LAMBDA TEST*********");
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
                switch (configFileReader.runTestMode()) {
                    case "true" -> {
                        switch (configFileReader.runTests()) {
                            case "remote" -> {
                                logger.info("*********TESTS BEING RUN IN GITHUB ACTION USING CHROME BROWSER*********");
                                chromeOptions.addArguments("--headless");
                            }
                            case "local" -> {
                                logger.info("*********TESTS BEING RUN LOCALLY IN CHROME IN HEADLESS MODE*********");
                                chromeOptions.addArguments("--headless");
                            }

                        }
                    }
                    case "false" -> logger.info("*********TESTS BEING RUN LOCALLY USING CHROME BROWSER*********");
                }
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                driver.get(getString("homePage.url"));
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                switch (configFileReader.runTestMode()) {
                    case "true" -> {
                        switch (configFileReader.runTests()) {
                            case "remote" -> {
                                logger.info("*********TESTS BEING RUN IN GITHUB ACTION USING EDGE BROWSER*********");
                                edgeOptions.addArguments("--headless");
                            }
                            case "local" -> {
                                logger.info("*********TESTS BEING RUN LOCALLY IN HEADLESS MODE USING EDGE BROWSER*********");
                                edgeOptions.addArguments("--headless");
                            }

                        }
                    }
                    case "false" -> logger.info("*********TESTS BEING RUN LOCALLY USING EDGE BROWSER*********");
                }
                driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
                driver.get(getString("homePage.url"));
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                switch (configFileReader.runTestMode()) {
                    case "true" -> {
                        switch (configFileReader.runTests()) {
                            case "remote" -> {
                                logger.info("*********TESTS BEING RUN IN GITHUB ACTION USING FIREFOX BROWSER*********");
                                firefoxOptions.addArguments("--headless");
                            }
                            case "local" -> {
                                logger.info("*********TESTS BEING RUN LOCALLY IN HEADLESS MODE USING FIREFOX BROWSER*********");
                                firefoxOptions.addArguments("--headless");
                            }

                        }
                    }
                    case "false" -> logger.info("*********TESTS BEING RUN LOCALLY USING FIREFOX BROWSER*********");
                }
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
