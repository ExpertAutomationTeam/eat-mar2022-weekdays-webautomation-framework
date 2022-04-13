package test;

import base.CommonAPI;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSearchMultipleItems extends CommonAPI{

    @Test
    public void searchMultipleItems(){
        List<String> list = new ArrayList<String>();
        list.add("ps5");
        list.add("iphone case");
        list.add("laptop");
        list.add("mouse");
        list.add("keyboard");

        for (String item: list){
            typeAndEnter("#twotabsearchtextbox", item);
            clear("#twotabsearchtextbox");
        }

    }
}
