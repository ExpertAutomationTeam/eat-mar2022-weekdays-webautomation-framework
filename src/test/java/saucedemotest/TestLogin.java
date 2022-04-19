package saucedemotest;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemopages.HomePage;
import saucedemopages.LoginPage;
import utility.Utilities;

public class TestLogin extends CommonAPI{

    String validUsername = Utilities.loadProperties().getProperty("saucedemo.username");
    String validPassword = Utilities.loadProperties().getProperty("saucedemo.password");
    String lockedUser = Utilities.loadProperties().getProperty("saucedemo.lockeduser");
    String wrongPassword = "wrongpassword";

    //positive test case
    @Test
    public void validLoginCred(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginBtn();
        String actualText = homePage.getProductsElementText();
        String expectedText = "PRODUCTS";
        Assert.assertEquals(actualText, expectedText);
    }

    //negative test cases
    @Test
    public void lockedLoginCred(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername(lockedUser);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void wrongPassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(wrongPassword);
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}
