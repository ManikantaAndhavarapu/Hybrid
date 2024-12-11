package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import config.Base;

public class AddEmp extends Base{
	
public AddEmp(WebDriver driver) {
	this.driver = driver;
}
	
@FindBy(xpath = "//b[normalize-space()='PIM']")
WebElement ObjPim;
	@FindBy(linkText = "Add Employee")
	WebElement ObjAdd;

	@FindBy(id = "firstName")
	WebElement objFname;
	@FindBy(name ="middleName")
	WebElement objMname;
	@FindBy(name="lastName")
	WebElement objLname;
	@FindBy(xpath = "//input[@id='employeeId']")
	WebElement objEid;
	@FindBy(xpath = "//input[@id='btnSave']")
	WebElement objsave;
	@FindBy(xpath= "//input[@id='personal_txtEmployeeId']")
	WebElement objId;
	public boolean verifyEmp(String FirstName,String MiddleName,String LastName) throws Throwable {
		
		Actions act = new Actions(driver);
		act.moveToElement(ObjPim).click().perform();
		act.moveToElement(ObjAdd).click().perform();
		
		objFname.sendKeys(FirstName);
		objMname.sendKeys(MiddleName);
		objLname.sendKeys(LastName);
		
	String Exp_Data = objEid.getAttribute("value");

		
		act.moveToElement(objsave).click().perform();
		Thread.sleep(3000);
		String Act_data =objId.getAttribute("value");
		
		if(Act_data.equals(Exp_Data))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	}


