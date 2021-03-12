package com.Selenium.Pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


import com.Selenium.ReadInputExcel.ReadExcelData;

public class GiftcardErrorMessage extends BaseUI {

	WebDriver driver;
	
	public GiftcardErrorMessage(WebDriver driver)
	{
		this.driver = driver;
		logger = report.createTest("GiftCard ErrorMEssage Verfication");
	}
	
	/****************   GIFT CARD ERROR MESSAGE   ****************/
	public void displayErrorMessage() throws Exception
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.get("https://www.urbanladder.com/");
		Thread.sleep(5000);
		
	    clickElement("giftCard_Xpath");	
	    clickElement("occasion_Xpath");			
	    
	    enterText("amount_Xpath", ReadExcelData.readExcelDataForGiftCard(1,0));
	    clickElement("cardNextButton_Xpath");
	    
	    enterText("senderName_Xpath", ReadExcelData.readExcelDataForGiftCard(1,1));
	    enterText("sendersMail_Xpath", ReadExcelData.readExcelDataForGiftCard(1,2));
	    
	    enterText("recipientsPhoneNo_Xpath", ReadExcelData.readExcelDataForGiftCard(1,3));
	    enterText("recipientsName_Xpath", ReadExcelData.readExcelDataForGiftCard(1,4));
	    enterText("recipientsMail_Id", ReadExcelData.readExcelDataForGiftCard(1,5));
		reportPass("Successfully Retrived Data From ExcelSheet");
		reportPass("Successfully Entered Into Textfields");
	    clickElement("confirmButton_Xpath");	
		
	    try {
	    	
		String ErrorMessage= getElement("recipientsMail_Id").getAttribute("validationMessage");
		System.out.println("\n"+"The error message appeared for invalid e-mail: " +ErrorMessage +"\n");
		reportPass("Error Message: "+ErrorMessage+" is displayed");
		getScreenshot();
		
		}
	    catch(Exception e) {reportFail("Error Message Not Displayed");}

	}
}
