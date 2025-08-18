package com.augmont.objectpages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import com.augmont.base.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class TransactionPage extends BaseTest {
	
	
	By transactionHistory=AppiumBy.xpath("//android.widget.Button[@content-desc='Transaction History']");
	By amountfromTransactionHistory=AppiumBy.xpath(
			  "//android.view.View[@content-desc='Latest \nInvestment']/following-sibling::android.view.View[1]//android.view.View[contains(@content-desc, 'Gold Bought')][1]"
			);

	By amountFromWalletFundHistory=AppiumBy.xpath("//android.widget.ImageView[@content-desc='Deposit Transaction Log']/following-sibling::android.view.View[1]//android.view.View[contains(@content-desc, 'Amount added to your Augmont Wallet')][1]"
			);
	By amountFromWalletFundHistoryPartiallPayEle=AppiumBy.xpath("//android.widget.ImageView[@content-desc='Deposit Transaction Log']/following-sibling::android.view.View[1]//android.view.View[contains(@content-desc, 'Amount added to your Augmont Wallet')][2]"
			);

	public void clikOnTransactionHistoryTab()
	{
		Wait<AndroidDriver> wait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(40))             // Total wait time
			    .pollingEvery(Duration.ofMillis(500))             // Polling interval
			    .ignoring(NoSuchElementException.class);        // Ignore exception			
			WebElement element = wait.until(driver ->
		    driver.findElement(transactionHistory)
		);
		wait.until(ExpectedConditions.elementToBeClickable(element));	
		element.click();   

		 	Reporter.log("Click Perofom transaction History Tab SuceessFully",true);
		 	extentTestChild.info("Click Perofom transaction History Tab SuceessFully");
			
	}
	
	public void getTransactionAmount(double expectedAmount) {
	    // Wait for the element to be visible
		Wait<AndroidDriver> wait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(40))             // Total wait time
			    .pollingEvery(Duration.ofMillis(500))             // Polling interval
			    .ignoring(NoSuchElementException.class);        // Ignore exception			
			WebElement element = wait.until(driver ->
		    driver.findElement(amountfromTransactionHistory)
		);

	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    String rawAmountText = driver.findElement(amountfromTransactionHistory).getAttribute("content-desc");
	    System.out.println("Raw content-desc: " + rawAmountText);

	    // Extract and clean numeric part after ₹
	    int rupeeIndex = rawAmountText.lastIndexOf("₹");
	    String cleanedAmountText = rawAmountText.substring(rupeeIndex).replaceAll("[^0-9.]", "").trim();

	    Reporter.log("Paid  Amount (Text): ₹" + cleanedAmountText, true);

	    // Convert to BigDecimal
	    BigDecimal actualAmount = new BigDecimal(cleanedAmountText).setScale(2, RoundingMode.DOWN);
	    BigDecimal expectedAmountBD = BigDecimal.valueOf(expectedAmount).setScale(2, RoundingMode.HALF_UP);

	    System.out.println("Parsed Actual Amount: ₹" + actualAmount);
	    System.out.println("Expected Amount: ₹" + expectedAmountBD);

	    // Compare using BigDecimal
	    if (actualAmount.compareTo(expectedAmountBD) == 0) {
	        Reporter.log("✅ Paid Amount verified. Expected: " + expectedAmountBD + ", Found: " + actualAmount, true);
	        extentTestChild.pass("Paid Amount verified. Expected: " + expectedAmountBD + ", Found: " + actualAmount);
	    } else {
	        Reporter.log("❌ Paid Amount mismatch. Expected: " + expectedAmountBD + ", Found: " + actualAmount, true);
	        extentTestChild.fail("Paid Amount mismatch. Expected: " + expectedAmountBD + ", Found: " + actualAmount);
	    }
	}
	
	public static String extractAmountUsingSplit(String rawText) {
	    if (rawText == null || !rawText.contains("₹")) {
	        return null;
	    }

	    // Split by ₹
	    String[] parts = rawText.split("₹");
	    if (parts.length < 2) {
	        return null;
	    }

	    // The part after ₹, trim and split by whitespace or newline to isolate amount
	    String afterRupee = parts[1].trim();
	    String[] tokens = afterRupee.split("\\s+|\\n");
	    
	    // First token should be the amount
	    if (tokens.length > 0) {
	        return tokens[0];
	    }
	    
	    return null;
	}


	public void getTransactionAmountForWallet(double expectedAmount) {
	    // Wait until the wallet amount element is visible
	    wait.until(ExpectedConditions.visibilityOfElementLocated(amountFromWalletFundHistory));
	    // Read content-desc from the element
	    String rawAmountText = driver.findElement(amountFromWalletFundHistory).getAttribute("content-desc");
	    System.out.println("Raw content-desc: " + rawAmountText);
	    

	        // parts[1] starts with the number you want, extract digits and dot
	        String amountPart = extractAmountUsingSplit(rawAmountText);
	        System.out.println("Extracted Amount: " + amountPart);
	    


//	    // Extract and clean numeric part after ₹
//	    int rupeeIndex = rawAmountText.lastIndexOf("₹");
//	    String cleanedAmountText = rawAmountText.substring(rupeeIndex).replaceAll("[^0-9.]", "").trim();


	    // Convert to BigDecimal
	    BigDecimal actualAmount = new BigDecimal(amountPart).setScale(2, RoundingMode.DOWN);
	    BigDecimal expectedAmountBD = BigDecimal.valueOf(expectedAmount).setScale(2, RoundingMode.HALF_UP);

	    System.out.println("Parsed Actual Amount: ₹" + actualAmount);
	    System.out.println("Expected Amount: ₹" + expectedAmountBD);

	    // Compare using BigDecimal
	    if (actualAmount.compareTo(expectedAmountBD) == 0) {
	        Reporter.log("✅ Paid Amount verified. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount, true);
	        extentTestChild.pass("Paid Amount verified. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount);
	    } else {
	        Reporter.log("❌ Paid Amount mismatch. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount, true);
	        extentTestChild.fail("Paid Amount mismatch. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount);
	    }
	}


	
	public void getTransactionAmountForWalletPartiallyPayment(double expectedAmount) {
	    // Wait for the element to be visible
	    wait.until(ExpectedConditions.visibilityOfElementLocated(amountFromWalletFundHistoryPartiallPayEle));

	    // Get the raw amount from UI
	    String rawAmountText = driver.findElement(amountFromWalletFundHistoryPartiallPayEle).getAttribute("content-desc");
	    System.out.println("Raw content-desc: " + rawAmountText);

//	    // Extract and clean the numeric value
//	    int rupeeIndex = rawAmountText.lastIndexOf("₹");
//	    String cleanedAmountText = rawAmountText.substring(rupeeIndex).replaceAll("[^0-9.]", "").trim();
//
	    

        // parts[1] starts with the number you want, extract digits and dot
        String amountPart = extractAmountUsingSplit(rawAmountText);
        System.out.println("Extracted Amount: " + amountPart);
    


	    // Convert to BigDecimal
	    BigDecimal actualAmount = new BigDecimal(amountPart).setScale(2, RoundingMode.DOWN);
	    BigDecimal expectedAmountBD = BigDecimal.valueOf(expectedAmount).setScale(2, RoundingMode.HALF_UP);

	    System.out.println("Parsed Actual Amount: ₹" + actualAmount);
	    System.out.println("Expected Amount: ₹" + expectedAmountBD);

	    // Compare using BigDecimal
	    if (actualAmount.compareTo(expectedAmountBD) == 0) {
	        Reporter.log("✅ Wallet Amount verified. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount, true);
	        extentTestChild.pass("Wallet Amount verified. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount);
	    } else {
	        Reporter.log("❌ Wallet Amount mismatch. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount, true);
	        extentTestChild.fail("Wallet Amount mismatch. Expected: ₹" + expectedAmountBD + ", Found: ₹" + actualAmount);
	    }

	}

}
