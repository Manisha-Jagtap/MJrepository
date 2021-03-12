package com.Selenium.Pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CollectionSublist extends BaseUI{
	
	 WebDriver driver;

	public CollectionSublist(WebDriver driver)
	{
		this.driver = driver;
		logger = report.createTest("Retrive SubmenuList of Collections");
	}

	
	/**************** RETRIVE SUB_MENU LIST  ****************/
	public void retriveSubmenuList() throws Exception
	 {
		 driver.get("https://www.urbanladder.com/");				
      clickElement("collectionElements_Xpath");

      List<WebElement> allMenu = getElements("submenuElements_Xpath");
      System.out.println("\n"+"The Sub-menu list from 'Being at home' column:");
  
      List<String> submenuList = new ArrayList<>();
      int submenuSize = allMenu.size();
      
      for(int i=0;i<submenuSize;i++)
	        { 
     	 String menu = allMenu.get(i).getText();
     	 submenuList.add(menu);
	        }
      submenuList.removeAll(Arrays.asList("", null));
      
      
      for(int i=0;i<13;i++)
	        {
	        	System.out.println(submenuList.get(i).toString());
	        }

            reportPass("All Sub_menues Retrived Sccessfully");
            getScreenshot();
	    }



}
