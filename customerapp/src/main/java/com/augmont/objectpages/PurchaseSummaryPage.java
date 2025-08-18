package com.augmont.objectpages;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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

public class PurchaseSummaryPage extends BaseTest {
	
	public static double paidAmountFromWallet=0.0;
	public static double totalPurAmount=0.0;
	public static double netPayAmount=0.0;
	public static double purchaseAmount=0.0;
	public static double purchaseAmountTwoDigit=0.0;
	public static double purchaseAmountThreeDigit=0.0;
	public static double threeDigitTaxAmount=0.0;
	public static double twoDigitTaxAmount=0.0;
	public static double goldTax=0.0;
	double perGramPrice=0.0;

	By investPurposeText=AppiumBy.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
	By calendarOKBtn=AppiumBy.accessibilityId("OK");
	By proceedToPay=AppiumBy.xpath("//android.widget.ImageView[@content-desc='Proceed to Pay']");
	By investmentType=AppiumBy.xpath("//android.view.View[@content-desc='One Time']");	
	By goldGramAmount= AppiumBy.xpath("//android.view.View[@content-desc='Amount']/following-sibling::android.view.View[starts-with(@content-desc, '₹')]");
	By goldQuantity= AppiumBy.xpath("//android.view.View[@content-desc='Gold Quantity']/following-sibling::android.view.View[starts-with(@content-desc, 'gm')]");

	By totalAmount= AppiumBy.xpath("//android.view.View[@content-desc='Total Amount']/following-sibling::android.view.View[starts-with(@content-desc, '₹')]")
		;
	By paidAmount=AppiumBy.xpath("//android.view.View[@content-desc='Paid From Wallet']/following-sibling::android.view.View[starts-with(@content-desc, '₹')]");
	By netPaybleAmount=AppiumBy.xpath("//android.view.View[@content-desc='Net Payable Amount']/following-sibling::android.view.View[starts-with(@content-desc, '₹')]");
	By taxAmountElement=AppiumBy.xpath("//android.view.View[@content-desc='Tax']/following-sibling::android.view.View[starts-with(@content-desc, '₹')]");
	By goldPriceLive = AppiumBy.xpath("//android.view.View[@content-desc='Digital Gold Summary']/following-sibling::android.widget.ImageView[starts-with(@content-desc, 'Live Gold Price')]");

	
	
