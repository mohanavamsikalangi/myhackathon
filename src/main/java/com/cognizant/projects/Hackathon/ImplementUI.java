package com.cognizant.projects.Hackathon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cognizant.projects.Utilities.AccessProperties;
import com.cognizant.projects.Utilities.FileOperations;

import bsh.ParseException;

public class ImplementUI extends Implement {
	FileOperations fileOP = new FileOperations();

	/***********************************
	 * Method to open BookShelves Page
	 ************************************/
	public void openBookShelvesPage() throws InterruptedException {
		driver.get("https://www.urbanladder.com/");
		WebElement living = driver.findElement(By.xpath(AccessProperties.getXpathForLivingButtonOnNavBar()));
		mouseHoverActions(driver, living);
		timeWait();
		WebElement bookshelves = driver.findElement(By.xpath(AccessProperties.getXpathforBookshelvesOption()));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", bookshelves);
	}

	/***********************************
	 * Method to set storage Type
	 ************************************/
	public void setStorageType(String SelectedstorageType) throws InterruptedException {
		WebElement storage = driver.findElement(By.cssSelector(AccessProperties.getCssSelectorToSetStorageType()));
		mouseHoverActions(driver, storage);
		if (SelectedstorageType.equalsIgnoreCase("open")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement open = driver.findElement(By.id("filters_storage_type_Open"));
			js.executeScript("arguments[0].click()", open);
		}
	}

	/***********************************
	 * Method to set the prices range
	 ************************************/
	public void setPrice() throws InterruptedException {
		WebElement setPriceElement = driver.findElement(By.xpath(AccessProperties.getXpathToSetPrice()));
		sliderAction(setPriceElement);
	}

	/***********************************
	 * Method to press exclude out of stock
	 ************************************/

	public void excludeOutOfStock() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement outOfStock = driver.findElement(By.xpath("//input[@id=\"filters_availability_In_Stock_Only\"]"));
		jse.executeScript("arguments[0].click()", outOfStock);
	}

	/***********************************
	 * Method to Retrieve and print first three BookShelve's names and prices
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 ************************************/
	public void selectFirstthreeBookSelves() throws FileNotFoundException, InterruptedException, IOException {

		List<WebElement> nameElement = driver.findElements(By.xpath("//span[@class='name']"));
		List<WebElement> priceElement = driver.findElements(By.xpath("//div[@class='price-number']/span"));

		String[] names = new String[3];
		String[] prices = new String[3];
		String pri = null;

		System.out.println("\nHi!! Got the items You want!\n");

		for (int i = 0; i < 3; i++) {
			names[i] = nameElement.get(i).getText();
			prices[i] = priceElement.get(i).getText();
			pri = convert(prices[i]);
			// System.out.print(names[i]+" "+prices[i]);
			System.out.println("Item" + (i + 1) + ": " + names[i] + " " + " and Price: " + pri);
			System.out.println();
		}

		fileOP.readItemsIntoExcelSheet(names, prices);
	}

	/***********************************
	 * Method send a gift card to a person
	 ************************************/

	public void GiftCards() throws IOException, InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement giftCards = driver.findElement(By.xpath("//*[@id=\"header\"]/section/div/ul[2]/li[3]/a"));
		jse.executeScript("arguments[0].click()", giftCards);
		jse.executeScript("window.scrollBy(0,800)");
		WebElement birthday = driver
				.findElement(By.xpath("//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]"));
		mouseHoverActions(driver, birthday);
		WebElement choose = driver.findElement(
				By.xpath("//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]/div/div/button"));
		jse.executeScript("arguments[0].click()", choose);
		WebElement customize = driver.findElement(
				By.xpath("//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/div[1]/button[1]"));
		jse.executeScript("arguments[0].click()", customize);
		WebElement next = driver
				.findElement(By.xpath("//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/button"));
		jse.executeScript("arguments[0].click()", next);
		jse.executeScript("window.scrollBy(0,500)");
		timeWait();
		WebElement toName = driver.findElement(By.xpath("//*[@id=\"ip_4036288348\"]"));
		toName.sendKeys(AccessProperties.ReciversName());
		WebElement toMail = driver.findElement(By.xpath("//*[@id=\"ip_137656023\"]"));
		toMail.sendKeys(AccessProperties.ReciversEmail());
		WebElement fromName = driver.findElement(By.xpath("//*[@id=\"ip_1082986083\"]"));
		fromName.sendKeys(AccessProperties.SendersName());
		WebElement fromMail = driver.findElement(By.xpath("//*[@id=\"ip_4081352456\"]"));
		fromMail.sendKeys(AccessProperties.SendersEmail());
		WebElement fromMob = driver.findElement(By.xpath("//*[@id=\"ip_2121573464\"]"));
		fromMob.sendKeys(AccessProperties.SendersPhoneNumber());
		timeWait();
		WebElement confirm = driver
				.findElement(By.xpath("//*[@id=\"app-container\"]/div/main/section/section[3]/form/button"));
		jse.executeScript("arguments[0].click()", confirm);
		timeWait();
		takeScreenShot("errorMessage");
		jse.executeScript("window.scrollTo(0,0)");
	}

	/**********************************
	 * Method to Retrieve the List of Mattresses Types
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws FileNotFoundException
	 ***************************************/
	public void subMenuItemsInMattresses() throws InterruptedException, FileNotFoundException, IOException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,0)");
		Actions act = new Actions(driver);
		WebElement matt = driver.findElement(By.xpath("//*[@id=\"app-container\"]/div/header/div[2]/nav/ul/li[8]/h4"));
		act.moveToElement(matt).build().perform();
		timeWait();
		List<WebElement> mlist = driver.findElements(
				By.xpath("//a[contains(text(), 'Mattresses & Bedding')]/parent::*/following-sibling::ul/li"));// _1YLMD//
																												// _1KFQA
		String[] prodList = new String[30];
		System.out.println(mlist.size() + " size");
		for (WebElement li : mlist) {
			System.out.println(li.getText());
		}

		for (int i = 0; i < mlist.size(); i++) {
			prodList[i] = mlist.get(i).getText();
		}

		fileOP.readMatressesIntoExcelSheet(prodList);

	}

	/***************************************************
	 * Main method
	 *****************************************************************/
	public static void main(String[] args) throws IOException, InterruptedException {
		ImplementUI imui = new ImplementUI();

		imui.createDriver();
		imui.displayWelcomeMessage();
		imui.maximizeBrowser();
		imui.closePopup();
		String page = "Startpage";
		imui.takeScreenShot(page);
		imui.openBookShelvesPage();
		imui.setPrice();
		imui.setStorageType("open");
		imui.excludeOutOfStock();
		imui.selectFirstthreeBookSelves();
		imui.GiftCards();
		imui.subMenuItemsInMattresses();
		imui.closeBrowser();
	}

}
