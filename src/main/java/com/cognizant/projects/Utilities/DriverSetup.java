package com.cognizant.projects.Utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverSetup {

	private static WebDriver driver;
	public static String userDir = System.getProperty("user.dir");

	public static void launchChrome() {

		System.setProperty("webdriver.chrome.driver", userDir + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public static void launchFirefox() {
		System.setProperty("webdriver.gecko.driver", userDir + "\\Drivers\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
	}

	public static WebDriver getWebDriver() {
		String userChoice = AccessProperties.getUserInput();
		if (userChoice.equalsIgnoreCase(AccessProperties.getChromeProperty())) {
			launchChrome();
		} else if (userChoice.equalsIgnoreCase(AccessProperties.getFirefoxProperty())) {
			{
				launchFirefox();
			}
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}

}
