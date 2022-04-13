package test;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSearchSingleItem extends CommonAPI{


    @Test
    public void searchUsingClickButton1() {
        type("#twotabsearchtextbox", "java book");
        click("#nav-search-submit-button");
        Boolean searchForIsDisplayed = isPresent(".a-color-state.a-text-bold");
        Assert.assertTrue(searchForIsDisplayed);

    }

    @Test
    public void searchUsingClickButton2() {
        type("#twotabsearchtextbox", "java book");
        click("#nav-search-submit-button");
        String resultFor = getElementText(".a-color-state.a-text-bold");
        System.out.println("expected results for \"java book\"");
        System.out.println("actual result for: "+resultFor);
        Assert.assertEquals(resultFor, "\"java book\"");

    }

    @Test
    public void searchUsingEnter() {
        typeAndEnter("#twotabsearchtextbox", "selenium book");
    }

    @Test
    public void searchForTeether1(){
        selectFromDropdown("#searchDropdownBox", "Baby");
        typeAndEnter("#twotabsearchtextbox", "teether");
    }

    @Test
    public void searchForTeether2(){
        selectOptionFromDropdownList("#searchDropdownBox", "baby");
        typeAndEnter("#twotabsearchtextbox", "teether");
    }

    @Test
    public void searchForTV(){
        selectFromDropdown("#searchDropdownBox", "Electronics");
        typeAndEnter("#twotabsearchtextbox", "Samsung curve");
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
