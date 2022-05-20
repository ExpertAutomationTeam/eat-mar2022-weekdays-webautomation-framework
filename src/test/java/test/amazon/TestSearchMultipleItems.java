package test.amazon;

import base.CommonAPI;
import org.testng.annotations.Test;
import pages.amazon.*;
import utility.DataReader;
import utility.Utility;

import java.util.List;

public class TestSearchMultipleItems extends CommonAPI{

    static String path = Utility.root+"/data/my_data.xlsx";
    String sheet = "Sheet1";
    String header = "items";

    DataReader dr = new DataReader(path);

    @Test
    public void searchMultipleItems(){
        List<String> list = dr.getEntireColumnForGivenHeader(sheet, header);

        for (String item: list){
            BasePage bp = new BasePage(getDriver());
            bp.searchElementAndEnterAndClearField(item);
        }

    }
}
