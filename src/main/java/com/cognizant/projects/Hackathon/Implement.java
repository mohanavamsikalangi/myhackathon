package com.cognizant.projects.Hackathon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.projects.Utilities.AccessProperties;
import com.cognizant.projects.Utilities.DriverSetup;

public class Implement {

	public static WebDriver driver;
	public static String baseUrl;
	public static String userDir = System.getProperty("user.dir");

	/**********************************
	 * Method for creating driver and opening browser
	 ***********************************************/
	public static WebDriver createDriver() throws InterruptedException {
		driver = DriverSetup.getWebDriver();
		baseUrl = AccessProperties.getBaseUrl();
		driver.get(baseUrl);
		return driver;
	}

	/********************
	 * Method to display welcome message in console
	 *****************************************************/
	public void displayWelcomeMessage() {
		System.out.println("Hi!! Welcome to Automation");
	}

	/**********************************************
	 * method to maximize the Browser
	 **************************************/
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	/*******************************************
	 * method to reusable Action
	 ***********************************************/
	public void mouseHoverActions(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		Action mouseAction = action.moveToElement(element).build();
		mouseAction.perform();
	}

	/*******************************************
	 * method to close the Browser
	 *************************************************/
	public void closeBrowser() {
		driver.close();
		System.out.println("Automation Finished!! Thank You !!!");
	}

	/*************************
	 * method to implement a small time sleep
	 **********************************************************/
	public void timeWait() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**********************
	 * Method to close the pop-up appeared on start page
	 *************************************************/
	public void closePopup() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AccessProperties.getPopupXpath())));

		WebElement closepopup = driver.findElement(By.xpath(AccessProperties.getPopupXpath()));
		closepopup.click();
	}

	/*******
	 * This method is to do the slider action to set price range from 0 - 15000 RS
	 ****************************/
	public void sliderAction(WebElement priceElement) {

		Actions action = new Actions(driver);
		action.clickAndHold(priceElement);
		action.build().perform();
		timeWait();
		WebElement sliderA = driver.findElement(By.xpath(
				"//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[1]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[1]/div"));
		Actions move = new Actions(driver);
		move.dragAndDropBy(sliderA, 0, 0);
		timeWait();
		WebElement sliderB = driver.findElement(By.xpath(
				"//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[1]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[2]/div"));
		move.dragAndDropBy(sliderB, -208, 0).click();
		move.build().perform();
		timeWait();

	}

	/************************************
	 * 8method to format the price
	 *******************************/
	public static String convert(String price) {
		StringBuffer buf = new StringBuffer();
		// System.out.println(buf.toString());
		buf.append("Rs.");
		for (int i = 0; i < price.length(); i++) {
			char ch = price.charAt(i);
			if (i == 0 || ch == ' ')
				continue;
			else
				buf.append(ch);
		}
		buf.append("/-");
		// System.out.println(buf.toString());
		return buf.toString();
	}

	/**********************************
	 * Utility function for taking and storing screenshots in the folder Screenshots
	 * in the current project directory. If the folder is already in the project
	 * directory it will delete and create a new one.
	 ******************/
	public static boolean isFirstrun = true;

	public static void takeScreenShot(String filename) throws IOException {
		// Take Screenshot and store it as a file format
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Getting the project directory path for the user
		String currentDirectory = System.getProperty("user.dir");

		// Check whether this is the first time this method is called if yes then delete
		// all the screenshots from previous run
		if (isFirstrun) {
			Path path = Path.of(currentDirectory + "//Screenshots");

			// Check if Folder already exists if yes then delete and create the new folder
			// otherwise just create the folder.
			if (Files.exists(path)) {
				try {
					// Delete the existing folder
					FileUtils.deleteDirectory(new File(currentDirectory + "//Screenshots"));
					System.out.println("Deleted Existing Screenshots Folder Now creating a new one.");
				} catch (Exception e) {
					System.out.println("Sorry some error occured While deleting your Screenshots folder");
				}
			}

			else {
				System.out.println("Creating Screenshots Folder");
			}

			// Create the new folder
			Files.createDirectory(Path.of(currentDirectory + "//Screenshots"));
		}
		System.out.println("Taking ScreenShot!!!!");
		FileUtils.copyFile(file, new File(currentDirectory + "//Screenshots//" + filename + ".jpg"));

		System.out.println("Screenshot saved: " + currentDirectory + "\\Screenshots\\" + filename + ".jpg");
		isFirstrun = false;
	}

}
