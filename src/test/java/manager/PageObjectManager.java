package manager;

import org.openqa.selenium.WebDriver;
import page.ContactSalesPage;
import page.HomePage;

public class PageObjectManager {
    private final WebDriver driver;

    private HomePage homePage;
    private ContactSalesPage contactSalesPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public ContactSalesPage getContactSalesPage() {
        if (contactSalesPage == null) {
            contactSalesPage = new ContactSalesPage(driver);
        }
        return contactSalesPage;
    }
}
