package utils;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;

public class LambdaTest {

    private static final ConfigFileReader configFileReader = new ConfigFileReader();
    private static final String USER_NAME = configFileReader.getUserName();
    private static final String ACCESS_KEY = configFileReader.getAccessKey();
    private static final String GRID_URL = configFileReader.getGridURL();

    private LambdaTest() {
    }

    public static RemoteWebDriver setCapability() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browserName", "chrome");
            options.setCapability("browserVersion", "70.0");
            options.setCapability("platformName", "win10"); // If this cap isn't specified, it will just get any available one
            options.setCapability("build", "TheInternet");
            options.setCapability("name", "HerokuApp7");
            options.setCapability("network", true); // To enable network logs
            options.setCapability("visual", true); // To enable step by step screenshot
            options.setCapability("video", true); // To enable video recording
            options.setCapability("console", true); // To capture console logs
            return new RemoteWebDriver(new URL("https://" + USER_NAME + ":" + ACCESS_KEY + GRID_URL), options);
        } catch (MalformedURLException e) {
            System.getLogger("Invalid URL for the remote WebDriver server: " + e.getMessage());
        } catch (UnreachableBrowserException e) {
            System.getLogger("Browser is unreachable: " + e.getMessage());
        } catch (WebDriverException e) {
            System.getLogger("General WebDriver error: " + e.getMessage());
        }
        return null;
    }
}
