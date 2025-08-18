package com.augmont.tests;

import org.testng.Reporter;

import com.augmont.base.BaseTest;
import com.augmont.objectpages.AddWalletBalance;
import com.augmont.objectpages.HomePage;
import com.augmont.objectpages.PaymentPage;
import com.augmont.objectpages.PurchasePage;
import com.augmont.objectpages.PurchaseSummaryPage;


public class PurchaseMethodTest extends BaseTest {
	
	
	PurchasePage purchasePage=new PurchasePage();
	PurchaseSummaryPage purchaseSummaryPage=new PurchaseSummaryPage();
	PaymentPage paymentPage=new PaymentPage();
	HomePage homePage=new HomePage();
	
	public void createSipGoldWeekly()
	{
	extentTestChild=extentTest.createNode("Create SIP OF gold weekly");
	Reporter.log("Create SIP OF gold weekly",true);
	Reporter.log("-----------------------------------------------",true);
	purchasePage.clickOnInvestmentAndEarnMore();
	purchasePage.clickOnGoldInvestment();
	purchasePage.clickOnStartInvestingGold();
	purchasePage.clickOnSIPRecommend();
	purchasePage.clickOnWeeklyInvestment();
	purchasePage.insertInvestmentAmount();
	purchasePage.clickOnProceed();
	Reporter.log("-----------------------------------------------",true);

	}	
	
	public void getSipSummary()
	{
		extentTestChild=extentTest.createNode("SIP Form");
		Reporter.log("SIP Form",true);
		Reporter.log("-----------------------------------------------",true);
		purchaseSummaryPage.emailIDSIPForm();
		purchaseSummaryPage.insvestmentPurpose();
		purchaseSummaryPage.insertSipStartDate();
		purchaseSummaryPage.clickOnCalendarOkButton();
		purchaseSummaryPage.clikOnProceedtoPay();

		Reporter.log("-----------------------------------------------",true);

}
	
	public void buyGoldOneTime()
	{
		extentTestChild=extentTest.createNode("Buy gold one time with defalut Amount Option");
		Reporter.log("Buy gold one time with defalut Amount Option",true);
		Reporter.log("-----------------------------------------------",true);
		homePage.clickOnHomeMenu();
		purchasePage.clickOnInvestmentAndEarnMore();
		purchasePage.clickOnGoldInvestment();
		purchasePage.clickOnStartInvestingGold();
		purchasePage.clickOnOneTimeBtn();
		purchasePage.clickOnAmountBtn();
		purchasePage.selectInvAmtFromAmountTab(getExcelData.getNumaricDataInt("PurchaseData",2,2));
		purchasePage.clickOnProceed();
		Reporter.log("-----------------------------------------------",true);

	}
	
	public void buyGoldOneTimeWithManuallyEnteredAmount()
	{
		extentTestChild=extentTest.createNode("Buy Gold (One-Time) with Manually Entered Amount");
		Reporter.log("Buy Gold (One-Time) with Manually Entered Amount",true);
		Reporter.log("-----------------------------------------------",true);
		homePage.clickOnHomeMenu();
		purchasePage.clickOnInvestmentAndEarnMore();
		purchasePage.clickOnGoldInvestment();
		purchasePage.clickOnStartInvestingGold();
		purchasePage.clickOnOneTimeBtn();
		purchasePage.clickOnAmountBtn();
		purchasePage.enterGoldAmountManually(getExcelData.getNumaricDataInt("PurchaseData",3,2));
		purchasePage.clickOnProceed();
		Reporter.log("-----------------------------------------------",true);

	}

	
	public void buyGoldOneTimeWithGrams()
	{
		extentTestChild=extentTest.createNode("Purchase Gold One-Time with Grams Mannually Entered Grams");
		Reporter.log("Purchase Gold One-Time with Grams Option",true);
		Reporter.log("-----------------------------------------------",true);
		homePage.clickOnHomeMenu();
		purchasePage.clickOnInvestmentAndEarnMore();
		purchasePage.clickOnGoldInvestment();
		purchasePage.clickOnStartInvestingGold();
		purchasePage.clickOnOneTimeBtn();
		purchasePage.clickOnGramsBtn();
		purchasePage.enterGramValue();
		purchasePage.clickOnProceed();
		Reporter.log("-----------------------------------------------",true);

	}
	
