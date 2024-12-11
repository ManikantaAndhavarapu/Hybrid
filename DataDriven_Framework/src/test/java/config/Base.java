package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import applicationLayer.AdminLogin;
import applicationLayer.AdminLogout;

public class Base {
	
	public static WebDriver driver;
	public static Properties conpro;
	
	
	@BeforeTest
	public static void setUp() throws Throwable
	
	{
		
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFile/EnvironmentalVariable.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("url"));
			
			AdminLogin login = PageFactory.initElements(driver, AdminLogin.class );
			login.verifyLogin("Admin", "Qedge123!@#");
	
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			driver.get(conpro.getProperty("url"));
			AdminLogin login = PageFactory.initElements(driver, AdminLogin.class );
			login.verifyLogin("Admin", "Qedge123!@#");
		}
		
		else {
			
			Reporter.log("Invalid Browser",true);
		}
			
	}
	
	@AfterTest
	public static void tearDown() throws Throwable
	{
		AdminLogout logout = PageFactory.initElements(driver, AdminLogout.class);
		logout.adminLogout();
		driver.quit();
	}
	}



