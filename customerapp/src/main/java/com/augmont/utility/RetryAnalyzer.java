package com.augmont.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	//int retryLimit = 2;
	int retryLimit = 0;
	public boolean retry(ITestResult result) {
		if(!result.getThrowable().toString().contains("AssertionError"))
		if(counter < retryLimit)
		{
			counter++;
			return true;
		}
		return false;
	}
}

