package page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.StringExtract.getListFromString;
import static utils.StringExtract.getString;

public class ContactSalesPage extends BasePage {

    public ContactSalesPage(WebDriver driver) {
        super(driver);
        this.url = getString("contactSalesPage.url");
        this.heading = getString("contactSalesPage.heading");
        this.pageTitle = getString("contactSalesPage.title");
    }

    public void checkElements() {
        assertEquals(Arrays.asList(getString("contactSalesPage.tailoredSolutionTxt"), getString("contactSalesPage.costEffectiveSolutionTxt"),
                        getString("contactSalesPage.personalizedDemonstrationTxt"), getString("contactSalesPage.scalableSolutionsTxt")),
                findByElements("div[class='contact-page-slider-content']>ul>li").stream().map(WebElement::getText).toList());
        assertEquals(getString("contactSalesPage.centerTxt"), findByCss("div[class*='text-center']>h3").getText());
        assertEquals(getListFromString("orangeOrmHeadLinks"),
                findByElements("a[class='nav-link-hed']").stream().map(WebElement::getText).toList());
        assertTrue(findByCss("a[class='navbar-brand nav-logo']").isDisplayed());
        assertTrue(findByLinkText(getString("orangeOrmFreeDemo.buttonTxt")).isDisplayed());
        assertTrue(findByLinkText(getString("OrangeOrmContactSale.buttonTxt")).isDisplayed());
    }
}
