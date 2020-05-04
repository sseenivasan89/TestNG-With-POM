package Parallel;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class ParallelTest1 {
	WebDriver driver;
		
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.google.com/");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void test1() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Parallel-1");
		Thread.sleep(1000);
	}

	@Test(priority = 2)
	public void test2() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='q']")).submit();
		Thread.sleep(1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void afterSuite() {
		System.out.println("After Suite");
	}
}
