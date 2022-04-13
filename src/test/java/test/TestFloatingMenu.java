package test;

import base.CommonAPI;
import org.testng.Assert;

import org.testng.annotations.Test;


public class TestFloatingMenu extends CommonAPI{


    @Test
    public void showWatchList(){
        hoverOver("span[id='nav-link-accountList-nav-line-1']");
        click("//div[@id='nav-al-your-account']/a[5]/span");
        String signInPageTitle = getPageTitle();
        Assert.assertEquals(signInPageTitle, "Amazon Sign-In");
    }

}
