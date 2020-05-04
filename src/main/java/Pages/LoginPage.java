package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util.BaseClass;

public class LoginPage extends BaseClass{
	
	WebDriverWait wait;
	
	@FindBy(id = "txtUsername")
	WebElement username;
	@FindBy(name = "txtPassword")
	WebElement password;
	@FindBy(css = "[value='LOGIN']")	
	WebElement loginButton;
	@FindBy(css = "[id='branding']>a>img")
	WebElement homepageLogo;
	
	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, BaseClass.ExplicitWaitTimeOutUnit);
	}
	
	public void loginTest() {
		username.sendKeys(BaseClass.username);
		password.sendKeys(BaseClass.password);
		loginButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(homepageLogo));
		
	}
		
	public String validateLoginPageTitle() {
		return driver.getTitle(); 
	}

}
