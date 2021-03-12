package com.Selenium.GenrateReport;


import com.Selenium.Pages.BaseUI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class GenrateReport extends BaseUI{
	
	public  static ExtentReports  reports;
	
	/****************THIS IS FOR EXTENT REPORT****************/
	public static  ExtentReports getExtentReport()
	{		
		if(reports == null) {
			ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\com\\Selenium\\GenrateReport\\HtmlReport.html");
		    reports = new ExtentReports();
		    reports.attachReporter(htmlReporter);
		    htmlReporter.config().setDocumentTitle("Urbanladder Find BookShelves Automation");
		}
		return reports;
	}
}

