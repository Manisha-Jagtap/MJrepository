package com.Selenium.Pages;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;

import com.Selenium.GenrateReport.DateUtils;
import com.Selenium.GenrateReport.GenrateReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BaseUI {

	
	/****************** OBJECTS ************/
	public static WebDriver driver;
	public static Properties property;
	public ExtentReports report = GenrateReport.getExtentReport();
	public ExtentTest logger;

	
	/****************** SELECT BROWSER ************/
	public String selectBrowser() {
		try {
			System.out.println("1. Chrome\n2. FireFox\n3. Opera\n4. InternetExplorer\nEnter choice:");
			try (Scanner s = new Scanner(System.in)) {
				int n = s.nextInt();
				switch (n) {
				case 1:
					reportPass("Browser Chrome Selected");
					return "chrome";
				case 2:
					reportPass("Browser FireFox Seleced");
					return "firefox";
				case 3:
					reportPass("Browser Opera Selected");
					return "opera";
				case 4:
					reportPass("Browser IE Selected");
					return "IE";
				}
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass("Brwoser Selected");
		return null;
	}
	

	/****************** INVOKE BROWSER ************/
	public WebDriver invokeBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\srcTestResources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\srcTestResources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("opera")) {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "\\srcTestResources\\drivers\\operadriver.exe");
				driver = new OperaDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\srcTestResources\\drivers\\operadriver.exe");
				driver = new InternetExplorerDriver();
			}

			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

			if (property == null) {

				property = new Properties();
				try {
					FileInputStream file = new FileInputStream(System.getProperty("user.dir")
							+ "\\srcTestResources\\ObjectRepository\\projectConfig.properties");
					property.load(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass("Brwoser Invoked");
		return driver;
	}

	
	/****************** OPEN THE WEBSITE URL ************/
	public void openURL(String websiteURLkey) {
		try {
			driver.get(property.getProperty(websiteURLkey));
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass("Redirected on " + property.getProperty(websiteURLkey));
	}

	/****************** TO CLOSE BROWSER INSTANCE ************/
	public void stopBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass("Browser Closed");
	}
	

	/****************** TO QUIT BROWSER INSTANCE ************/
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass("Browser Quit");
	}
	

	/****************** TO ENTER THE TEXT ************/
	public void enterText(String pathkey, String value) {
		try {
			getElement(pathkey).sendKeys(value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass(pathkey+"-"+value+" Entered Successfully");
	}

	/****************** TO GET THE TEXT ************/
	public String getText(String pathkey) {
		String text = "";
		try {
			text = getElement(pathkey).getText();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass(text+" Retrived Successfully");
		return text;
	}

	
	/****************** TO CLICK ************/
	public void clickElement(String pathkey) {
		try {
			getElement(pathkey).click();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		reportPass(pathkey+" Clicked Successfully");
	}

	
	/****************** TO GET THE WEB ELEMENT *******************/
	public WebElement getElement(String locator) {
		WebElement element = null;

		if (locator.endsWith("_Id")) {
			element = driver.findElement(By.id(property.getProperty(locator)));
			logger.log(Status.INFO, ""+locator);
		} else if (locator.endsWith("_Css")) {
			element = driver.findElement(By.cssSelector(property.getProperty(locator)));
			logger.log(Status.INFO, ""+locator);
		} else if (locator.endsWith("_Link")) {
			element = driver.findElement(By.linkText(property.getProperty(locator)));
			logger.log(Status.INFO, ""+locator);
		} else if (locator.endsWith("_Partiallink")) {
			element = driver.findElement(By.partialLinkText(property.getProperty(locator)));
			logger.log(Status.INFO, ""+locator);
		} else if (locator.endsWith("_Name")) {
			element = driver.findElement(By.name(property.getProperty(locator)));
			logger.log(Status.INFO, ""+locator);
		} else if (locator.endsWith("_Xpath")) {
			element = driver.findElement(By.xpath(property.getProperty(locator)));
			logger.log(Status.INFO, ""+locator);
		} else {
			reportFail("" + locator);
			Assert.fail("" + locator);
		}
		return element;
	}

	
	/******************* TO GET THE LIST OF WEBELEMENTS ***************/
	public List<WebElement> getElements(String locator) {

		if (locator.endsWith("_Id")) {
			return driver.findElements(By.id(property.getProperty(locator)));
			//logger.log(Status.INFO, ""+locator);
		} else if (locator.endsWith("_Xpath")) {
			return driver.findElements(By.xpath(property.getProperty(locator)));
			//logger.log(Status.INFO, ""+locator);
		} else if (locator.endsWith("_CSS")) {
			return driver.findElements(By.cssSelector(property.getProperty(locator)));
		} else {
			reportFail("" + locator);
			Assert.fail("" + locator);
		}
		return null;
	}

	
	/*********************   REPORT   **********************/
	public void reportFail(String reportStatus) {

		logger.log(Status.FAIL, reportStatus);
		getScreenshot();
		Assert.fail(reportStatus);
	}

	public void reportPass(String reportStatus) {

		logger.log(Status.PASS, reportStatus);
		
	}

	
	/************************   SCREENSHOTS   ************************/
	public void getScreenshot() {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + DateUtils.getTimeStamp() +".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\ScreenShots\\" + DateUtils.getTimeStamp() + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
