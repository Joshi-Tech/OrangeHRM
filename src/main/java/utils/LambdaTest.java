package utils;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
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
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("version", "70.0");
            capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one
            capabilities.setCapability("build", "TheInternet");
            capabilities.setCapability("name", "HerokuApp7");
            capabilities.setCapability("network", true); // To enable network logs
            capabilities.setCapability("visual", true); // To enable step by step screenshot
            capabilities.setCapability("video", true); // To enable video recording
            capabilities.setCapability("console", true); // To capture console logs
            return new RemoteWebDriver(new URL("https://" + USER_NAME + ":" + ACCESS_KEY + GRID_URL), capabilities);
        } catch (MalformedURLException e) {
            System.getLogger("Invalid URL for the remote WebDriver server: " + e.getMessage());
        } catch (SessionNotCreatedException e) {
            System.getLogger("Failed to create a session: " + e.getMessage());
        } catch (UnreachableBrowserException e) {
            System.getLogger("Browser is unreachable: " + e.getMessage());
        } catch (WebDriverException e) {
            System.getLogger("General WebDriver error: " + e.getMessage());
        }
        return null;
    }
}
