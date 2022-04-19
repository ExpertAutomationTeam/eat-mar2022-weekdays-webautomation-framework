package amazontest;

import base.CommonAPI;
import org.testng.Assert;

import org.testng.annotations.Test;
import amazonpages.BasePage;


public class TestFloatingMenu extends CommonAPI{


    @Test
    public void showWatchList(){
        BasePage basePage = new BasePage(getDriver());
        //LoginPage loginPage = new LoginPage(getDriver());
        basePage.hoverOverFloatingMenu(getDriver());
        basePage.clickOnWatchListLink();
        //String signInPageTitle = loginPage.getLoginPageTitle();
        String signInPageTitle = getPageTitle();
        Assert.assertEquals(signInPageTitle, "Amazon Sign-In");
    }

}
