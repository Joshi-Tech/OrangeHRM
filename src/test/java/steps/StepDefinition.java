package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.PageObjectManager;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

import static constants.Constants.CONTACT_SALES;
import static constants.Constants.HOME_PAGE;

public class StepDefinition {
    private WebDriver driver;

    private final PageObjectManager pageObjectManager = new PageObjectManager(new DriverManager().getDriver());

    @Given("they are on {string}")
    public void they_are_on(String page) {
        switch (page.toLowerCase()) {
            case HOME_PAGE -> {
                pageObjectManager.getHomePage().navigateTo();
                pageObjectManager.getHomePage().on();
            }
            case CONTACT_SALES -> {
                they_are_on(HOME_PAGE);
                pageObjectManager.getHomePage().clickByLinkText("Contact Sales");
                pageObjectManager.getContactSalesPage().on();
            }
            default -> throw new IllegalArgumentException("Invalid section");
        }
    }

    @Then("the {string} will be correct")
    public void the_will_be_correct(String page) {
        switch (page.toLowerCase()) {
            case HOME_PAGE -> pageObjectManager.getHomePage().checkElements();
            case CONTACT_SALES -> pageObjectManager.getContactSalesPage().checkElements();
        }
    }
}