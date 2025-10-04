package com.augmont.test.basetestcases;

import org.testng.annotations.Test;

import com.augmont.api.GoldPurchaseAPI;
import com.augmont.base.BaseTest;
import com.augmont.objectpages.PurchaseSummaryPage;
import com.augmont.tests.AddWalletBalanceTest;
import com.augmont.tests.LoginTest;
import com.augmont.tests.PaymentMethodTest;
import com.augmont.tests.PurchaseMethodTest;
import com.augmont.tests.TransactionMethodTest;
import com.augmont.utility.TokenReader;

public class PurchaseSilverSip extends BaseTest {
	LoginTest loginMethod=new LoginTest();
	PurchaseMethodTest purchaseMethod=new PurchaseMethodTest();
	PaymentMethodTest paymentMethod=new PaymentMethodTest();
	TransactionMethodTest transactionMethod=new TransactionMethodTest();
	AddWalletBalanceTest addWalletBalanceMethod=new AddWalletBalanceTest();
	GoldPurchaseAPI goldPurchaseAPI=new GoldPurchaseAPI();
	PurchaseSummaryPage purchaseSummaryPage=new PurchaseSummaryPage();	
	TokenReader tokenReader=new TokenReader();
	
	@Test()
	public void SilverSipPurchase_TC_001()
	{	
		extentTest=extentReports.createTest("Validate Weekly Silver SIP creation with Emandate(NetBanking)successful payment response and verify SIP details");
		extentTest.info("SilverSipPurchase_TC_001");
		extentTest.assignCategory("Silver Sip Purchase - Transaction Verification");
		loginMethod.login();
		purchaseMethod.purchaseSipSilver(getExcelData.getNumaricDataInt("PurchaseData",4,2),
				getExcelData.getData("PurchaseData",4,7));
		purchaseMethod.verifySipSummary(getExcelData.getNumaricDataInt("PurchaseData",4,2), "Gold",getExcelData.getData("PurchaseData",4,1),5,0);			
		paymentMethod.paymentViaNetBankingWithEmandate();
		transactionMethod.logOut();
		goldPurchaseAPI.getToken();
		tokenReader.getSignature();
		goldPurchaseAPI.getSipDetails(getExcelData.getNumaricDataInt("PurchaseData",4,2));

	}

	@Test()
	public void SilverSipPurchase_TC_002()
	{	
		extentTest=extentReports.createTest("Validate Weekly Silver SIP creation with Emandate(NetBanking) payment failure and verify SIP details");
		extentTest.info("SilverSipPurchase_TC_002");
		extentTest.assignCategory("Silver Sip Purchase - Transaction Verification");
		loginMethod.login();
		purchaseMethod.purchaseSipSilver(getExcelData.getNumaricDataInt("PurchaseData",4,2),
				getExcelData.getData("PurchaseData",4,7));
		purchaseMethod.verifySipSummary(getExcelData.getNumaricDataInt("PurchaseData",4,2), "Silver",getExcelData.getData("PurchaseData",4,1),5,0);			
		paymentMethod.failureMethodNetBankingWithEMandate();
	}

	
	@Test()
	public void SilverSipPurchase_TC_003()
	{	
		extentTest=extentReports.createTest("Validate Monthly Silver SIP creation with Emandate(NetBanking) successful payment response and verify SIP details");
		extentTest.info("SilverSipPurchase_TC_003");
		extentTest.assignCategory("Silver Sip Purchase - Transaction Verification");
		loginMethod.login();
		purchaseMethod.purchaseSipSilver(getExcelData.getNumaricDataInt("PurchaseData",5,2),
				getExcelData.getData("PurchaseData",5,7));
		purchaseMethod.verifySipSummary(getExcelData.getNumaricDataInt("PurchaseData",5,2), "Gold",getExcelData.getData("PurchaseData",5,1),5,0);			
		paymentMethod.paymentViaNetBankingWithEmandate();
		transactionMethod.logOut();
		goldPurchaseAPI.getToken();
		tokenReader.getSignature();
		goldPurchaseAPI.getSipDetails(getExcelData.getNumaricDataInt("PurchaseData",5,2));
	}

	
	@Test()
	public void SilverSipPurchase_TC_004()
	{	
		extentTest=extentReports.createTest("Validate Monthly Silver SIP creation with Emandate payment failure and verify SIP details");
		extentTest.info("SilverSipPurchase_TC_004");
		extentTest.assignCategory("Silver Sip Purchase - Transaction Verification");
		loginMethod.login();
		purchaseMethod.purchaseSipSilver(getExcelData.getNumaricDataInt("PurchaseData",5,2),
				getExcelData.getData("PurchaseData",5,7));
		purchaseMethod.verifySipSummary(getExcelData.getNumaricDataInt("PurchaseData",5,2), "Gold",getExcelData.getData("PurchaseData",5,1),5,0);			
		paymentMethod.failureMethodNetBankingWithEMandate();
	}

}
