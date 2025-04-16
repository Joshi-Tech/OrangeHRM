package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.StringExtract.getListFromString;
import static utils.StringExtract.getString;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        this.url = getString("homePage.url");
        this.heading = getString("homePage.heading");
        this.pageTitle = getString("homePage.title");
    }

    public void checkElements() {
        assertEquals(getString("homePage.subheading"), findByCss("div[class='homepage-slider-content']>p").getText());
        assertEquals(getString("homePage.emailTxtBox"), findByCss("input[type='email']").getAttribute("placeholder"));
        assertEquals(getString("homePage.freeTrial"), findByCss("input[type='submit']").getAttribute("value"));
        assertEquals(getString("homePage.centerTxt"), findByCss("h3[class='text-center']").getText());
        assertEquals(getString("homePage.someClientTxt"), findByCss("p[class='text-center sub-title']").getText());
        assertEquals(getString("homePage.hrForAllTxt"), findByCss("div[class='homepage-hrforall-content']>h3").getText());
        assertEquals(getListFromString("orangeOrmHeadLinks"),
                findByElements("a[class='nav-link-hed']").stream().map(WebElement::getText).toList());
        assertTrue(findByCss("a[class='navbar-brand nav-logo']").isDisplayed());
        assertTrue(findByLinkText(getString("orangeOrmFreeDemo.buttonTxt")).isDisplayed());
        assertTrue(findByLinkText(getString("OrangeOrmContactSale.buttonTxt")).isDisplayed());
    }
}
