package co.com.automatizacion.nequi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(css = "a[href='/computers']")
    WebElement computersMenu;

    @FindBy(css = "a[href='/register?returnUrl=%2F']")
    WebElement registerMenu;

    @FindBy(css = "a[href='/login?returnUrl=%2F']")
    WebElement loginMenu;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getComputersMenu() {
        return computersMenu;
    }

    public WebElement getRegisterMenu() {
        return registerMenu;
    }

    public WebElement getLoginMenu() {
        return loginMenu;
    }
}
