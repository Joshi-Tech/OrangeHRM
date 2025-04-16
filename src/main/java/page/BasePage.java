package page;

import dataProvider.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasePage {
    private static WebDriver driver;
    public String url;
    public String pageTitle;
    public String previousUrl;
    public String heading;

    public String baseUrl() {
        return new ConfigFileReader().getApplicationUrl();
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static void getDriver() {
        WebDriverManager.chromedriver().setup();
        ;
    }

    public void backLink() {
        driver.navigate().back();
    }

    public WebElement findByCss(String css) {
        return driver.findElement(By.cssSelector(css));
    }

    public WebElement findById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement findByLinkText(String linkText) {
        return driver.findElement(By.linkText(linkText));
    }

    public WebElement findByText(String text) {
        return driver.findElement(By.xpath(text));
    }

    public List<WebElement> findByElements(String css) {
        return driver.findElements(By.cssSelector(css));
    }

    public void clickByLinkText(String linkText) {
        findByLinkText(linkText).click();
    }

    public void navigateTo() {
        driver.navigate().to(baseUrl());
    }

    public void on() {
       /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(url));*/
        assertHeading();
    }

    private void assertHeading() {
        String expectedHeading = this.heading;
        String actualHeading = findByCss("[class*='slider-content']>h1").getText();
        assertEquals(expectedHeading, actualHeading);
    }

    private void assertBackLink() {
        backLink();
        String expectedUrl = this.previousUrl;
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    public void validate() {
        assertBackLink();
        assertHeading();
        // ass
    }
}
