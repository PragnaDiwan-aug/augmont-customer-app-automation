package com.augmont.objectpages;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.augmont.base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;


public class HomePage extends BaseTest {

By homeMenuClick=AppiumBy.accessibilityId("Home");	
By clickOnSkipLink=AppiumBy.accessibilityId("Skip");
By clickOnSignInSignUpLink=AppiumBy.accessibilityId("Sign Up/Sign In");
By clickOnRegister=AppiumBy.accessibilityId("Register/Sign-In Now");
By mobileNumberTextBox=By.xpath("//android.widget.EditText");
By noneOftheAboveLink=By.xpath("//android.view.View[contains(@content-desc, 'NONE OF THE ABOVE')]");
By generateOTP=AppiumBy.accessibilityId("Generate OTP");
By logOutLink=AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Logout\"]");	


public void clickOnHomeMenu()
{
	Wait<AndroidDriver> wait = new FluentWait<>(driver)
		    .withTimeout(Duration.ofSeconds(40))             // Total wait time
		    .pollingEvery(Duration.ofMillis(500))             // Polling interval
		    .ignoring(NoSuchElementException.class);        // Ignore exception		
		WebElement element = wait.until(driver ->
	    driver.findElement(homeMenuClick)
	);
		wait.until(ExpectedConditions.elementToBeClickable(homeMenuClick));
	element.click();   
    Reporter.log("Click Perform on Home Menu",true);
    extentTestChild.info("Click Perform on Home Menu");    
}


public void clickOnSkipLinkMethod()
{
	Wait<AndroidDriver> wait = new FluentWait<>(driver)
		    .withTimeout(Duration.ofSeconds(50))             // Total wait time
		    .pollingEvery(Duration.ofMillis(500))             // Polling interval
		    .ignoring(NoSuchElementException.class);        // Ignore exception		
		WebElement element = wait.until(driver ->
	    driver.findElement(clickOnSkipLink)
	);   
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
    Reporter.log("Click Perform on Skip Button",true);
    extentTestChild.info("Click perform on Skip button");    
}
public void clickOnSignInSignUpMethod()
{
	  driver.findElement(clickOnSignInSignUpLink).click();
      Reporter.log("Click Perform on sign up Link",true);
      extentTestChild.info("Click Perform on sign up Link"); 
      }
public void clickOnRegisterLink()
{
    driver.findElement(AppiumBy.accessibilityId("Register/Sign-In Now")).click();
    Reporter.log("Click Perform on RegisterorSignin Now Button",true);
    extentTestChild.info("Click Perform on RegisterorSignin Now Button"); 

}
public void mobileNumberTextBoxClick()
{
    wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumberTextBox));
    WebElement mobileNum=driver.findElement(mobileNumberTextBox);
    mobileNum.click();
}
public void noneOftheAboveLinkClick()
{
	WebElement btn = driver.findElement(noneOftheAboveLink);
    btn.click();
    Reporter.log("click Perform On None of The Above Link",true);
    extentTestChild.info("click Perform On None of The Above Link");     
}
public void insertMobileNumber(long mobileNumber)
{
	wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumberTextBox));
	WebElement btn = driver.findElement(mobileNumberTextBox);
	btn.clear();
	btn.sendKeys(String.valueOf(mobileNumber));
     Reporter.log("Inserted Mobile Number is:"+mobileNumber,true);
     extentTestChild.info("Inserted Mobile Number is:"+mobileNumber);
}

public void clickOnGenerateOTP()
{
	wait.until(ExpectedConditions.visibilityOfElementLocated(generateOTP));
	WebElement genOTP= driver.findElement(generateOTP);
	genOTP.click();
    Reporter.log("Click peform on Generate OTP",true);
    extentTestChild.info("Click peform on Generate OTP");
}

public void insertOTP(long otp)

{  

	try {
		Thread.sleep(4000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	// Tap on specific coordinates
	Map<String, Object> tap = new HashMap<>();
	tap.put("x", 106);  //962
	tap.put("y", 908); //1006
	//tap.put("duration", 100);  // Optional: add short duration in ms
	driver.executeScript("mobile: clickGesture", tap);

	// Wait for UI to become ready for input
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}  // Consider replacing with proper wait

	// Send OTP via adb shell input
	Map<String, Object> shellCommand = new HashMap<>();
	shellCommand.put("command", "input");
	//shellCommand.put("args", List.of("text", "123456"));
	shellCommand.put("args", List.of("text", String.valueOf(otp)));

	driver.executeScript("mobile: shell", shellCommand);
     Reporter.log("Enter OTP Sucessfully",true);
     extentTestChild.info("Enter OTP Sucessfully");
     try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void handleSkipButtonIfPresent() {
    try {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));        
        WebElement skipButton = shortWait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.xpath("//android.view.View[@content-desc='Skip']")));
        
        if (skipButton.isDisplayed()) {
            skipButton.click();
            System.out.println("Skip button was present and clicked.");
        }
    } catch (TimeoutException e) {
        System.out.println("Skip button not displayed. Continuing without clicking.");
    }
}
public void validateLoginSuccess() {
    try {
        Wait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        // Replace with any unique home page element
        WebElement homePageElement = wait.until(driver ->
                driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Reinvest & Earn More\"]")) // <- Update as per your app
        );

        if (homePageElement.isDisplayed()) {
            extentTestChild.pass("Login successful – Home Page is displayed.");
            Reporter.log("Login validation passed: User is on Home Page", true);
        } else {
            extentTestChild.fail("Login failed – Home Page element not visible.");
            Reporter.log("Login validation failed: Element not displayed", true);
        }

    } catch (TimeoutException e) {
        extentTestChild.fail("Login failed – Home Page did not load in time.");
        Reporter.log("Login validation failed: Timeout", true);
        throw e;
    }
}





public void logOut()
{
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 driver.findElement(AppiumBy.androidUIAutomator(
		    "new UiScrollable(new UiSelector().scrollable(true))" +
		    ".scrollIntoView(new UiSelector().description(\"Logout\"))"	));
	 
	 wait.until(ExpectedConditions.elementToBeClickable(logOutLink));	
	driver.findElement(logOutLink).click();   
	//driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Logout\"]")).click();
    Reporter.log("Logged out of the app",true);
    extentTestChild.info("Logged out of the app");    
}
}

