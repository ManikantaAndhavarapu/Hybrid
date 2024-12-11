package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Base;

public class AdminLogout extends Base {
	
	public  AdminLogout(WebDriver driver) {
		
		// TODO Auto-generated constructor stub
	this.driver = driver;
		
	}
	@FindBy(xpath = "//a[@id='welcome']")
	WebElement ObjWelCome;

	@FindBy(xpath ="//a[normalize-space()='Logout']")
	WebElement objLogout;
	public void adminLogout () throws Throwable{
		Thread.sleep(2000);
		ObjWelCome.click();
		Thread.sleep(3000);
		objLogout.click();
		
	}

}
