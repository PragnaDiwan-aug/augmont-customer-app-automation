package com.augmont.objectpages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.augmont.base.BaseTest;

import io.appium.java_client.AppiumBy;

public class CommonMethod extends BaseTest {
    public String webviewContext = null;

	
	public void backButton()
	{
		driver.navigate().back();
		Reporter.log("Back Button Click perform",true);
		extentTestChild.info("Back Button Click perform");
	}
	public WebElement scrollToElement(By locator) {
	    // Try forward scroll first
	    for (int i = 0; i < 5; i++) {
	        try {
	            return driver.findElement(locator);
	        } catch (Exception e) {
	            driver.findElement(AppiumBy.androidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
	            ));
	        }
	    }

	    
	    // If not found, try backward scroll
	    for (int i = 0; i < 5; i++) {
	        try {
	            return driver.findElement(locator);
	        } catch (Exception e) {
	            driver.findElement(AppiumBy.androidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"
	            ));
	        }
	    }

	    throw new NoSuchElementException("Element not found after scrolling both directions: " + locator);
	}
	 public void switchToWebView() {

	        wait.until(d -> {
	            Set<String> contexts = driver.getContextHandles();
	            for (String context : contexts) {
	                if (context.contains("WEBVIEW")) {
	                    webviewContext = context;
	                    return true;
	                }
	            }
	            return false;
	        });
	        driver.context(webviewContext);
	        System.out.println("Switched to WebView: " + webviewContext);
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	 
	 
	 public void switchToNativeContext() {
		    Set<String> contexts = driver.getContextHandles();
		    if (contexts.contains("NATIVE_APP")) {
		        driver.context("NATIVE_APP");
		        System.out.println("Switched to Native context");
		    } else {
		        System.out.println("Native context not found");
		    }
		}


}
