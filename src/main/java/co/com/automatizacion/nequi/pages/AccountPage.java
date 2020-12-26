package co.com.automatizacion.nequi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    @FindBy(xpath = "//div[@class='welcome-box-info']/h3")
    WebElement balancesTitle;

    @FindBy(xpath = "//div[contains(@class, 'row-balance')]//p[contains(., 'Bolsillo')]")
    WebElement bolsillosTitle;

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getBalancesTitle() {
        return balancesTitle;
    }

    public WebElement getBolsillosTitle() {
        return bolsillosTitle;
    }
}
