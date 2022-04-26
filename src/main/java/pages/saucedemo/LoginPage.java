package pages.saucedemo;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonAPI{

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='user-name']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-button']")
    WebElement loginBtn;

    @FindBy(xpath = "//input[@id='password']/../following-sibling::div/h3")
    WebElement errorMessage;

    //reusable steps
    public void enterUsername(String username){
        type(usernameField, username);
    }
    public void enterPassword(String password){
        type(passwordField, password);
    }
    public void clickOnLoginBtn(){
        click(loginBtn);
    }
    public String getErrorMessage(){
        return getElementText(errorMessage);
    }
}
