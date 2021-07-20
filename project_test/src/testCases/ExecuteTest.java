package testCases;

import java.util.Properties;
import operation.ReadObject;
import operation.Keywords;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import excelExportAndFileIO.ReadExcelFile;

/**
 * THIS IS THE EXAMPLE OF KEYWORD DRIVEN TEST CASE
 *
 */
public class ExecuteTest {
    @Test
	public void testLogin() throws Exception {
    	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
    	ReadExcelFile file = new ReadExcelFile();
        ReadObject object = new ReadObject();
        Properties allObjects =  object.getObjectRepository();
        Keywords operation = new Keywords(driver);
        //Read keyword sheet System.getProperty("user.dir")+"\\src\\objects\\object.properties")
        Sheet sureshSheet = file.readExcel("C:\\Users\\My PC\\eclipse-workspace\\appium_project_test\\src\\TestScripts","TestCase1.xlsx" , "Search");
      //Find number of rows in excel file
    	int rowCount = sureshSheet.getLastRowNum()-sureshSheet.getFirstRowNum();
    	//Create a loop over all the rows of excel file to read it
    	for (int i = 1; i < rowCount+1; i++) {
    		//Loop over all the rows
    		Row row = sureshSheet.getRow(i);
    		//Check if the first cell contain a value, if yes, That means it is the new testcase name
    		if(row.getCell(0).toString().length()==0){
    		//Print testcase detail on console
    			System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
    			row.getCell(3).toString()+"----"+ row.getCell(4).toString());
    		//Call perform function to perform operation on UI
    			operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
    				row.getCell(3).toString(), row.getCell(4).toString());
    	    }
    		else{
    			//Print the new  testcase name when it started
    				System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
    			}
    		}
	}

}
