package test.saucedemo;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.saucedemo.HomePage;
import pages.saucedemo.LoginPage;
import utility.Utility;

public class TestLogin extends CommonAPI{
    String validUsername = Utility.loadProperties().getProperty("saucedemo.username");
    String validPassword = Utility.loadProperties().getProperty("saucedemo.password");
    String lockedUser = Utility.loadProperties().getProperty("saucedemo.lockeduser");
    String wrongPassword = "wrongpassword";

    //positive test case
    //@Test
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
    //@Test
    public void lockedLoginCred(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername(lockedUser);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    //@Test
    public void wrongPassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(wrongPassword);
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    //get data with data provider
    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {
        // The number of times data is repeated, test will be executed the same no. of times
        // Here it will execute once
        return new Object[][] {{"standard_user", "secret_sauce"}};
    }

    @Test(dataProvider = "Authentication")
    public void validLoginCredWithDataProvider(String username, String password){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginBtn();
        String actualText = homePage.getProductsElementText();
        String expectedText = "PRODUCTS";
        Assert.assertEquals(actualText, expectedText);
    }
}
