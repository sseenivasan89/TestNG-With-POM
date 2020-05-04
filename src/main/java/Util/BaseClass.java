package Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static JSONObject JSONObject;
	public static String browserName;
	public static String url;
	public static String username;
	public static String password;
	public static Duration ExplicitWaitTimeOutUnit = Duration.ofSeconds(120);

	public static void initialization() throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "/Resources/Data.json"));
		JSONObject = (JSONObject) obj;

		browserName = (String) JSONObject.get("browser");
		url = (String) JSONObject.get("url");
		username = (String) JSONObject.get("username");
		password = (String) JSONObject.get("password");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver(chromeOptions);

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().setSize(new Dimension(1300, 800));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(url);

	}

	public void tearDown() {
		driver.close();
		driver.quit();
		driver = null;
	}

}
