package test.amazon;

import base.CommonAPI;
import org.testng.annotations.Test;
import pages.amazon.*;
import utility.DataReader;
import utility.Utilities;

import java.util.List;

public class TestSearchMultipleItems extends CommonAPI{

    String path = Utilities.PATH+"/data/my_data.xlsx";
    String sheet = "Sheet1";
    String header = "items";

    DataReader dr = new DataReader();

    @Test
    public void searchMultipleItems(){
        List<String> list = dr.getEntireColumnForGivenHeader(path, sheet, header);

        for (String item: list){
            BasePage bp = new BasePage(getDriver());
            bp.searchElementAndEnterAndClearField(item);
        }

    }
}
