package co.com.automatizacion.nequi.utils;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageUtils {

    private WebDriver driver;
    private final Random rand = new Random();
    private WebDriverWait wait;
    private static String scenarioName;
    String featureName = "";
    String stepName = "";

    public PageUtils() {
        featureName = getTestNames()[0];
        stepName = getTestNames()[1];
    }

    public PageUtils(boolean startDriver) {
        if(startDriver) {
            startDriver();
        }
        featureName = getTestNames()[0];
        stepName = getTestNames()[1];
    }

    public static String getScenarioName() {
        return scenarioName;
    }

    public static void setScenarioName(String scenarioName) {
        PageUtils.scenarioName = scenarioName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getStepName() {
        return stepName;
    }

    private String[] getTestNames() {
        String[] testNames = new String[2];
        Thread.currentThread().getStackTrace();
        String regexDatePattern = "step[s]?\\.(\\w+)";
        Pattern datePattern = Pattern.compile(regexDatePattern);

        for (StackTraceElement stackTraceElement: Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().contains("step")) {
                Matcher dateRegexMatch = datePattern.matcher(stackTraceElement.getClassName());
                dateRegexMatch.find();
                testNames[0] = dateRegexMatch.group(1);
                testNames[1] = stackTraceElement.getMethodName();
            }
        }
        return testNames;
    }

    public void startDriver() {
        String driverType = PageUtils.getPropertyValue("test.run.mode");
        if(driverType.contains("local")) {
            if(driverType.contains("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                wait = new WebDriverWait(driver, 10);
            }
            else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                wait = new WebDriverWait(driver, 10);
            }
        }
        else if(driverType.contains("remote")) {
            try {
                String host = "localhost";
                DesiredCapabilities desiredCapabilities;

                if(System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
                    desiredCapabilities = DesiredCapabilities.firefox();
                }
                else {
                    desiredCapabilities = DesiredCapabilities.chrome();
                }
                if(System.getProperty("HUB_HOST") != null) {
                    host = System.getProperty("HUB_HOST");
                }

                String url = "http://" + host + ":4444/wd/hub";
                driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
                wait = new WebDriverWait(driver, 10);
            }catch (Exception e) {
                LogWriter.testErrorLog("Error al iniciar el webdriver" + e.getMessage());
            }
        }

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void openMainPage() {
        LogWriter.pageInfoLog("Ingresando a la pagina principal");

        try {
            driver.get("https://demo.nopcommerce.com/");
            driver.manage().window().maximize();
        }catch (Exception e) {
            LogWriter.testErrorLog("Error al tratar de ingresar a la pagina principal" + e.getMessage());
        }
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void closeBrowser(String closeMessage) {
        LogWriter.pageInfoLog(closeMessage);
        driver.quit();
    }

    public void startTestLog() {
        LogWriter.testInfoLog(String.format("Iniciando el escenario %s de la feature %s", getScenarioName(),getFeatureName()));
    }

    public void waitUntil(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element, boolean makeScreenshot) {
        waitUntil(element);
        element.click();

        if(makeScreenshot) {
            takeScreenshot();
        }
    }

    public void send(WebElement element, String data, boolean makeScreenshot) {
        waitUntil(element);
        element.clear();
        element.sendKeys(data);

        if(makeScreenshot) {
            takeScreenshot();
        }
    }

    public String getText(WebElement element, boolean makeScreenshot) {
        waitUntil(element);
        if(makeScreenshot) {
            takeScreenshot();
        }
        return element.getText();
    }

    public void selectDropdown(WebElement element, boolean makeScreenshot) {
        waitUntil(element);
        Select selector = new Select(element);
        int optionAmount = selector.getOptions().size();
        int randomIndex = rand.nextInt(optionAmount);
        selector.selectByIndex(randomIndex);

        if(makeScreenshot) {
            takeScreenshot();
        }
    }

    public void selectDropdownByText(WebElement element, String data, boolean makeScreenshot) {
        waitUntil(element);
        Select dropdownSelector = new Select(element);
        dropdownSelector.selectByVisibleText(data);

        if(makeScreenshot) {
            takeScreenshot();
        }
    }

    public void selectDropdownByValue(WebElement element, String data, boolean makeScreenshot) {
        waitUntil(element);
        Select dropdownSelector = new Select(element);
        dropdownSelector.selectByValue(data);

        if(makeScreenshot) {
            takeScreenshot();
        }
    }

    public boolean isDisplayed(WebElement element, boolean makeScreenshot) {
        if(makeScreenshot) {
            takeScreenshot();
        }
        return element.isDisplayed();
    }

    private void takeScreenshot(){
        var camera = (TakesScreenshot)driver;
        featureName = getTestNames()[0];
        stepName = getTestNames()[1];

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss.SSS");
        LocalDateTime currentTimeObject = LocalDateTime.now();
        String currentTime = dateTimeFormatter.format(currentTimeObject);
        String folderPath = "src/test/resources/screenshots/"+featureName+"/"+scenarioName+"/";

        String regexDatePattern = "^[a-z]+[A-Z][a-z]+[A-Z]?[a-z]+";
        Pattern stepNamePattern = Pattern.compile(regexDatePattern);
        Matcher stepNameRegexMatch = stepNamePattern.matcher(stepName);
        stepNameRegexMatch.find();
        stepName = stepNameRegexMatch.group(0);

        String filePath = folderPath+currentTime+"_"+stepName+".png";

        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(screenshot, new File(filePath));
        } catch (IOException e) {
            LogWriter.testErrorLog("Unable to save the screenshot in the specified folder.\n" + e.getMessage());
        }
    }

    public Map<String, String> captureDate(String date) {
        date = date.replaceAll("\\b0", "");
        String regexDatePattern = "(\\d{4})-(\\d{1,2})-(\\d{1,2})";
        Pattern datePattern = Pattern.compile(regexDatePattern);
        Matcher dateRegexMatch = datePattern.matcher(date);
        dateRegexMatch.find();

        Map<String, String> dateDictionary = new HashMap<>();
        dateDictionary.put("day", dateRegexMatch.group(3));
        dateDictionary.put("month", dateRegexMatch.group(2));
        dateDictionary.put("year", dateRegexMatch.group(1));

        return dateDictionary;
    }

    public static String getPropertyValue(String property)  {
        String result = "";
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            String propFileName = "configuration.properties";

            inputStream = PageUtils.class.getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputStream);

            result = prop.getProperty(property);

        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
