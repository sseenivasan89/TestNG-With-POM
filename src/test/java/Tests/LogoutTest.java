package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Pages.LoginPage;
import Pages.LogoutPage;
import Util.BaseClass;
import Util.Screeshots;

public class LogoutTest extends BaseClass {
	LoginPage loginPage;
	LogoutPage logoutPage;
	ExtentReports extent;
	ExtentTest logger;
	String className;
	
	public LogoutTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException {
		initialization();
		loginPage = new LoginPage();
		logoutPage = new LogoutPage();
		className = this.getClass().getName();
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir")+"/Reports/"+className+".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest(this.getClass().getName());

	}

	@Test(priority = 1, groups = {"logout"})
	public void signIn() {
		logger.log(Status.INFO, "Sign-In");
		loginPage.loginTest();
		logger.pass("Sign-In Success");
	}

	@Test(priority = 2, groups = {"logout"})
	public void loginPageTitleTest() throws InterruptedException {
		logger.log(Status.INFO, "Login Page Title");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "OrangeHRM");
		Thread.sleep(1000);
		logger.pass("Login Page Title Success");
	}
	
	@Test(priority = 3, groups = {"logout"})
	public void signOut() throws InterruptedException {
		logger.log(Status.INFO, "Sign-Out");
		logoutPage.logoutTest();
		logger.pass("Sign-Out Success");
	}
	
	@Test(priority = 4, groups = {"success"})
	public void signInSuccess() {
		System.out.println("Log-Out Success");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Screeshots.getScreenshot(driver, className);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

		extent.flush();
	}

	@AfterClass
	public void closeDriver() throws IOException {
		tearDown();
	}
}

