package driverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import commonfunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class AppTest {
	
String inputPath = "./FileInput/DataEngine.xlsx";
String OutputPath = "./FileOutput/HybridResults.xlsx";
String TCSheet = "MasterTestCases";
ExtentReports reports;
ExtentTest logger;
WebDriver driver;

@Test
public void startTest() throws Throwable {
	
	String Module_Status="";
	String Module_New="";
	
	ExcelFileUtil xl = new ExcelFileUtil(inputPath);
	
	int rc = xl.rowCount(TCSheet);
	
	for(int i=1;i<=rc;i++) {
		
		if(xl.getCellData(TCSheet, i, 2).equalsIgnoreCase("Y")){
			
			
		String TCModule = xl.getCellData(TCSheet, i, 1)	;
		
				for(int j=1; j<=xl.rowCount(TCModule);j++) {
					
					
					String Description =xl.getCellData(TCModule, j, 0);
					String ObjectType = xl.getCellData(TCModule, j, 1);
					String Ltype =xl.getCellData(TCModule, j, 2);
					String LValue = xl.getCellData(TCModule, j, 3);
					String TestData = xl.getCellData(TCModule, j, 4);
					try {

						if(ObjectType.equalsIgnoreCase("startBrowser"))
						{
						driver =FunctionLibrary.startBrowser();	
						}
						if(ObjectType.equalsIgnoreCase("openUrl"))
						{
							FunctionLibrary.openUrl();
						}
						if(ObjectType.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(Ltype, LValue, TestData);
						}
						if(ObjectType.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(Ltype, LValue, TestData);
						}
						if(ObjectType.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(Ltype, LValue);
						}
						if(ObjectType.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(TestData);
						}
						if(ObjectType.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser();
						}
						
						if(ObjectType.equalsIgnoreCase("dropDownAction"))
						{
							FunctionLibrary.dropDownAction(Ltype, LValue, TestData);
						}
						if(ObjectType.equalsIgnoreCase("captureStock")) {
							
							FunctionLibrary.captureStock(Ltype, LValue);
						}
						
						if(ObjectType.equalsIgnoreCase("stockTable")) {
							FunctionLibrary.stockTable();
						}
						
						if(ObjectType.equalsIgnoreCase("captureSupplierNum")) {
							
							FunctionLibrary.captureSupplierNum(Ltype, LValue);
						}
						

						if(ObjectType.equalsIgnoreCase("captureSupplierNum")) {
							
							FunctionLibrary.supplierTable();
						}
						
						
						xl.setCellData(TCModule, j, 5, "Pass", OutputPath);
						Module_Status="True";

					}
						
					catch (Exception e) {
						System.out.println(e.getMessage());
						//write as Fail into status cell in TCModule
						xl.setCellData(TCModule, j, 5, "Fail", OutputPath);
						Module_New="False";	
		}
					if(Module_Status.equalsIgnoreCase("True"))
					{
						//write as pass into TCSheet
						xl.setCellData(TCSheet, i, 3, "Pass", OutputPath);
					}
					if(Module_New.equalsIgnoreCase("False"))
					{
						//write as Fail into TCSheet
						xl.setCellData(TCSheet, i, 3, "Fail", OutputPath);
					}
				}
			}

		
			
			else
			{
				//write as blocked into TCsheet which testcase flag to N
				xl.setCellData(TCSheet, i, 3, "Blocked", OutputPath);
			}

		}
	}
}

