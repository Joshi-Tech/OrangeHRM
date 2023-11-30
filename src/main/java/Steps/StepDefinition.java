package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;
import managers.PageObjectManager;
import page.HomePage;

import static constants.Constants.CONTACT_SALES;
import static constants.Constants.HOME_PAGE;
import static managers.PageObjectManager.getContactSalesPage;
import static managers.PageObjectManager.getHomePage;
import static page.Hook.*;

@RequiredArgsConstructor
public class StepDefinition {

    @Given("they are on {string}")
    public void they_are_on(String page) {
        switch (page.toLowerCase()) {
            case HOME_PAGE -> {
               getHomePage().navigateTo();
                getHomePage().on();
            }
            case CONTACT_SALES -> {
                they_are_on(HOME_PAGE);
                getHomePage().clickByLinkText("Contact Sales");
                getContactSalesPage().on();
            }
            default -> throw new IllegalArgumentException("Invalid section");
        }
    }

    @Then("the {string} will be correct")
    public void the_will_be_correct(String page) {
        switch (page.toLowerCase()) {
            case HOME_PAGE -> {
                getHomePage().checkElements();
            }
            case CONTACT_SALES -> {
                getContactSalesPage().checkElements();
            }
        }
    }
}
