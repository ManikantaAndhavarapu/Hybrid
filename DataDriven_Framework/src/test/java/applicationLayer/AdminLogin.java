package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogin {
	
	
	
	@FindBy(id ="txtUsername")
	WebElement Objusername;
	@FindBy(name ="txtPassword")
	WebElement Objpassword;
	@FindBy(xpath ="//input[@id='btnLogin']")
	WebElement Objlogin;
	
	public void verifyLogin(String user,String pass) {
		
		Objusername.sendKeys(user);
		Objpassword.sendKeys(pass);
		Objlogin.click();
	}


}
