package managers;

import org.openqa.selenium.WebDriver;
import page.BasePage;
import page.ContactSalesPage;
import page.HomePage;

import static page.Hook.setUp;

public class PageObjectManager {
    private static HomePage homePage;
    private static BasePage basePage;
    private static ContactSalesPage contactSalesPage;
    private static final WebDriver driver = setUp();

    public PageObjectManager() {
        basePage = new BasePage(driver);

        driver.get(basePage.baseUrl());
    }

    public static HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public static ContactSalesPage getContactSalesPage() {
        return (contactSalesPage == null) ? contactSalesPage = new ContactSalesPage(driver) : contactSalesPage;
    }
}
