package com.home.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.home.qa.util.TestUtil;
import com.home.qa.util.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static WebDriverWait wait;
	static String OS = System.getProperty("os.name").toLowerCase();

	public TestBase() {
		try {
			prop = new Properties();
			String dirPath = System.getProperty("user.dir");
			String configFilePath = "//src//main//java//com//home//qa//config//config.properties";
			FileInputStream config = new FileInputStream(dirPath + configFilePath);
			prop.load(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("BROWSER");
		switch (browserName) {
			case "CHROME": {
				String chromeDriverPath = prop.getProperty("CHROME_DRIVER_PATH");
				String path = System.getProperty("user.dir");
				System.setProperty("webdriver.chrome.driver", path + chromeDriverPath);
				driver = new ChromeDriver();
				break;
			}
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));
		wait = new WebDriverWait(driver, 100);
	}
}
