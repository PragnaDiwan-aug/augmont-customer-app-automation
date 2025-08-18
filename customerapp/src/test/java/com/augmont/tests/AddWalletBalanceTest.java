package com.augmont.tests;

import org.testng.Reporter;

import com.augmont.base.BaseTest;
import com.augmont.objectpages.AddWalletBalance;
import com.augmont.objectpages.CommonMethod;
import com.augmont.objectpages.PaymentPage;

public class AddWalletBalanceTest extends BaseTest{
	
	AddWalletBalance addWallletBalance=new AddWalletBalance();
	PaymentPage paymentPage=new PaymentPage();
	CommonMethod commonMethod=new CommonMethod();
	public void AddWalletBalanceUsingWallet(int walletAmount)
	{
	extentTestChild=extentTest.createNode("Add Fund Via wallet -JIO Money ");
	Reporter.log("Add Wallet Balance",true);
	Reporter.log("-----------------------------------------------",true);
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
	paymentPage.clickOnJioMoney();
//	paymentPage.clickOnContinue();
	paymentPage.clickOnContinueAndPay();
	paymentPage.clickOnSucessBtn();
	paymentPage.addWalletBalanceSucessMessage();
	}

	public void getWalletBalanceTest()
	{
	extentTestChild=extentTest.createNode("Get Wallet Balance");
	Reporter.log("Get Wallet Balance",true);
	Reporter.log("-----------------------------------------------",true);
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
	commonMethod.backButton();

	}
}
