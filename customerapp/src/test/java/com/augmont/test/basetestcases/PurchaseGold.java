package com.augmont.test.basetestcases;


import java.io.IOException;

import org.testng.annotations.Test;

import com.augmont.api.GoldPurchaseAPI;
import com.augmont.base.BaseTest;
import com.augmont.objectpages.AddWalletBalance;
import com.augmont.objectpages.PaymentPage;
import com.augmont.objectpages.PurchasePage;
import com.augmont.objectpages.PurchaseSummaryPage;
import com.augmont.tests.AddWalletBalanceTest;
import com.augmont.tests.LoginTest;
import com.augmont.tests.PaymentMethodTest;
import com.augmont.tests.PurchaseMethodTest;
import com.augmont.tests.TransactionMethodTest;




public class PurchaseGold extends BaseTest{
	LoginTest loginMethod=new LoginTest();
	PurchaseMethodTest purchaseMethod=new PurchaseMethodTest();
	PaymentMethodTest paymentMethod=new PaymentMethodTest();
	TransactionMethodTest transactionMethod=new TransactionMethodTest();
	AddWalletBalanceTest addWalletBalanceMethod=new AddWalletBalanceTest();
	GoldPurchaseAPI goldPurchaseAPI=new GoldPurchaseAPI();
	PurchaseSummaryPage purchaseSummaryPage=new PurchaseSummaryPage();	


	
//	@Test(enabled = false)
//	public void createGoldSip_TC001()
//	{	
//		extentTest=extentReports.createTest("Purchase gold with Weekly SIP Create, verify successful payment response, and verify order details");
//		extentTest.info("purchaseGoldAndVarifyDetail_TC001");
//		extentTest.assignCategory("Gold Purchase - Transaction Verification");
//		loginMethod.login();
//		purchaseMethod.createSipGoldWeekly(getExcelData.getNumaricDataInt("PurchaseData",3,2));
//		purchaseMethod.verifySipSummary();
//		paymentMethod.paymentViaNetBankingWithEmandate();
//		
//	}
	
	@Test()
	public void buyGold_ValidateOrder_TC001() throws IOException
	{	
		extentTest=extentReports.createTest("Add Funds to Wallet,Buy Gold (One-Time), Pay Fully via Wallet, and Validate Order Details");
		extentTest.info("buyGold_ValidateOrder_TC001");
		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
		loginMethod.login();
		addWalletBalanceMethod.AddWalletBalanceUsingWallet(getExcelData.getNumaricDataInt("PurchaseData",2,6));
		transactionMethod.validateWalletAmountFromTransactionLog(getExcelData.getNumaricDataInt("PurchaseData",2,6));
		purchaseMethod.buyGoldOneTime();
		purchaseMethod.verifyPurchaseSummaryForWallet(PaymentPage.congratulationMsg,PaymentPage.BuyGoldmessage);
		transactionMethod.getTransactionAmountFromHistory(getExcelData.getNumaricDataInt("PurchaseData",2,3));
	}

	@Test
	public void buyGold_ValidateOrder_TC002() throws IOException
	{	
		extentTest=extentReports.createTest("Buy Gold (One-Time) With Manually Enter in Grams, Pay Partially Using Wallet and Card, Then Validate Order Details");
		extentTest.info("buyGold_ValidateOrder_TC002");
		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
		//goldPurchaseAPI.getGoldRates();
		loginMethod.login();
		addWalletBalanceMethod.getWalletBalanceTest();
		purchaseMethod.buyGoldOneTimeWithGrams();
		purchaseMethod.varifypurchaseSummaryForGoldGramOption(PurchasePage.gramToBuy,AddWalletBalance.intialWalletBal);
		paymentMethod.paymentMethodUsingCard(PurchaseSummaryPage.netPayAmount);
		transactionMethod.validateWalletAmountForPartialPayment(PurchaseSummaryPage.totalPurAmount-AddWalletBalance.intialWalletBal);
		transactionMethod.getTransactionAmountFromHistory(PurchaseSummaryPage.totalPurAmount);
	}
	
	@Test
	public void buyGold_ValidateOrder_TC003() throws IOException 
	{	
		extentTest=extentReports.createTest("Buy Gold (One-Time) with Manually Entered Amount Using Partial Payment via Wallet and Net Banking");
		extentTest.info("buyGold_ValidateOrder_TC003");
		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
		loginMethod.login();
		addWalletBalanceMethod.AddWalletBalanceUsingWallet(getExcelData.getNumaricDataInt("PurchaseData",3,6));
		transactionMethod.validateWalletAmountFromTransactionLog(getExcelData.getNumaricDataInt("PurchaseData",3,6));
		purchaseMethod.buyGoldOneTimeWithManuallyEnteredAmount();
		purchaseMethod.varifypurchaseSummaryForManuallyEnteredAmountOption(getExcelData.getNumaricDataInt("PurchaseData",3,6));		
		paymentMethod.partialPaymentMethodUsingNetBanking(PaymentPage.congratulationMsg,PaymentPage.BuyGoldmessage,purchaseSummaryPage.calculateAndPrintNetPayable());		
		transactionMethod.validateWalletAmountForPartialPayment(PurchaseSummaryPage.totalPurAmount-PurchaseSummaryPage.paidAmountFromWallet);
		transactionMethod.getTransactionAmountFromHistory(getExcelData.getNumaricDataInt("PurchaseData",3,3));

	}
	
