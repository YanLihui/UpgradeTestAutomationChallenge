package tech.credify.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import tech.credify.base.TestBase;

import java.io.*;
import java.util.ArrayList;

public class TestUtil extends TestBase {

    public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/src/main/java/tech/credify/testData/TestData.xlsx";
    static Workbook book;
    static Sheet sheet;
    static JavascriptExecutor js;

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                 System.out.println(data[i][k]);
            }
        }
        return data;
    }


    public static void runTimeInfo(String messageType, String message) throws InterruptedException {
        js = (JavascriptExecutor) webDriver;
        // Check for jQuery on the page, add it if need be
        js.executeScript("if (!window.jQuery) {"
                + "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
                + "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
                + "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
        Thread.sleep(5000);

        // Use jQuery to add jquery-growl to the page
        js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

        // Use jQuery to add jquery-growl styles to the page
        js.executeScript("$('head').append('<link rel=\"stylesheet\" "
                + "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
        Thread.sleep(5000);

        // jquery-growl w/ no frills
        js.executeScript("$.growl({ title: 'GET', message: '/' });");
//'"+color+"'"
        if (messageType.equals("error")) {
            js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
        }else if(messageType.equals("info")){
            js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
        }else if(messageType.equals("warning")){
            js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
        }else
            System.out.println("no error message");
         Thread.sleep(5000);
    }

    public void saveOfferInformationToExcel(final String sheetName, final ArrayList<String> offerInfo) throws IOException

    {
        File file = new File(TESTDATA_SHEET_PATH);
        FileInputStream inputStream = new FileInputStream(file);

        Workbook testDataWorkBook= null;
        testDataWorkBook = new XSSFWorkbook(inputStream);

        Sheet sheet = testDataWorkBook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.getRow(1);
        for(int j = 0; j < row.getLastCellNum(); j++){

            Cell cell = newRow.getCell(j);

            cell.setCellValue(offerInfo.get(j));

        }

        inputStream.close();

        //Create an object of FileOutputStream class to create write data in excel file

        FileOutputStream outputStream = new FileOutputStream(file);

        //write data in the excel file

        testDataWorkBook.write(outputStream);

        //close output stream

        outputStream.close();

    }

}