	public void buyGoldOneTimeWithGramsDefaultOption()
	{
		extentTestChild=extentTest.createNode("Purchase Gold One-Time with Grams Default Option");
		Reporter.log("Purchase Gold One-Time with Grams Default Option",true);
		Reporter.log("-----------------------------------------------",true);
		homePage.clickOnHomeMenu();
		purchasePage.clickOnInvestmentAndEarnMore();
		purchasePage.clickOnGoldInvestment();
		purchasePage.clickOnStartInvestingGold();
		purchasePage.clickOnOneTimeBtn();
		purchasePage.clickOnGramsBtn();
		purchasePage.selectInvGoldInGramFromGramTab("1 Grams");
		purchasePage.clickOnProceed();
		Reporter.log("-----------------------------------------------",true);

	}

	
	public void verifyPurchaseSummaryForWallet()
	{
		extentTestChild=extentTest.createNode("Varify Purchase Gold One Time Detail and Validate Sucess Message");
		Reporter.log("Varify Purchase Gold One Time Summary",true);
		Reporter.log("-----------------------------------------------",true);
		purchaseSummaryPage.varifyInvestmentType(getExcelData.getData("PurchaseData",2,1));
		purchaseSummaryPage.verifyTotalAmount(getExcelData.getNumaricDataInt("PurchaseData",2,2));
		purchaseSummaryPage.verifyPaidAmountFromWallet(getExcelData.getNumaricDataInt("PurchaseData",2,2));
		purchaseSummaryPage.verifyNetPayableAmount(getExcelData.getNumaricDataInt("PurchaseData",2,5));
		purchaseSummaryPage.clikOnProceedtoPay();
		paymentPage.getToastMessageoneTimeBuyGold();
		Reporter.log("-----------------------------------------------",true);
	
	}
	
	public void varifypurchaseSummaryForGoldGramOption(double gramtobuy,double walletBal)
	{
		extentTestChild=extentTest.createNode("Verify Gold(Buy one Time) Gram Purchase Details");
		Reporter.log("Verify Gold(Buy one Time) Gram Purchase Details",true);
		Reporter.log("-----------------------------------------------",true);
		purchaseSummaryPage.varifyInvestmentType(getExcelData.getData("PurchaseData",2,1));
		purchaseSummaryPage.getLivePerGramGoldPrice();
		purchaseSummaryPage.calculateGoldPurchaseAmount(gramtobuy);
		purchaseSummaryPage.verifyGoldAmount(PurchaseSummaryPage.purchaseAmountTwoDigit);
		purchaseSummaryPage.calculateGoldTaxAmount(PurchaseSummaryPage.purchaseAmountThreeDigit,3);
		purchaseSummaryPage.verifyGoldTaxAmount(PurchaseSummaryPage.threeDigitTaxAmount);
		purchaseSummaryPage.verifyAmountUsingBigDecimal(PurchaseSummaryPage.purchaseAmountThreeDigit+PurchaseSummaryPage.threeDigitTaxAmount);
		purchaseSummaryPage.verifyPaidAmountFromWallet(walletBal);
		purchaseSummaryPage.calculateAndPrintNetPayable();
		purchaseSummaryPage.calculateAndPrintNetPayableRoundingOff();
		purchaseSummaryPage.verifyNetPayableAmount(purchaseSummaryPage.calculateAndPrintNetPayable());
		purchaseSummaryPage.clikOnProceedtoPay();

		Reporter.log("-----------------------------------------------",true);
	
	}
	
	public void varifypurchaseSummaryForManuallyEnteredAmountOption()
	{
		extentTestChild=extentTest.createNode("Verify Gold (Buy One-Time) – Manually Entered Amount, Purchase Details, and Validate Success Confirmation");
		Reporter.log("Verify Gold (Buy One-Time) – Manually Entered Amount, Purchase Details, and Validate Success Confirmation",true);
		Reporter.log("-----------------------------------------------",true);
		purchaseSummaryPage.varifyInvestmentType(getExcelData.getData("PurchaseData",3,1));
		purchaseSummaryPage.verifyTotalAmount(getExcelData.getNumaricDataInt("PurchaseData",3,2));
		purchaseSummaryPage.verifyPaidAmountFromWallet(getExcelData.getNumaricDataInt("PurchaseData",3,6));
		purchaseSummaryPage.verifyNetPayableAmount(purchaseSummaryPage.calculateAndPrintNetPayable());
		purchaseSummaryPage.clikOnProceedtoPay();		
		Reporter.log("-----------------------------------------------",true);
	
	}

	public void verifyPurchaseSummaryForCard()
	{
		extentTestChild=extentTest.createNode("Verify Purchase Gold One Time Summary");
		Reporter.log("Verify Purchase Gold One Time Summary",true);
		Reporter.log("-----------------------------------------------",true);
		purchaseSummaryPage.varifyInvestmentType(getExcelData.getData("PurchaseData",2,1));
		purchaseSummaryPage.verifyTotalAmount(getExcelData.getNumaricDataInt("PurchaseData",2,2));
		purchaseSummaryPage.verifyPaidAmountFromWallet(AddWalletBalance.intialWalletBal);
		purchaseSummaryPage.verifyNetPayableAmount(PurchaseSummaryPage.totalPurAmount-AddWalletBalance.intialWalletBal);
		purchaseSummaryPage.clikOnProceedtoPay();
		Reporter.log("-----------------------------------------------",true);
	
	}


}
