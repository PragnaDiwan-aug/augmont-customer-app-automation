package com.augmont.tests;

import org.testng.Reporter;

import com.augmont.base.BaseTest;
import com.augmont.objectpages.AddWalletBalance;
import com.augmont.objectpages.CommonMethod;
import com.augmont.objectpages.HomePage;
import com.augmont.objectpages.PurchasePage;
import com.augmont.objectpages.PurchaseSummaryPage;
import com.augmont.objectpages.TransactionPage;

public class TransactionMethodTest extends BaseTest {
	
		TransactionPage transactionPage=new TransactionPage();		
		PurchasePage purchasePage=new PurchasePage();
		AddWalletBalance addWallletBalance=new AddWalletBalance();
		CommonMethod commonMethod=new CommonMethod();
		HomePage homePage=new HomePage();
		public void getTransactionAmountFromHistory(double amount)
		{
		extentTestChild=extentTest.createNode("Verify Paid Amount from Transaction History");
		Reporter.log("Varify Amount from Transaction History",true);
		Reporter.log("-----------------------------------------------",true);
		homePage.clickOnHomeMenu();
		purchasePage.clickOnInvestmentAndEarnMore();
		purchasePage.clickOnGoldInvestment();
		transactionPage.clikOnTransactionHistoryTab();
		transactionPage.getTransactionAmount(amount);
		commonMethod.backButton();
		commonMethod.backButton();
		addWallletBalance.clickOnMoreMenu();
		addWallletBalance.clickOnCancelButton();
		homePage.logOut();
		extentTestChild.info("-----------------------------------------------");
}
		
		public void getTransactionAmountFromHistoryForSilver(double amount)
		{
		extentTestChild=extentTest.createNode("Verify Silver Paid Amount From Transaction History");
		Reporter.log("Verify Silver Paid Amount From Transaction History",true);
		Reporter.log("-----------------------------------------------",true);
		homePage.clickOnHomeMenu();
		purchasePage.clickOnInvestmentAndEarnMore();
		purchasePage.clickOnSilverInvestment();
		transactionPage.clikOnTransactionHistoryTab();
		transactionPage.getTransactionAmountForSilver(amount);
		commonMethod.backButton();
		commonMethod.backButton();
		addWallletBalance.clickOnMoreMenu();
		homePage.logOut();
		extentTestChild.info("-----------------------------------------------");
}

		
		public void validateWalletAmountFromTransactionLog(double walletAmt)
		{
			extentTestChild=extentTest.createNode("Varify Wallet Transaction Amount from Transaction History");
			Reporter.log("Varify Wallet Transaction Amount from Transaction History",true);
			Reporter.log("-----------------------------------------------",true);
			addWallletBalance.clickOnMoreMenu();			
			addWallletBalance.clickOnWalletBalance();
			transactionPage.getTransactionAmountForWallet(walletAmt);
			commonMethod.backButton();
			
		}
		
		public void validateWalletAmountForPartialPayment(double tranamt)
		{
			extentTestChild=extentTest.createNode("Varify Wallet Transaction Amount from Transaction History For Partially Payment");
			Reporter.log("Varify Wallet Transaction Amount from Transaction History For Partially Payment",true);
			Reporter.log("-----------------------------------------------",true);
			homePage.clickOnHomeMenu();
			addWallletBalance.clickOnMoreMenu();
			addWallletBalance.clickOnWalletBalance();
//			transactionPage.getTransactionAmountForWallet(PurchaseSummaryPage.purchaseAmountThreeDigit+PurchaseSummaryPage.threeDigitTaxAmount-PurchaseSummaryPage.paidAmountFromWallet);
			transactionPage.getTransactionAmountForWallet(tranamt);
			transactionPage.getTransactionAmountForWalletPartiallyPayment(PurchaseSummaryPage.paidAmountFromWallet);			
			commonMethod.backButton();
			homePage.clickOnHomeMenu();
		}
		
		public void logOut()
		{
			extentTestChild=extentTest.createNode("logOut");
			Reporter.log("logOut",true);
			Reporter.log("-----------------------------------------------",true);
			homePage.clickOnHomeMenu();
			addWallletBalance.clickOnMoreMenu();
			addWallletBalance.clickOnCancelButton();
			homePage.logOut();
			}

}
