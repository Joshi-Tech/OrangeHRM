package page;

import dataProvider.ConfigFileReader;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RequiredArgsConstructor
public class Hook {
    public static WebDriver driver;

    @BeforeAll
    public static WebDriver setUp() {
        var browser = new ConfigFileReader().browserType();
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
            default -> throw new IllegalArgumentException("Unsupported driver type: ");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static void tearDown() {
        driver.quit();
    }
}
