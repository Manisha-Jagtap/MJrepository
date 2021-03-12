package com.Selenium.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.Selenium.ReadInputExcel.ReadExcelData;

public class RecommendedStudyChair extends BaseUI{

	WebDriver driver;

	public RecommendedStudyChair(WebDriver driver)
	{
		this.driver = driver;
		logger = report.createTest("Highest Recommendation Study Chair Display Verification");
	}
	
	/****************   HIGHY RECOMMENTED CHAIR  ****************/
	public void displayStudyChair() throws Exception
	{
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.get("https://www.urbanladder.com/");
		
		
		enterText("searchShelvesTextbox_Id", ReadExcelData.readExcelDataForSearchValue(1,1));
		clickElement("searchButton_Id");
		
		clickElement("recommendation_Xpath");
		Thread.sleep(5000);
		
		List <WebElement> allPrice =  getElements("priceListElements_Xpath");
		List <WebElement> allName =  getElements("nameListElements_Xpath");
		
		System.out.println("\n"+ "First Three Study Chair details with highest recommendation");
       Reporter.log("\n"+ "The price of the first three study chair");

       String pric, name;
       
       List<String> priceList = new ArrayList<>();
	
	     int size = allPrice.size();
	     for(int i=0;i<size;i++)
	     { 
	     pric = allPrice.get(i).getText();
	     name= allName.get(i).getText();
	     pric = pric.replaceAll("₹","");
	     pric = pric.replaceAll(",","");
	     priceList.add(name+" :");
    	 priceList.add("Rs."+pric);
	     }
	     for(int i=0;i<6;i++)
	     {
    	System.out.println(priceList.get(i).toString());
    	Reporter.log(priceList.get(i).toString());
	     }
	     getScreenshot();
	}
	
}
