
package com.Selenium.GenrateReport;

import java.util.Date;

public class DateUtils{

	/*********************    GET UNIQUE TIMING   **********************/
public static String getTimeStamp(){
	
Date date=new Date();
return date.toString().replaceAll(":","_").replaceAll(" ","_");

}
}
