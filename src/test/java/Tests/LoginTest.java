package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Pages.LoginPage;
import Util.BaseClass;
import Util.Screeshots;

public class LoginTest extends BaseClass {
	LoginPage loginPage;
	ExtentReports extent;
	ExtentTest logger;
	String className;
	Logger log = Logger.getLogger(LoginTest.class);

	public LoginTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException {
		initialization();
		loginPage = new LoginPage();
		className = this.getClass().getName();
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/Reports/" + className + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest(this.getClass().getName());
		log.info("entering application URL");
		log.warn("Hey this just a warning message");
		log.fatal("hey this is just fatal error message");
		log.debug("this is debug message");

	}

	@Test(priority = 1, groups = { "login" })
	public void signIn() {
		logger.log(Status.INFO, "Sign-In");
		loginPage.loginTest();
		logger.pass("Sign-In Success");
	}

	@Test(priority = 2, groups = { "login" })
	public void loginPageTitleTest() throws InterruptedException {
		logger.log(Status.INFO, "Login Page Title");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "OrangeHRM");
		Thread.sleep(1000);
		logger.pass("Login Page Title Success");
	}

	@Test(priority = 3, groups = { "success" })
	public void signInSuccess() {
		System.out.println("Log-In Success");
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
