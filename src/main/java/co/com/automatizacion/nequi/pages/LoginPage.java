package co.com.automatizacion.nequi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "j_username")
    WebElement phoneField;

    @FindBy(id = "j_password")
    WebElement passwordField;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getPhoneField() {
        return phoneField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }
}
