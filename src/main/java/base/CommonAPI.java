package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    String dirPath = System.getProperty("user.dir");

    public WebDriver driver;

    public WebDriver getDriver(String browserName, String os){
        if (browserName.equalsIgnoreCase("chrome")){
            if (os.equalsIgnoreCase("mac")){
                System.setProperty("webdriver.chrome.driver", dirPath+"/src/driver/chromedriver");
            }else if(os.equalsIgnoreCase("windows")){
                System.setProperty("webdriver.chrome.driver", dirPath+"/src/driver/chromedriverexe");
            }
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            if (os.equalsIgnoreCase("mac")){
                System.setProperty("webdriver.gecko.driver", dirPath+"/src/driver/geckodriver");
            }else if (os.equalsIgnoreCase("windows")){
                System.setProperty("webdriver.gecko.driver", dirPath+"/src/driver/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        return driver;
    }

    @Parameters({"browserName","os","url"})
    @BeforeMethod
    public void beforeTest(String browserName, String os, String url) {
        getDriver(browserName, os);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterMethod
    public void afterTest(){
        driver.close();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getElementText(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).getText();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).getText();
        }
    }

    public void click(String locator){
        try {
            driver.findElement(By.cssSelector(locator)).click();
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).click();
        }
    }

    public void clear(String locator){
        try {
            driver.findElement(By.cssSelector(locator)).clear();
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).clear();
        }
    }

    public void type(String locator, String text){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }
    }
    public void typeAndEnter(String locator, String text){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text, Keys.ENTER);
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).sendKeys(text, Keys.ENTER);
        }
    }
    public void selectFromDropdown(String locator, String option){
        try{
            WebElement dropdown = driver.findElement(By.cssSelector(locator));
            Select select =new Select(dropdown);
            try{
                select.selectByVisibleText(option);
            }catch (Exception e){
                select.selectByValue(option);
            }
        }catch (Exception e){
            WebElement dropdown = driver.findElement(By.xpath(locator));
            Select select =new Select(dropdown);
            try{
                select.selectByVisibleText(option);
            }catch (Exception e1){
                select.selectByValue(option);
            }
        }
    }

    public void selectOptionFromDropdownList(String locator, String option){
        try {
            WebElement dropdown = driver.findElement(By.cssSelector(locator));
            Select select = new Select(dropdown);
            List<WebElement> list = select.getOptions();
            for (WebElement element: list) {
                if (element.getText().equalsIgnoreCase(option)){
                    element.click();
                }
            }
        }catch (Exception e){
            WebElement dropdown = driver.findElement(By.xpath(locator));
            Select select = new Select(dropdown);
            List<WebElement> list = select.getOptions();
            for (WebElement element: list) {
                if (element.getText().equalsIgnoreCase(option)){
                    element.click();
                }
            }
        }

    }

    public void hoverOver(String locator){
        Actions actions = new Actions(driver);
        try{
            actions.moveToElement(driver.findElement(By.cssSelector(locator))).build().perform();
        }catch (Exception e){
            actions.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
        }
    }

    public boolean isPresent(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).isDisplayed();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).isDisplayed();
        }
    }
}
