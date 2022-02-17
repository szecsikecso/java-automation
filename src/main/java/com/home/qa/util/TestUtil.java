package com.home.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.home.qa.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 200;
	public static long IMPLICIT_WAIT = 10;
	static XSSFWorkbook book;
	static XSSFSheet sheet;

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void clearText(WebElement we) {
		wait(500);
		wait.until(ExpectedConditions.visibilityOf(we));
		we.sendKeys(Keys.CONTROL + "a");
		we.sendKeys(Keys.DELETE);
	}

	public static void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isElementVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException exception) {
			return false;
		}

	}

	public static void waitElementToBeInvisible(WebElement element) {
		try {
			// If we see it then wait until element disappears.
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (org.openqa.selenium.NoSuchElementException|org.openqa.selenium.StaleElementReferenceException exception) {
			// We might miss seeing the element.
		}
	}

	public static void clickElement(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public static int getSizeOfElementList(List<WebElement> list) {
		if (TestUtil.isElementVisible(list.get(0))){
			return list.size();
		}

		return 0;
	}
}
