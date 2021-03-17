package com.Selenium.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.Selenium.ReadInputExcel.*;


public class BookShelves extends BaseUI{
	
	 WebDriver driver;

	public BookShelves(WebDriver driver)
	{
		this.driver = driver;
		logger = report.createTest("Find BookShelves");
	}

	/****************   TO SERACH BOOKSHELVES BELOW 15000   ****************/
	public void searchBookshelf() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		clickElement("popUp_Xpath");		
		enterText("searchShelvesTextbox_Id", ReadExcelData.readExcelDataForSearchValue(1,0));
				
				
		clickElement("searchButton_Id");
		
		
		clickElement("category_Xpath");
		clickElement("booShelves_Xpath");
		
		clickElement("storageType_Xpath");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='filters_storage_type_Open']")).click();  
		clickElement("stockCheckbox_Xpath");
			

			Thread.sleep(5000);
			
			List <WebElement> allPrice =  getElements("priceListElements_Xpath");
			List <WebElement> allName =  getElements("nameListElements_Xpath");
		  
	        System.out.println("\n"+ "The price of the first three bookshelves below Rs.15000:");

	        String pric, name;
	     
		     List<String> priceList = new ArrayList<>();
		     int price;
		
		     int size = allPrice.size();
		     for(int i=0;i<size;i++)
		     { 
		     pric = allPrice.get(i).getText();
		     name= allName.get(i).getText();
		     pric = pric.replaceAll("₹","");
		     pric = pric.replaceAll(",","");
		     price = Integer.parseInt(pric);
		     
			     if(price < 15000)
			     {
				        	priceList.add(name+" :");
				        	priceList.add("Rs."+pric);
				 } 
			     
		     }
		     
		     for(int i=0;i<6;i++)
		     {
	     	System.out.println(priceList.get(i).toString());
	     	
		     }
		     getScreenshot();
		}
	
}

