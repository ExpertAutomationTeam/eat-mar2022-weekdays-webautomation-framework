package test.amazon;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.amazon.*;
import utility.DataReader;
import utility.Utility;

import java.io.IOException;

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

    @DataProvider
    public Object[][] readVariant() throws IOException {
        DataReader dr = new DataReader(Utility.root+"/data/my_data.xlsx");
        return dr.readVariant("Sheet1");
    }

    @Test(dataProvider = "readVariant")
    public void searchMultipleItemsWithDataProvider(String id, String item) {
        System.out.println(id);
        BasePage bp = new BasePage(getDriver());
        bp.searchElementAndEnterAndClearField(item);
    }

}
