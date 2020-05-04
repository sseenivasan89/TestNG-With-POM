package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Util.BaseClass;

public class LogoutPage extends BaseClass{
	
	WebDriverWait wait;
	
	@FindBy(id = "welcome")
	WebElement adminIcon;
	@FindBy(css = "[id='welcome-menu']>ul>li:nth-child(2)>a")
	WebElement logoutLink;
	@FindBy(id = "logInPanelHeading")
	WebElement homePageLoginPanel;

	
	// Initializing the Page Objects:
	public LogoutPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, BaseClass.ExplicitWaitTimeOutUnit);
	} 
	public void logoutTest() {
		adminIcon.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(logoutLink));
		logoutLink.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(homePageLoginPanel));
		
	}
		
}
