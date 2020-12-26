package co.com.automatizacion.nequi.controller;

import co.com.automatizacion.nequi.pages.AccountPage;
import co.com.automatizacion.nequi.pages.HomePage;
import co.com.automatizacion.nequi.utils.PageUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

public class MainController {

    private WebDriver driver;
    private HomePage homePage;
    private AccountPage accountPage;
    private PageUtils pageUtils;
    Faker dataGenerator = new Faker();

    public MainController(PageUtils pageUtils) {
        this.pageUtils = pageUtils;
        this.driver = pageUtils.getDriver();
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
    }
}
