package amazontest;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import amazonpages.BasePage;
import amazonpages.SearchJavaBookPage;

public class TestSearchSingleItem extends CommonAPI{


    @Test
    public void searchUsingClickButton1() {
        BasePage bp = new BasePage(getDriver());
        SearchJavaBookPage searchJavaBookPage = new SearchJavaBookPage(getDriver());
        bp.searchElement("java book");
        Boolean searchForIsDisplayed = searchJavaBookPage.searchForPresence();
        Assert.assertTrue(searchForIsDisplayed);
    }

    @Test
    public void searchUsingClickButton2() {
        BasePage bp = new BasePage(getDriver());
        SearchJavaBookPage searchJavaBookPage = new SearchJavaBookPage(getDriver());
        bp.searchElement("java book");
        String resultFor = searchJavaBookPage.getSearchForText();
        System.out.println("expected results for \"java book\"");
        System.out.println("actual result for: "+resultFor);
        Assert.assertEquals(resultFor, "\"java book\"");

    }

    @Test
    public void searchUsingEnter() {
        BasePage bp = new BasePage(getDriver());
        bp.searchElementAndEnter("selenium book");
    }

    @Test
    public void searchForTeether1(){
        BasePage bp = new BasePage(getDriver());
        bp.selectFromMenuDropdown("Baby");
        bp.searchElementAndEnter("teether");
    }

    @Test
    public void searchForTeether2(){
        BasePage bp = new BasePage(getDriver());
        bp.selectOptionFromMenuDropdownList("baby");
        bp.searchElementAndEnter("teether");
    }

    @Test
    public void searchForTV(){
        BasePage bp = new BasePage(getDriver());
        bp.selectFromMenuDropdown("Electronics");
        bp.searchElementAndEnter("Samsung curve");
    }

    //@Test
//    public void searchUsingEnterYouTube() throws InterruptedException {
//        Actions action = new Actions(driver);
//        action.moveToElement(driver.findElement(By.cssSelector("div[id='search-input']"))).sendKeys("selenium course").build().perform();
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#search-icon-legacy")).click();
//        Thread.sleep(3000);
//        //locators
//
//        //Assert.assertEquals("", "");
//
//    }

}
