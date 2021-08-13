package com.cognizant.projects.Testing;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cognizant.projects.Hackathon.Implement;
import com.cognizant.projects.Hackathon.ImplementUI;

import junit.framework.Assert;

public class MainTesting {
	public static WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports extent;
	ImplementUI implement = new ImplementUI();

	@Parameters("Browser")
	@BeforeSuite
	public void setDriver() throws InterruptedException {
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/testReport.html");
		extent = new ExtentReports();
		driver = Implement.createDriver();
		implement.maximizeBrowser();
	}

	@BeforeTest
	public void startTest() {
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output/ExtentTest.html");
	}

	@Test(priority = 1)
	public void showBookShelves() throws InterruptedException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("BookShelfs Testing", "To validate if the Bookshelves is showing or Not");
		test1.info("This will show the BookShelves section");
		implement.openBookShelvesPage();
		test1.pass("Passed");
	}

	@Test(priority = 2)
	public void getFirstThreeBookshelves() throws InterruptedException, FileNotFoundException, IOException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Bookshelves Testing",
				"To validate if the first 3  Bookshelves is showing or Not");
		test1.info("This will check the functionality to get the first 3 bookshelves");
		implement.selectFirstthreeBookSelves();
		test1.pass("Passed");
	}

	@Test(priority = 3)
	public void getGiftCards() throws InterruptedException, IOException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Gift Cards Section testing",
				"To validate if the gift cards is showing or Not");
		test1.info("This will check the functionality to open the gift card");
		implement.GiftCards();
		test1.pass("Passed");
	}

	@Test(priority = 4, dependsOnMethods = "getGiftCards")
	public void getListOfSubmenuItems() throws InterruptedException, FileNotFoundException, IOException {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Testing phase",
				"To validate if the sub menu items in mattresses is showing or Not");
		test1.info("This will check the functionality to get the sub menu in mattresses");
		implement.subMenuItemsInMattresses();
		test1.pass("Passed");
	}

	@Test(priority = 5)
	public void emailEnterdisWrong() {
		extent.attachReporter(htmlreporter);
		ExtentTest test1 = extent.createTest("Test", "this is a failed test case to show email is entered wring");
		test1.info("This is a failed test for email");
		Assert.assertTrue(false);

	}

	@AfterSuite
	public void closeBrowser() throws InterruptedException {
		extent.flush();
		implement.timeWait();
		implement.closeBrowser();
	}

}