	public void emailIDSIPForm()
	{


WebElement emailIdTextBox = driver.findElement(AppiumBy.androidUIAutomator(
    			    "new UiSelector().className(\"android.widget.EditText\").instance(0)"
    			));	          
emailIdTextBox.click(); 
emailIdTextBox.clear(); 

	     Reporter.log("click Perfotm emailID Textbox",true);
	     try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		}
	     emailIdTextBox.sendKeys("pinklediwan@gmail.com");	     
	     Reporter.log("Email ID Is:",true);
	     extentTestChild.info("Email ID Is:");
	}

	
	public void insvestmentPurpose()
	{


WebElement investpurposetext = driver.findElement(AppiumBy.androidUIAutomator(
    			    "new UiSelector().className(\"android.widget.EditText\").instance(1)"
    			));	          
	     investpurposetext.click(); 
	     Reporter.log("click Perfotm",true);
	     try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		}
	     investpurposetext.sendKeys("Gold Loan Purpose");	     
	     Reporter.log("Purpose of Investment is:",true);
	     extentTestChild.info("Purpose of Investment is:");
	}
	public void insertSipStartDate()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Define formatter for output format
	       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	       // Get current date
	       LocalDate currentDate = LocalDate.now();
	       // Add 1 day
	       LocalDate newDate = currentDate.plusDays(1);
	       // Format the new date
	       String formattedDate = newDate.format(formatter);
	       // Output
	       System.out.println("New Date After Adding 1 day(Today + 1): " + formattedDate);
		//////////////////////////////////////////////////////////////////
	       WebElement dateElement = driver.findElement(By.xpath("//android.view.View[@text='"+formattedDate+"']")); 
	       dateElement.click();
	       Reporter.log("Date Calendare click perform",true);
	       extentTestChild.info("Date Calendare click perform");
	       ///////////////////////////////////////////////////////////
	       /////////////////////////////////////////select new date after add 6 day in calendar method
	       LocalDate selectedDate = LocalDate.parse(formattedDate, formatter);
	       LocalDate targetDate = selectedDate.plusDays(6);
	       
	       Reporter.log("Targeted Date is:"+targetDate,true);
	       if (targetDate.getMonthValue() != selectedDate.getMonthValue() || targetDate.getYear() != selectedDate.getYear()) {
	    	   WebElement button = driver.findElement(AppiumBy.androidUIAutomator(
	    			    "new UiSelector().className(\"android.widget.Button\").instance(3)"
	    			));
	    			button.click();       
	       
	       
	       
	       ////////////////////////////////////////
	    // 5. Format final output
	       String dayOfWeek = targetDate.getDayOfWeek().toString(); 
	       String formattedDay = dayOfWeek.substring(0, 1) + dayOfWeek.substring(1).toLowerCase(); 
	       String fullDate = targetDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)); 
	       String finalFormatted = targetDate.getDayOfMonth() + ", " + formattedDay + ", " + fullDate;

	       // 6. Output
	       System.out.println("Final Output: " + finalFormatted);
	       //////////////////////////////////////////////////////////////////////////
	       //click on final start Date
	       WebElement finalStartDate = driver.findElement(By.xpath("//android.widget.Button[@content-desc='"+finalFormatted+"']")); 
	       finalStartDate.click();
	       Reporter.log("Final Start Date add sucessfully perform",true);
	       
	       }
	       else
	       {
	    	// 5. Format final output
		       String dayOfWeek = targetDate.getDayOfWeek().toString(); 
		       String formattedDay = dayOfWeek.substring(0, 1) + dayOfWeek.substring(1).toLowerCase(); 
		       String fullDate = targetDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)); 
		       String finalFormatted = targetDate.getDayOfMonth() + ", " + formattedDay + ", " + fullDate;

		       // 6. Output
		       System.out.println("Final Output: " + finalFormatted);
		       //////////////////////////////////////////////////////////////////////////
		       //click on final start Date
		       WebElement finalStartDate = driver.findElement(By.xpath("//android.widget.Button[@content-desc='"+finalFormatted+"']")); 
		       finalStartDate.click();
		       Reporter.log("Final Start Date add sucessfully perform",true);
	       }


	}
	
	public void clickOnCalendarOkButton()
	{
		 	driver.findElement(calendarOKBtn).click();       
		 	Reporter.log("Click Perofom On Ok button SuceessFully",true);
		 	extentTestChild.info("Click Perofom On Ok button SuceessFully");
	       
	}
	public void clikOnProceedtoPay()
	{
		
		Wait<AndroidDriver> wait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(50))             // Total wait time
			    .pollingEvery(Duration.ofMillis(500))             // Polling interval
			    .ignoring(NoSuchElementException.class);          // Ignore exception

			WebElement element = wait.until(driver ->
			    driver.findElement(proceedToPay));			
			wait.until(ExpectedConditions.elementToBeClickable(element));	
		 	element.click();       
		 	Reporter.log("Click Perform On Proceed to pay SuceessFully",true);
		 	extentTestChild.info("Click Perform On Proceed to pay SuceessFully");
		 	
	}
	public double getLivePerGramGoldPrice()
	{
	    
			wait.until(ExpectedConditions.visibilityOfElementLocated(goldPriceLive));
			WebElement goldPriceLiveEle = driver.findElement(goldPriceLive);
			String samount = goldPriceLiveEle.getAttribute("content-desc");
			String amountString = samount.replaceAll("[^0-9.]", "");
			double amountValue = Double.parseDouble(amountString);
			Reporter.log("Gold Live Price/gm is:"+amountValue,true);
			extentTestChild.info("Gold Live Price/gm is:"+amountValue);
			perGramPrice=amountValue;
			return perGramPrice;
	}
	
	public void calculateGoldPurchaseAmount(double gramtoBuy) {
		double total = perGramPrice * gramtoBuy;

	    // Truncate to 3 decimal places (no rounding)
	    purchaseAmountThreeDigit = perGramPrice * gramtoBuy;

	    // Truncate to 2 decimal places (no rounding)
	    purchaseAmountTwoDigit = Math.floor(total * 100) / 100.0;

	    Reporter.log("Gold Purchase Amount (3 digits): " + purchaseAmountThreeDigit, true);
	    Reporter.log("Gold Purchase Amount (2 digits): " + purchaseAmountTwoDigit, true);

	    extentTestChild.info("Gold Purchase Amount (3 digits): " + purchaseAmountThreeDigit);
	    extentTestChild.info("Gold Purchase Amount (2 digits): " + purchaseAmountTwoDigit);
	}

	
	public void varifyInvestmentType(String investmentTypetext)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(investmentType));
		String investmentText=driver.findElement(investmentType).getAttribute("content-desc");
		Reporter.log("Investment Type is:"+investmentText,true);
		if(investmentText.equals(investmentTypetext))
		{
			Reporter.log("Investment Type is varified:"+investmentTypetext,true);
			extentTestChild.pass("Investment Type is varified:"+investmentTypetext);
					
		}
		else
		{
			Reporter.log("Investment Type is not varified:"+investmentTypetext,true);
			extentTestChild.fail("Investment Type is not varified:"+investmentTypetext);
					
		}
	}

	
	public double verifyTotalAmount(double expectedAmount) {
	    // Wait until the element is visible
	    wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount));

	    // Extract content-desc value
	    String stotalamt = driver.findElement(totalAmount).getAttribute("content-desc");
	    Reporter.log("Raw Total Amount from UI: " + stotalamt, true);

	    // Remove all non-numeric characters except the decimal point
	    String amountString = stotalamt.replaceAll("[^0-9.]", "");

	    // Parse to double and round
	    double actualAmount = Double.parseDouble(amountString);
	    actualAmount = Math.round(actualAmount * 100.0) / 100.0;
	    expectedAmount = Math.round(expectedAmount * 100.0) / 100.0;

	    // Format for display
	    String formattedActual = String.format("%.2f", actualAmount);
	    String formattedExpected = String.format("%.2f", expectedAmount);

	    // Log actual amount
	    System.out.println("Parsed Total Amount: ₹" + formattedActual);

	    double epsilon = 0.01;

	    // Verification
	    if (Math.abs(actualAmount - expectedAmount) <= epsilon) {
	        Reporter.log("✅ Total Amount Verified: ₹" + formattedActual, true);
	        extentTestChild.pass("Total Amount Verified: ₹" + formattedActual);
	    } else {
	        Reporter.log("❌ Total Amount Mismatch - Expected: ₹" + formattedExpected + ", Found: ₹" + formattedActual, true);
	        extentTestChild.fail("Total Amount Mismatch - Expected: ₹" + formattedExpected + ", Found: ₹" + formattedActual);
	    }

	    // Save to class-level variable
	    totalPurAmount = actualAmount;

	    return actualAmount;
	}
	public double verifyAmountUsingBigDecimal(double expectedAmount) {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount));
	    
	    String stotalamt = driver.findElement(totalAmount).getAttribute("content-desc");
	    Reporter.log("Total Amount is: " + stotalamt, true);

	    // Remove unwanted characters like ₹ and commas
	    String amountString = stotalamt.replaceAll("[^0-9.]", "");

	    // Convert to BigDecimal and round to 2 decimal places
	    BigDecimal actual = new BigDecimal(amountString).setScale(2, RoundingMode.DOWN);
	    BigDecimal expected = BigDecimal.valueOf(expectedAmount).setScale(2, RoundingMode.DOWN);

	    System.out.println("Actual (BigDecimal): ₹" + actual);
	    System.out.println("Expected (BigDecimal): ₹" + expected);

	    if (actual.compareTo(expected) == 0) {
	        Reporter.log("✅ Amount verified: ₹" + actual, true);
	        extentTestChild.pass("Amount verified: ₹" + actual);
	    } else {
	        Reporter.log("❌ Amount mismatch. Expected: ₹" + expected + ", Found: ₹" + actual, true);
	        extentTestChild.fail("Amount mismatch. Expected: ₹" + expected + ", Found: ₹" + actual);
	    }
	    totalPurAmount = actual.doubleValue();
	    return actual.doubleValue();

	}

	
	public void varifyGoldQuantity(double goldQty)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(goldQuantity));
		String sGoldQuantity=driver.findElement(goldQuantity).getAttribute("content-desc");
		Reporter.log("Gol Quantity is:"+sGoldQuantity,true);		
		String sgoldAmt = sGoldQuantity.replaceAll("[^0-9.]", "");
		double dGoldAmt = Double.parseDouble(sgoldAmt);
		System.out.println("Gold (double) Quantity: " + dGoldAmt);			
		if(dGoldAmt==goldQty)
		{
			Reporter.log("Gold Quantity is varified:"+dGoldAmt,true);
			extentTestChild.pass("Total Amount is varified:"+dGoldAmt);
					
		}
		else
		{

			Reporter.log("Gold Quantity is not varified:"+dGoldAmt,true);
			extentTestChild.fail("Gold Quantity is not varified:"+dGoldAmt);
					
		}
	}
	
	

	public double verifyGoldAmount(double expectedAmount) {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(goldGramAmount));

	    String stotalamt = driver.findElement(goldGramAmount).getAttribute("content-desc");
	    Reporter.log("Raw Gold Amount from UI: " + stotalamt, true);

	    // Clean and convert string to BigDecimal
	    String amountString = stotalamt.replaceAll("[^0-9.]", "");

	    // Convert both values with consistent rounding
	    BigDecimal actualAmount = new BigDecimal(amountString).setScale(2, RoundingMode.DOWN);
	    BigDecimal expected = BigDecimal.valueOf(expectedAmount).setScale(2, RoundingMode.DOWN);

	    System.out.println("Gold Amount (BigDecimal): ₹" + actualAmount);
	    System.out.println("Expected Amount (BigDecimal): ₹" + expected);

	    // Compare
	    if (actualAmount.compareTo(expected) == 0) {
	        Reporter.log("✅ Gold Amount Verified: Expected ₹" + expected + ", Found ₹" + actualAmount, true);
	        extentTestChild.pass("Gold Amount Verified: Expected ₹" + expected + ", Found ₹" + actualAmount);
	    } else {
	        Reporter.log("❌ Gold Amount Mismatch: Expected ₹" + expected + ", Found ₹" + actualAmount, true);
	        extentTestChild.fail("Gold Amount Mismatch: Expected ₹" + expected + ", Found ₹" + actualAmount);
	    }

	    purchaseAmount = actualAmount.doubleValue();
	    return actualAmount.doubleValue();
	}

	

	public void calculateGoldTaxAmount(double purchaseAmount, double taxPercentage) {
	    double taxAmount = (purchaseAmount * taxPercentage) / 100.0;

	    // Truncate without rounding
	     threeDigitTaxAmount =(purchaseAmount * taxPercentage) / 100.0;;
	     twoDigitTaxAmount = Math.floor(taxAmount * 100) / 100.0;

	    Reporter.log("Calculated Tax on Gold (3 digits): " + threeDigitTaxAmount, true);
	    Reporter.log("Calculated Tax on Gold (2 digits): " + twoDigitTaxAmount, true);

	    extentTestChild.info("Calculated Tax on Gold (3 digits): " + threeDigitTaxAmount);
	    extentTestChild.info("Calculated Tax on Gold (2 digits): " + twoDigitTaxAmount);

	}


	

	public double verifyGoldTaxAmount(double expectedTaxAmount) {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(taxAmountElement));

	    String sTaxAmount = driver.findElement(taxAmountElement).getAttribute("content-desc");
	    Reporter.log("Displayed Gold Tax Amount (raw): " + sTaxAmount, true);

	    // Extract numeric value
	    String amountString = sTaxAmount.replaceAll("[^0-9.]", "");

	    // Use BigDecimal for precise handling
	    BigDecimal actualTax = new BigDecimal(amountString).setScale(2, RoundingMode.DOWN);
	    BigDecimal expectedTax = BigDecimal.valueOf(expectedTaxAmount).setScale(2, RoundingMode.DOWN);


	    Reporter.log("Parsed Gold Tax Amount: ₹" + actualTax, true);
	    System.out.println("Expected Gold Tax Amount: ₹" + expectedTax);

	    // Compare using BigDecimal
	    if (actualTax.compareTo(expectedTax) == 0) {
	        Reporter.log("✅ Gold Tax Amount verified successfully. Expected: ₹" + expectedTax + ", Actual: ₹" + actualTax, true);
	        extentTestChild.pass("Gold Tax Amount verified: ₹" + actualTax);
	    } else {
	        Reporter.log("❌ Gold Tax Amount verification failed. Expected: ₹" + expectedTax + ", Actual: ₹" + actualTax, true);
	        extentTestChild.fail("Gold Tax Amount mismatch. Expected: ₹" + expectedTax + ", Actual: ₹" + actualTax);
	    }

	    goldTax = actualTax.doubleValue();  // assign to global variable if still needed as double
	    return actualTax.doubleValue();
	}


	
	public double verifyPaidAmountFromWallet(double expectedAmount) {
	    // Wait for the paid amount element to be visible
	    wait.until(ExpectedConditions.visibilityOfElementLocated(paidAmount));
	    
	    // Get the amount string
	    String stotalamt = driver.findElement(paidAmount).getAttribute("content-desc");
	    Reporter.log("Paid Amount (raw): " + stotalamt, true);
	    
	    // Clean and parse the amount
	    String amountString = stotalamt.replaceAll("[^0-9.]", "");
	    double actualAmount = Double.parseDouble(amountString);
	    System.out.println("Paid Amount (double): " + actualAmount);
	    
	    // Compare with epsilon to avoid floating-point issues
	    double epsilon = 0.01; // Tolerance for decimal comparison
	    if (Math.abs(actualAmount - expectedAmount) <= epsilon) {
	        Reporter.log("✅ Paid Amount from Wallet is verified: " + expectedAmount, true);
	        extentTestChild.pass("Paid Amount from Wallet is verified: " + expectedAmount);
	    } else {
	        Reporter.log("❌ Paid Amount from Wallet is not verified. Actual: " + actualAmount + ", Expected: " + expectedAmount, true);
	        extentTestChild.fail("Paid Amount from Wallet is not verified. Actual: " + actualAmount + ", Expected: " + expectedAmount);
	    }
	    paidAmountFromWallet=actualAmount;
	    return actualAmount;
	}
	public double calculateAndPrintNetPayable() {
	    double netPayableAmount = totalPurAmount - paidAmountFromWallet;
	    // Calling/using the variable:
	    System.out.println("Net Payable Amount: ₹" + netPayableAmount);
	    Reporter.log("Net Payable Amount is: ₹" + netPayableAmount, true);
	    return netPayableAmount;
	}
	
	public double calculateAndPrintNetPayableRoundingOff() {
	    double netPayableAmount = purchaseAmountThreeDigit+threeDigitTaxAmount - paidAmountFromWallet;
	    // Calling/using the variable:
	    System.out.println("Net Payable Amount: ₹" + netPayableAmount);
	    Reporter.log("Net Payable Amount is: ₹" + netPayableAmount, true);
	    netPayAmount=netPayableAmount;
	    return netPayableAmount;
	}

	

	public double verifyNetPayableAmount(double expectedAmount) {
	    // Wait for the Net Payable Amount element to appear
	    wait.until(ExpectedConditions.visibilityOfElementLocated(netPaybleAmount));

	    // Extract raw text from the UI element
	    String payableAmountText = driver.findElement(netPaybleAmount).getAttribute("content-desc");
	    Reporter.log("Raw Net Payable Amount from UI: " + payableAmountText, true);

	    // Remove non-numeric characters like ₹, spaces, etc.
	    String amountString = payableAmountText.replaceAll("[^0-9.]", "");

	    // Convert to BigDecimal and set scale to 2 decimal places (no rounding up)
	    BigDecimal actualAmount = new BigDecimal(amountString).setScale(2, RoundingMode.DOWN);
	    BigDecimal expected = BigDecimal.valueOf(expectedAmount).setScale(2, RoundingMode.DOWN);

	    System.out.println("Net Payable Amount (Actual): ₹" + actualAmount);
	    System.out.println("Net Payable Amount (Expected): ₹" + expected);

	    // Compare both amounts
	    if (actualAmount.compareTo(expected) == 0) {
	        Reporter.log("✅ Net Payable Amount Verified: Expected ₹" + expected + ", Found ₹" + actualAmount, true);
	        extentTestChild.pass("Net Payable Amount Verified: Expected ₹" + expected + ", Found ₹" + actualAmount);
	    } else {
	        Reporter.log("❌ Net Payable Amount Mismatch: Expected ₹" + expected + ", Found ₹" + actualAmount, true);
	        extentTestChild.fail("Net Payable Amount Mismatch: Expected ₹" + expected + ", Found ₹" + actualAmount);
	    }

	    // Save actual amount if needed elsewhere
	     return actualAmount.doubleValue();
	    
	}

	
	
}
