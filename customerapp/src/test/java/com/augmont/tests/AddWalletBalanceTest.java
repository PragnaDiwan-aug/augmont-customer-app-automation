package com.augmont.tests;

import java.io.IOException;

import org.testng.Reporter;

import com.augmont.base.BaseTest;
import com.augmont.objectpages.AddWalletBalance;
import com.augmont.objectpages.CommonMethod;
import com.augmont.objectpages.HomePage;
import com.augmont.objectpages.PaymentPage;

public class AddWalletBalanceTest extends BaseTest{
	
	AddWalletBalance addWallletBalance=new AddWalletBalance();
	PaymentPage paymentPage=new PaymentPage();
	CommonMethod commonMethod=new CommonMethod();
	HomePage homePage=new HomePage();
	public void AddWalletBalanceUsingWallet(int walletAmount) throws IOException
	{
	extentTestChild=extentTest.createNode("Add Fund Via wallet");
	Reporter.log("Add Wallet Balance",true);
	Reporter.log("-----------------------------------------------",true);
	homePage.clickOnHomeMenu();
	addWallletBalance.clickOnMoreMenu();	
	addWallletBalance.clickOnCancelButton();
	addWallletBalance.getWalletBalance();
	addWallletBalance.checkWalletCondition(getExcelData.getNumaricDataInt("PurchaseData",2,5));
	addWallletBalance.clickOnWalletBalance();
	addWallletBalance.clickOnAddFund();
	addWallletBalance.addCustomTopUpValue(walletAmount);
	addWallletBalance.clickOnTopUpButton();
	addWallletBalance.clickOnPayOnlineButton();
	addWallletBalance.depositAmount(walletAmount);
	paymentPage.clickOnWalletBtn();
	paymentPage.clickOnPayNowBtn();
	if(PaymentGateway.equalsIgnoreCase("PhonePay"))
	{
		extentTestChild.info("Payment Gateway is:"+PaymentGateway);
	paymentPage.clickOnJioMoney();
//	paymentPage.clickOnContinue();
	paymentPage.clickOnContinueAndPay();
	paymentPage.clickOnSucessBtn();
	paymentPage.addWalletBalanceSucessMessage();
	}
	else if(PaymentGateway.equalsIgnoreCase("RazorPay"))
	{
		extentTestChild.info("Payment Gateway is:"+PaymentGateway);
		paymentPage.waitForDefaultUpiOptionToBeVisible();
		paymentPage.clickContinueToPaymentButton();
		commonMethod.switchToWebView();
		paymentPage.clickRazorpayCardOption();
		paymentPage.enterCardNumberOnRazorpay();
		paymentPage.enterExpiryDateCardRazorPay();
		paymentPage.enterCVVNumberRazorPay();	
		paymentPage.clickOnContinueRazorPay();
		paymentPage.clickOnMaybeLaterButton();
		paymentPage.clickOnContinueAndPayRazorPay();
		paymentPage.enterOtpRazorPay("123456");
		paymentPage.clickOnContinueRazorPayAfterEnterOTP();
		commonMethod.switchToNativeContext();
		paymentPage.addWalletBalanceSucessMessage();
	
	}
	}

	public void getWalletBalanceTest()
	{
	extentTestChild=extentTest.createNode("Get Wallet Balance");
	Reporter.log("Get Wallet Balance",true);
	Reporter.log("-----------------------------------------------",true);
	homePage.clickOnHomeMenu();
	addWallletBalance.clickOnMoreMenu();	
	addWallletBalance.clickOnCancelButton();
	addWallletBalance.getWalletBalance();
	commonMethod.backButton();

	}
	public void verifyWalletAmountIsZero()
	{
	extentTestChild=extentTest.createNode("Verify Wallet Balance");
	Reporter.log("Verify Wallet Balance",true);
	Reporter.log("-----------------------------------------------",true);
	addWallletBalance.clickOnMoreMenu();	
	addWallletBalance.clickOnCancelButton();
	addWallletBalance.getWalletBalance();
	addWallletBalance.checkWalletCondition(0);
	//commonMethod.backButton();

	}
}
