package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.AddEmp;
import config.Base;
import utilities.ExcelFileUtil;

public class AppTest extends Base {
	String inputPath ="./FileInput/EmpData.xlsx";
	String OutPath = "./FileOutput/HybridResults.xlsx";
	String TcSheet = "Emp";
	ExtentReports reports;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable {
		
		reports  = new  ExtentReports("./target/reports/AddEmp.html");
		
		ExcelFileUtil xl = new ExcelFileUtil(inputPath);
		int rc= xl.rowCount(TcSheet);
		Reporter.log("No of rows are::"+rc,true);
		
		for(int i=1; i<=rc;i++) {
			
			logger = reports.startTest("Validate EmpDataTest");
			logger.assignAuthor("Mani");
			
			String Fname = xl.getCellData(TcSheet, i, 0);
			String Mname = xl.getCellData(TcSheet, i, 1);
			String LName = xl.getCellData(TcSheet, i, 2);
			logger.log(LogStatus.INFO,Fname+"   "+Mname+"   "+LName);
			
			AddEmp emp  = PageFactory.initElements(driver,AddEmp.class);
			boolean res = emp.verifyEmp(Fname, Mname, LName);
			if(res) {
				
				xl.setCellData(TcSheet, i, 3, "Pass", OutPath);
				logger.log(LogStatus.PASS, "Add Employee Success");
			}
			
			else {
				xl.setCellData(TcSheet, i, 3, "Fail", OutPath);
				logger.log(LogStatus.FAIL, "Add Employee Fail");
			}
			reports.endTest(logger);
			reports.flush();
		}
			
		}
		
	}
	