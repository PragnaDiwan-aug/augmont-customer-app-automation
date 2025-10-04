package com.augmont.objectpages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.Reporter;

import com.augmont.base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PurchasePage extends BaseTest {
	
	

	public static double gramToBuy=0.5;
	public static double gramToBuySilver=1;

	By reivestmentAndMore=By.xpath("//android.widget.ImageView[@content-desc=\"Reinvest & Earn More\"]");
	By goldInvesment=AppiumBy.accessibilityId("Gold Investments");
	By silverInvesment=AppiumBy.accessibilityId("Silver Investments");
	By startInvestingGold=AppiumBy.accessibilityId("Start Investing in Gold");
	By startInvestingSilver=AppiumBy.accessibilityId("Start Investing in Silver");
	By onetimeBtn=AppiumBy.accessibilityId("One Time");
	By investmentPatternAmount=AppiumBy.accessibilityId("Amount");
	By investmentPatternGrams=AppiumBy.accessibilityId("Grams");
	By sipRecommend=AppiumBy.accessibilityId("SIP (Recommended)");
	By weeklyInvestment=AppiumBy.accessibilityId("Weekly");
	By goldInvestAmt=By.xpath("//android.widget.EditText");
	By gramsValueEle=AppiumBy.xpath("//android.view.View[@content-desc='Enter Grams *']/following-sibling::android.widget.EditText");
	By proceedClick=AppiumBy.accessibilityId("Proceed");
	By manuallyEnteredAmountEle=AppiumBy.xpath("//android.view.View[@content-desc='₹']/following-sibling::android.widget.EditText");
	
	
	public void clickOnInvestmentAndEarnMore()
	{
		
		Wait<AndroidDriver> wait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(50))             // Total wait time
			    .pollingEvery(Duration.ofMillis(500))             // Polling interval
			    .ignoring(NoSuchElementException.class);          // Ignore exception

			WebElement element = wait.until(driver ->
			    driver.findElement(reivestmentAndMore));	      
			wait.until(ExpectedConditions.elementToBeClickable(reivestmentAndMore));		
		Actions action=new Actions(driver);
		action.moveToElement(element).click().build().perform();
	     Reporter.log("Click Perform On ReInvestAndMore SuceessFully",true);
	     extentTestChild.info("Click Perform On ReInvestAndMore SuceessFully");
	}
	public void clickOnGoldInvestment()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(goldInvesment));
		 driver.findElement(goldInvesment).click();
	     Reporter.log("Click Perform On Gold investment SuceessFully",true);
		 extentTestChild.info("Click Perform On Gold investment SuceessFully");
	}
	public void clickOnSilverInvestment()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(silverInvesment));
		 driver.findElement(silverInvesment).click();
	     Reporter.log("Click Perform On Silver investment SuceessFully",true);
		 extentTestChild.info("Click Perform On Silver investment SuceessFully");
	}

	public void clickOnStartInvestingGold()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(startInvestingGold));
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(startInvestingGold)).click().build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Click Perform On Start Investing in Gold SuceessFully",true);
		extentTestChild.info("Click Perform On Start Investing in Gold SuceessFully");
	}
	
	public void clickOnStartInvestingSilver()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(startInvestingSilver));
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(startInvestingSilver)).click().build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Click Perform On Start Investing in Silver SuceessFully",true);
		extentTestChild.info("Click Perform On Start Investing in Silver SuceessFully");
	}

	public void clickOnOneTimeBtn()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(onetimeBtn));
		 driver.findElement(onetimeBtn).click();
	     Reporter.log("Click Perform On One Time Button SuceessFully",true);
		 extentTestChild.info("Click Perform On One Time Button SuceessFully");
	}
	
	public void clickOnAmountBtn()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(investmentPatternAmount));
		 driver.findElement(investmentPatternAmount).click();
	     Reporter.log("Click Perform On Amount button SuceessFully",true);
		 extentTestChild.info("Click Perform On Amount button SuceessFully");
	}
	public void clickOnGramsBtn()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(investmentPatternGrams));
		 driver.findElement(investmentPatternGrams).click();
	     Reporter.log("Click Perform On Grams button SuceessFully",true);
		 extentTestChild.info("Click Perform On Grams button SuceessFully");
	}
	
	public void clickOnSIPRecommend()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(sipRecommend));
        driver.findElement(sipRecommend).click();
        Reporter.log("Click Perform On Sip Recommend SuceessFully",true);   
		extentTestChild.info("Click Perform On Sip Recommend SuceessFully");
	}
	public void clickOnWeeklyInvestment()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(weeklyInvestment));
		driver.findElement(weeklyInvestment).click();
	    Reporter.log("Click Perform On Weekely SuceessFully",true);
		extentTestChild.info("Click Perform On Weekely SuceessFully");
	      
	}
	public void insertInvestmentAmount()
	{
		 int amt = 82000;
	      WebElement goldamt = driver.findElement(goldInvestAmt);
	        goldamt.click();
	        // Wait briefly to ensure focus
	        try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();		}
	        // Move cursor to the end (optional but helps)
	        driver.pressKey(new KeyEvent(AndroidKey.MOVE_END));
	        // Send backspaces to clear (e.g., 10 characters max)
	        for (int i = 0; i < 10; i++) {
	            driver.pressKey(new KeyEvent(AndroidKey.DEL));
	            try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // small delay to ensure each delete is processed
	        }

	        // Enter new amount
	        goldamt.sendKeys(String.valueOf(amt));

	        // Confirm input (log or assert)
	        Reporter.log("Gold Amount is: " + amt, true);
	        extentTestChild.info("invested Gold Amount is:"+amt);
	}
	
	public void selectInvAmtFromAmountTab(double amount1)
	{
		String amount = String.valueOf(amount1); // or get from Excel/DataProvider
		String xpath = String.format("//android.view.View[@content-desc='₹ %s']", amount);
		WebElement amountElement = driver.findElement(By.xpath(xpath));
		amountElement.click();
		Reporter.log("Selected Tab Amount is:"+amount,true);
		extentTestChild.info("Selected Tab Amount is:"+amount);
	}
	
	public void selectInvGoldInGramFromGramTab(String contentDesc)
	{
		final String SCROLLABLE_CLASS = "android.widget.ScrollView";
		 String uiAutomatorString =
			        "new UiScrollable(new UiSelector().className(\"" + SCROLLABLE_CLASS + "\"))" +
			        ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"))";
			    
			    WebElement element = driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorString));
			    element.click();
		 
		Reporter.log("Selected Gram Value is:"+contentDesc,true);
		extentTestChild.info("Selected Gram Value is:"+contentDesc);
	}
	
	
	public void enterGramValue(double gramtobuy) {
		final String SCROLLABLE_CLASS = "android.widget.ScrollView";
		String contentDesc="5 Grams";
		 String uiAutomatorString =
			        "new UiScrollable(new UiSelector().className(\"" + SCROLLABLE_CLASS + "\"))" +
			        ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"))";
			    
			    WebElement element = driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorString));

	    Wait<AndroidDriver> wait = new FluentWait<>(driver)
	        .withTimeout(Duration.ofSeconds(30))
	        .pollingEvery(Duration.ofSeconds(2))
	        .ignoring(NoSuchElementException.class);

	    WebElement gramInputField = wait.until(driver -> driver.findElement(gramsValueEle));
	    gramInputField.click();
	    gramInputField.clear();
	    gramInputField.sendKeys(String.valueOf(gramtobuy));

	    Reporter.log("Entered Gram Value: " + gramtobuy, true);
	    extentTestChild.info("Entered Gram Value: " + gramtobuy);
	}
	
	public void enterGoldAmountManually(int amount) {
		
		final String SCROLLABLE_CLASS = "android.widget.ScrollView";
		String contentDesc="₹ 500";
		 String uiAutomatorString =
			        "new UiScrollable(new UiSelector().className(\"" + SCROLLABLE_CLASS + "\"))" +
			        ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"))";
			    
			    WebElement element = driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorString));

	    Wait<AndroidDriver> wait = new FluentWait<>(driver)
	        .withTimeout(Duration.ofSeconds(30))
	        .pollingEvery(Duration.ofMillis(500))
	        .ignoring(NoSuchElementException.class);
	    
	    WebElement gramInputField = wait.until(driver -> driver.findElement(manuallyEnteredAmountEle));
	    gramInputField.click();
	    gramInputField.clear();
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    gramInputField.sendKeys(String.valueOf(amount));

	    Reporter.log("Entered  Value: " + amount, true);
	    extentTestChild.info("Entered  Value: " + amount);
	}


	
	public void clickOnProceed()
	{
		  driver.findElement(proceedClick).click();       
	      Reporter.log("Click Perform On Proceed SuceessFully",true);
	      extentTestChild.info("Click perform On Proceed SuceessFully");
	      
	}
	

}
