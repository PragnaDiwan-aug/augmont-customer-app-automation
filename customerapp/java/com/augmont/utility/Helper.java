package com.augmont.utility;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.augmont.base.BaseTest;


public class Helper extends BaseTest {
	public static String captureScreenShots(WebDriver webDriver,String testName) {
		File src=((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
		String screenShotPath= System.getProperty("user.dir")+"/Screenshots/"+""+testName+""+getCurretTimeStamp()+".png";
	    try {
			FileHandler.copy(src,new File(screenShotPath));
			System.out.println("ScreenShots Captured");
		} catch (IOException e) {
			System.out.println("Unable to capture screenshots"+e.getMessage());
		}
	    return screenShotPath;
	}
	public static String getCurretTimeStamp() {
		DateFormat getFormat=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date currentDate=new Date();
		return getFormat.format(currentDate);
	}
	public static String getCurretTimeStampForRCRQ() {
		DateFormat getFormat=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");
		Date currentDate=new Date();
		return getFormat.format(currentDate);
	}
	public String timeStamp() {
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy_MM_dd__hh_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		return now.format(dft); // change by vivek mane 20 april 22
		}
	
	public static  String getScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);		
		try {
			FileHandler.copy(src,destination);
			System.out.println("ScreenShots Captured");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
}
	
	
				

			
			}
		


		
		 
	

