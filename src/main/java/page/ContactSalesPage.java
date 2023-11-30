package page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Arrays;

import static utils.StringExtract.getListFromString;
import static utils.StringExtract.getString;

//@AllArgsConstructor
//@NoArgsConstructor
public class ContactSalesPage extends BasePage {
    private WebDriver driver;
    @FindBy(how = How.ID, id = "#Form_getForm_FullName")
    private WebElement fullName;

    public ContactSalesPage(WebDriver driver) {
        super(driver);
        this.url = "https://www.orangehrm.com/en/contact-sales/";
        this.heading = "Contact Sales";
        this.pageTitle = "Get in Touch with OrangeHRM Sales | OrangeHRM";
        //PageFactory.initElements(driver,this);
    }

    public void checkElements() {
        Assert.assertEquals(Arrays.asList(getString("contactSalesPage.tailoredSolutionTxt"), getString("contactSalesPage.costEffectiveSolutionTxt"),
                        getString("contactSalesPage.personalizedDemonstrationTxt"), getString("contactSalesPage.scalableSolutionsTxt")),
                findByElements("div[class='contact-page-slider-content']>ul>li").stream().map(WebElement::getText).toList());
        Assert.assertEquals(getString("contactSalesPage.centerTxt"), findByCss("div[class*='text-center']>h3").getText());
        Assert.assertEquals(getListFromString("orangeOrmHeadLinks"),
                findByElements("a[class='nav-link-hed']").stream().map(WebElement::getText).toList());
        Assert.assertTrue(findByCss("a[class='navbar-brand nav-logo']").isDisplayed());
        Assert.assertTrue(findByLinkText(getString("orangeOrmFreeDemo.buttonTxt")).isDisplayed());
        Assert.assertTrue(findByLinkText(getString("OrangeOrmContactSale.buttonTxt")).isDisplayed());
    }

    public void setFullName() {
        fullName.sendKeys("Ram");
    }
}
