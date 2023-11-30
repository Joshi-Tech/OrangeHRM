package page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utils.StringExtract.getListFromString;
import static utils.StringExtract.getString;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        this.url = "https://www.orangehrm.com/";
       this.heading = "Peace of mind is just a few clicks away!";
        this.pageTitle = "OrangeHRM HR Software | OrangeHRM";
    }

    public void checkElements() {
        Assert.assertEquals(getString("homePage.subheading"), findByCss("div[class='homepage-slider-content']>p").getText());
        Assert.assertEquals(getString("homePage.emailTxtBox"), findByCss("input[type='email']").getAttribute("placeholder"));
        Assert.assertEquals(getString("homePage.tryItFreeBtn"), findByCss("input[type='submit']").getAttribute("value"));
        Assert.assertEquals(getString("homePage.centerTxt"), findByCss("h3[class='text-center']").getText());
        Assert.assertEquals(getString("homePage.someClientTxt"), findByCss("p[class='text-center sub-title']").getText());
        Assert.assertEquals(getString("homePage.hrForAllTxt"), findByCss("div[class='homepage-hrforall-content']>h3").getText());
        Assert.assertEquals(getListFromString("orangeOrmHeadLinks"),
                findByElements("a[class='nav-link-hed']").stream().map(WebElement::getText).toList());
        Assert.assertTrue(findByCss("a[class='navbar-brand nav-logo']").isDisplayed());
        Assert.assertTrue(findByLinkText(getString("orangeOrmFreeDemo.buttonTxt")).isDisplayed());
        Assert.assertTrue(findByLinkText(getString("OrangeOrmContactSale.buttonTxt")).isDisplayed());
    }
}
