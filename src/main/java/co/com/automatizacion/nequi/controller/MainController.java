package co.com.automatizacion.nequi.controller;

import co.com.automatizacion.nequi.data.dto.DTONequiData;
import co.com.automatizacion.nequi.pages.AccountPage;
import co.com.automatizacion.nequi.pages.HomePage;
import co.com.automatizacion.nequi.pages.LoginPage;
import co.com.automatizacion.nequi.utils.PageUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private PageUtils pageUtils;
    Faker dataGenerator = new Faker();

    public MainController(PageUtils pageUtils) {
        this.pageUtils = pageUtils;
        this.driver = pageUtils.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    public void openLoginPage() {
        String phoneNumber = PageUtils.getPropertyValue("phone.number");
        pageUtils.openMainPage();
        pageUtils.click(homePage.getLoginMenu(), false);
        pageUtils.send(loginPage.getPhoneField(), phoneNumber, false);
        pageUtils.send(loginPage.getPasswordField(),"",false);
    }

    public DTONequiData getNequiData() {
        DTONequiData moneyData = new DTONequiData();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        List<String> balanceTitles = Arrays.asList("Disponible", "Bolsillo", "Colch", "Meta", "Tarjeta", "Congelado", "Total");
        List<Integer> balanceValues = new ArrayList<>();
        pageUtils.isDisplayed(accountPage.getBalancesTitle(),false);
        pageUtils.pause(5);

        for(String title:balanceTitles) {
            By element = By.xpath(String.format("//div[contains(@class, 'row-balance')]//p[contains(.,'%s')]/parent::div/parent::div//p[contains(@class, 'balance-val')]",title));
            WebElement balanceWebElement = driver.findElement(element);
            String balanceValue = pageUtils.getText(balanceWebElement,false);
            balanceValue = balanceValue.replace("$", "");
            balanceValue = balanceValue.replace(",", "");
            balanceValues.add(Math.round(Float.parseFloat(balanceValue)));
        }

        moneyData.setRegisterDate(dtf.format(now));
        moneyData.setDisponible(balanceValues.get(0));
        moneyData.setBolsillos(balanceValues.get(1));
        moneyData.setColchon(balanceValues.get(2));
        moneyData.setMetas(balanceValues.get(3));
        moneyData.setTarjeta(balanceValues.get(4));
        moneyData.setCongelado(balanceValues.get(5));
        moneyData.setTotal(balanceValues.get(6));

        return moneyData;
    }

}
