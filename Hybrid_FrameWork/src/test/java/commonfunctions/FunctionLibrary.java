package commonfunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary {

	public static Properties conpro;

	public static WebDriver driver;

	public static WebDriver startBrowser() throws Throwable {


		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFile/EnvironmentalVariable.properties") );

		if(conpro.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		else if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		}

		else
		{
			Reporter.log("Browser Value is Not Matching",true);
		}

		return driver;
	}

	public static void openUrl()
	{
		driver.get(conpro.getProperty("url"));

	}
	public static void waitForElement(String LocatorType,String LocatorValue, String Wait) {

		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Wait)));

		if(LocatorType.equalsIgnoreCase("xpath")) {


			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}


		if(LocatorType.equalsIgnoreCase("name"))
		{
			//wait unti element is visible
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		if(LocatorType.equalsIgnoreCase("id"))
		{
			//wait unti element is visible
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}

	}

	public static void typeAction(String LocatorType,String LocatorValue, String TestData) {
		if(LocatorType.equalsIgnoreCase("name")) {

			driver.findElement(By.name(LocatorValue)).clear();

			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
		if(LocatorType.equalsIgnoreCase("xpath")) {

			driver.findElement(By.xpath(LocatorValue)).clear();

			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}

		if(LocatorType.equalsIgnoreCase("id")) {

			driver.findElement(By.id(LocatorValue)).clear();

			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}


	}

	public static void clickAction(String LocatorType,String LocatorValue) {

		if(LocatorType.equalsIgnoreCase("name")) {

			driver.findElement(By.name(LocatorValue)).click();
		}


		if(LocatorType.equalsIgnoreCase("id")) {

			driver.findElement(By.id(LocatorValue)).click();
		}

		if(LocatorType.equalsIgnoreCase("xpath")) {

			driver.findElement(By.xpath(LocatorValue)).click();
		}
	}
	public static void validateTitle(String Expected_Title) {

		String actual_title = driver.getTitle();
		try {

			Assert.assertEquals(actual_title, Expected_Title,"Title is not matching");

		} catch (AssertionError e) {

			System.out.println(e.getMessage());


		}
		// TODO: handle exception
	}

	public static void closeBrowser() {

		driver.quit();
	}
	
	public static void dropDownAction(String LocatorType,String LocatorValue,String TestData ) {
		

		if(LocatorType.equalsIgnoreCase("name")) {
			
			int value = Integer.parseInt(TestData);
			
			Select sel = new Select(driver.findElement(By.name(LocatorValue)));
			sel.selectByIndex(value);
		}
		
if(LocatorType.equalsIgnoreCase("id")) {
			
			int value = Integer.parseInt(TestData);
			
			Select sel = new Select(driver.findElement(By.id(LocatorValue)));
			sel.selectByIndex(value);
		}

if(LocatorType.equalsIgnoreCase("xpath")) {
	
	int value = Integer.parseInt(TestData);
	
	Select sel = new Select(driver.findElement(By.xpath(LocatorValue)));
	sel.selectByIndex(value);
}
		
	}
	
	public static void captureStock(String LocatorType,String LocatorValue) throws Throwable {
		
		String stocknumber ="";
		
		if(LocatorType.equalsIgnoreCase("name")) {
			
			stocknumber = driver.findElement(By.name(LocatorValue)).getAttribute("value");
		}

		if(LocatorType.equalsIgnoreCase("id")) {
			
			stocknumber= driver.findElement(By.id(LocatorValue)).getAttribute("value");
		}

		if(LocatorType.equalsIgnoreCase("xpath")) {
			
			stocknumber= driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
		}
		
		FileWriter fw = new FileWriter("./CaptureData/stocknum.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(stocknumber);
		bw.flush();
		bw.close();
		
	
	}
	
	public static void stockTable() throws Throwable {
		
		FileReader fr = new FileReader("./CaptureData/stocknum.txt");
		BufferedReader br = new BufferedReader(fr);
		String Exp_Data= br.readLine();
		if(!driver.findElement(By.xpath(conpro.getProperty("search-text"))).isDisplayed())
			driver.findElement(By.xpath(conpro.getProperty("search-pannel"))).click();
		driver.findElement(By.xpath(conpro.getProperty("search-text"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("search-text"))).sendKeys(Exp_Data);
		
		driver.findElement(By.xpath(conpro.getProperty("search-btn"))).click();
		
		Thread.sleep(3000);
		String Act_data =driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[8]/div/span/span")).getText();
		Reporter.log(Exp_Data+"         "+Act_data,true);
		try {
			Assert.assertEquals(Act_data, Exp_Data, "Stock Number NNot Matching");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void captureSupplierNum(String LocatorType,String LocatorValue) throws Throwable {
		String supplierNum ="";
		if(LocatorType.equalsIgnoreCase("xpath")) {
			supplierNum =driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
			
		}
		if(LocatorType.equalsIgnoreCase("name")) {
			supplierNum =driver.findElement(By.name(LocatorValue)).getAttribute("value");
			
		}
		
		if(LocatorType.equalsIgnoreCase("id")) {
			supplierNum =driver.findElement(By.id(LocatorValue)).getAttribute("value");
			
		}
		
		FileWriter fw = new FileWriter("./CaptureData/suppliernum.txt");
		
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(supplierNum);
		bw.flush();
		bw.close();
	}
	
	public static void supplierTable() throws Throwable {
		
		FileReader fr = new FileReader("./CaptureData/supplierum.txt");
		BufferedReader br = new BufferedReader(fr);
		String Exp_Data= br.readLine();
		if(!driver.findElement(By.xpath(conpro.getProperty("search-text"))).isDisplayed())
			driver.findElement(By.xpath(conpro.getProperty("search-pannel"))).click();
		driver.findElement(By.xpath(conpro.getProperty("search-text"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("search-text"))).sendKeys(Exp_Data);
		
		driver.findElement(By.xpath(conpro.getProperty("search-btn"))).click();
		
		Thread.sleep(3000);
		String Act_data =driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
		Reporter.log(Exp_Data+"         "+Act_data,true);
		try {
			Assert.assertEquals(Act_data, Exp_Data, "Supplier Number is Not Matching");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
} 