	@Test
	public void buyGold_ValidateOrder_TC004() throws IOException
	{	
		extentTest=extentReports.createTest("Buy Gold (One-Time) in Grams with Default Option, Pay Partially Using UPI and Wallet, Then Validate Order Details");
		extentTest.info("buyGold_ValidateOrder_TC004");
		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
	//	goldPurchaseAPI.getGoldRates();
		loginMethod.login();
		addWalletBalanceMethod.AddWalletBalanceUsingWallet(getExcelData.getNumaricDataInt("PurchaseData",3,6));
		transactionMethod.validateWalletAmountFromTransactionLog(getExcelData.getNumaricDataInt("PurchaseData",3,6));
		purchaseMethod.buyGoldOneTimeWithGramsDefaultOption();
		purchaseMethod.varifypurchaseSummaryForGoldGramOption(1,getExcelData.getNumaricDataInt("PurchaseData",3,6));
		paymentMethod.paymentMethodUsingUPI(purchaseSummaryPage.calculateAndPrintNetPayableRoundingOff());
		transactionMethod.validateWalletAmountForPartialPayment(PurchaseSummaryPage.purchaseAmountThreeDigit+PurchaseSummaryPage.threeDigitTaxAmount-PurchaseSummaryPage.paidAmountFromWallet);
		transactionMethod.getTransactionAmountFromHistory(PurchaseSummaryPage.purchaseAmountThreeDigit+PurchaseSummaryPage.threeDigitTaxAmount);
	}


	@Test
	public void buyGold_ValidateOrder_TC005() throws IOException 
	{	
		extentTest=extentReports.createTest("Buy Gold (One-Time) with Default Amount, pay using Card, then validate order details");
		extentTest.info("buyGold_ValidateOrder_TC005");
		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
		loginMethod.login();	
//		TokenReader tokenReader=new TokenReader();
//		tokenReader.tokenReadder();
//		goldPurchaseAPI.getGoldRates();
		addWalletBalanceMethod.verifyWalletAmountIsZero();
		purchaseMethod.buyGoldOneTime();
		purchaseMethod.verifyPurchaseSummaryForCard();
		paymentMethod.paymentMethodUsingCard(PurchaseSummaryPage.totalPurAmount-AddWalletBalance.intialWalletBal);
		transactionMethod.getTransactionAmountFromHistory(PurchaseSummaryPage.totalPurAmount);
	}

	@Test
	public void buyGold_ValidateOrder_TC006()
	{	
		extentTest=extentReports.createTest("Buy Gold (One-Time) in Grams with Default Option, Payment Done using Netbanking, Then Validate Order Details");
		extentTest.info("buyGold_ValidateOrder_TC006");
		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
		loginMethod.login();
		addWalletBalanceMethod.verifyWalletAmountIsZero();
		purchaseMethod.buyGoldOneTimeWithGramsDefaultOption();
		purchaseMethod.varifypurchaseSummaryForGoldGramOption(1,AddWalletBalance.intialWalletBal);
		paymentMethod.partialPaymentMethodUsingNetBanking(PaymentPage.congratulationMsg,PaymentPage.BuyGoldmessage,purchaseSummaryPage.calculateAndPrintNetPayableRoundingOff());
		transactionMethod.getTransactionAmountFromHistory(PurchaseSummaryPage.purchaseAmountThreeDigit+PurchaseSummaryPage.threeDigitTaxAmount);
	}


	@Test
	public void buyGold_ValidateOrder_TC007() throws IOException 
	{	
		extentTest=extentReports.createTest("Buy Gold (One-Time) with Default Amount purchase with netBanking, And Verify payment failure Response");
		extentTest.info("buyGold_ValidateOrder_TC007");
		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
		loginMethod.login();	
//		TokenReader tokenReader=new TokenReader();
//		tokenReader.tokenReadder();
//		goldPurchaseAPI.getGoldRates();
		addWalletBalanceMethod.verifyWalletAmountIsZero();
		purchaseMethod.buyGoldOneTime();
		purchaseMethod.verifyPurchaseSummaryForCard();
		paymentMethod.verifyPaymentUsingNetBankingFailureResponse(PurchaseSummaryPage.totalPurAmount);
	}

	
	@Test()//pending for razorpay
	public void buyGold_ValidateOrder_TC008() throws IOException
	{	
		extentTest=extentReports.createTest("Buy Gold (One-Time) in Amount with Manually Enter Value, Payment Done using UPI, Then Validate Order Details");
		extentTest.info("buyGold_ValidateOrder_TC008");
		extentTest.info("Note:Fail for Razor Pay Payment Gateway because YesHub App not working");

		extentTest.assignCategory("Gold Purchase(One-Time) - Transaction Verification");
		loginMethod.login();
		addWalletBalanceMethod.verifyWalletAmountIsZero();
		purchaseMethod.buyGoldOneTime();
		purchaseMethod.varifypurchaseSummaryForManuallyEnteredAmountOption(0.0);
		paymentMethod.paymentMethodUsingUPI(purchaseSummaryPage.calculateAndPrintNetPayable());
		transactionMethod.getTransactionAmountFromHistory(PurchaseSummaryPage.totalPurAmount);
	}

}
