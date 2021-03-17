package com.Selenium.maintest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
//import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import com.Selenium.Pages.*;


public class mainTest1 extends BaseUI{
	
	
	
	@BeforeSuite
	public void setupBrowser() {

		
		logger = report.createTest("Browser Setup");
		String Browser = selectBrowser("Browsers");
		driver = invokeBrowser(Browser);
		openURL("websiteURL");
		
	}
	
	@Test (priority=1)
	public void bookself()
	{
		
		BookShelves b1 = new BookShelves(driver);
		System.out.println("*******************  Test_Case 1  *******************");
		try {
			b1.searchBookshelf();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	@Test (priority=2)
	public void Collection()
	{
		CollectionSublist c = new CollectionSublist(driver);
		System.out.println("*******************  Test_Case 2  *******************");
		try {
			c.retriveSubmenuList();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	@Test (priority=3)
	public void giftCard()
	{
		
		GiftcardErrorMessage g = new GiftcardErrorMessage(driver);
		System.out.println("*******************  Test_Case 3  *******************");
		try {
			g.displayErrorMessage();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	@Test (priority=4)
	public void highlyRecommendedStudyChair()
	{
		
		RecommendedStudyChair chair = new RecommendedStudyChair(driver);
		System.out.println("*******************  Test_Case 4  *******************");
		try {
			chair.displayStudyChair();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	@AfterTest
	public void endReport() {
		report.flush();
	}
	
	@AfterSuite
	public void closeBrowser() {
		stopBrowser();
	}
}
