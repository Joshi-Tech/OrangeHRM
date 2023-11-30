package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AboutUsPage {
    @FindBy(how = How.CSS,using = ".title")
    private WebElement h1Heading;

    public AboutUsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public String getHeading(){
        return h1Heading.getText().trim();
    }
}