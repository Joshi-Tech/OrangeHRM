package managers;


import enums.DriverTypes;
import enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.HomePage;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverManagerInitializer{



    private static WebDriver driver;
    private static DriverTypes  driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";


    public WebDriverManagerInitializer() {
       WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
    }

    public WebDriver getDriver(String browser) {
        if(driver == null) driver = createLocalDriver(browser);
        return driver;
    }

    private WebDriver createLocalDriver(String browser) {
        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "edge" -> {
                WebDriverManager.chromedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported driver type: " + driverType);
        }
        configureDriver(driver);
        return driver;
    }

    private void configureDriver(WebDriver driver) {
        if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(
                FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(),
                TimeUnit.SECONDS
        );
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
