package utility;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataReader {

    String path;
    XSSFWorkbook excelWBook;
    XSSFSheet excelWSheet;
    XSSFCell cell;
    public DataFormatter formatter= new DataFormatter();

    public DataReader(String path){
        this.path = path;
    }

    public String getDataFromCell(String sheet, int rowNum, int colNum){
        try {
            FileInputStream excelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheet);
            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            String cellValue = cell.getStringCellValue();
            excelFile.close();
            return cellValue;
        }catch (Exception e){
            System.out.println("no data found");
            return "";
        }
    }

    public List<String> getEntireColumnData(String sheet, int rowStart, int colNum){
        List<String> columnData = new ArrayList<String>();
        try {
            FileInputStream excelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheet);
            for (int i = rowStart; i <= excelWSheet.getLastRowNum(); i++){
                columnData.add(excelWSheet.getRow(i).getCell(colNum).getStringCellValue());
            }
            excelFile.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("no data found");
        }
        return columnData;
    }
    //use for data provider
    public Object[][] readVariant(String sheetName) {
        FileInputStream fileInputStream= null; //Excel sheet file location get mentioned here
        try {
            fileInputStream = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(fileInputStream); //get my workbook
        } catch (IOException e) {
            e.printStackTrace();
        }
        excelWSheet = excelWBook.getSheet(sheetName);// get my sheet from workbook
        XSSFRow Row=excelWSheet.getRow(0);   //get my Row which start from 0

        int RowNum = excelWSheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum

        Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array

        for(int i=0; i<RowNum-1; i++) //Loop work for Rows
        {
            XSSFRow row= excelWSheet.getRow(i+1);

            for (int j=0; j<ColNum; j++) //Loop work for colNum
            {
                if(row==null)
                    Data[i][j]= "";
                else
                {
                    XSSFCell cell= row.getCell(j);
                    if(cell==null)
                        Data[i][j]= ""; //if it get Null value it pass no data
                    else
                    {
                        String value=formatter.formatCellValue(cell);
                        Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }
        }

        return Data;
    }

    public List<String> getEntireColumnForGivenHeader(String sheet, String headerName){
        int i = 0;
        while (getDataFromCell(sheet, 0, i) != null){
            if(getDataFromCell(sheet, 0, i).equalsIgnoreCase(headerName)){
                getEntireColumnData(sheet, 1, i);
                break;
            }
            i++;
        }
        return getEntireColumnData(sheet, 1, i);
    }

    public String getValueForGivenHeaderAndKey(String sheet, String headerName, String key){
        String value = null;
        int i = 0;
        while (getDataFromCell(sheet, 0, i) != null){
            if(getDataFromCell(sheet, 0, i).equalsIgnoreCase(headerName)){
                for (int j = 0; j < getEntireColumnData(sheet, 1, i).size(); j++){
                    if(getEntireColumnData(sheet, 1, i).get(j).equalsIgnoreCase(key)){
                        value = getEntireColumnData(sheet, 1, i+1).get(j);
                    }
                }
                break;
            }
            i++;
        }
        return value;
    }

    public static void main(String[] args) {
        String path = "/Users/nacer-zimu/EAT/eat-workspace/eat-mar2022-weekdays-web-automation-framework/data/my_data.xlsx";
        DataReader dr = new DataReader(path);
        //List<String> str = dr.getEntireColumnForGivenHeader(path, "Sheet1", "id");
        //String str = dr.getValueForGivenHeaderAndKey("Sheet1", "id", "id003");
        //System.out.println(str);
        //System.out.println(Arrays.deepToString(dr.readVariant("Sheet1")));
        for (Object[] x : dr.readVariant("Sheet1"))
        {
            for (Object y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
}
