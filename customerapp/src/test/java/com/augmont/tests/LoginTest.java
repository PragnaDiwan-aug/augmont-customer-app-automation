package com.augmont.tests;



import org.testng.Reporter;

import com.augmont.base.BaseTest;
import com.augmont.objectpages.HomePage;



public class LoginTest extends BaseTest {
	
	HomePage homePage = new HomePage();  

	public void login()
	{
	extentTestChild=extentTest.createNode("Login Method");
	Reporter.log("Login Method",true);
	Reporter.log("-----------------------------------------------",true);	
	//homePage.clickOnSkipLinkMethod();
	homePage.clickOnNext();
	homePage.clickOnSignInSignUpMethod();
	homePage.clickOnRegisterLink();
	homePage.mobileNumberTextBoxClick();
	homePage.noneOftheAboveLinkClick();
	homePage.insertMobileNumber(getExcelData.getNumaricDataLong("LoginData",0,1));
	homePage.clickOnGenerateOTP();
	homePage.insertOTP(getExcelData.getNumaricDataLong("LoginData",1,1));
	homePage.handleSkipButtonIfPresent();

	}
}