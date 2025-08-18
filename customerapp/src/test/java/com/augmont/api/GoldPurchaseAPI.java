package com.augmont.api;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.augmont.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GoldPurchaseAPI extends BaseTest {

	
	public static String goldBlockID;
	public static String TempOrderId;
	
    public void Base_URI() {
        RestAssured.baseURI = BaseURI;
        Reporter.log("Base URI is : "+BaseURI,true);
     }
     

	
    public void getStatus(String requestUri,String value) {

      //  extentTestChild = extentTest.createNode("Get Status Order ");
        extentTestChild.info("Get Status Order ");

            // Step 1: Prepare request
            RequestSpecification httpRequest = RestAssured.given();

		    httpRequest.header("Accept", "application/json");
	        httpRequest.queryParam("status", value); // ✅ Add query parameter here

		    Response response = httpRequest.request(Method.GET, requestUri);
		    int statusCode = response.getStatusCode();

		    extentTestChild.info("Status Code: " + statusCode);
		    extentTestChild.info("Response Body:\n" + response.getBody().prettyPrint());

		    Assert.assertEquals(statusCode, 200, "Expected status code 200");
		    JsonPath jsonPath = response.jsonPath();
		    
		    String status = jsonPath.getString("status");
		    extentTestChild.pass("Order Status: " + status);
		    Reporter.log("Order Status: " + status, true);
    }    
    
    
    public void getTemporaryOrderDetail(String requestUri, String token) {

//        extentTestChild = extentTest.createNode("Get Temporary OrderDetail");
//        extentTestChild.info("Get Temporary OrderDetail");

            // Step 1: Prepare request
            RequestSpecification httpRequest = RestAssured.given();

            // Step 2: Construct JSON body
            JSONObject requestParams = new JSONObject();
            requestParams.put("amount", 500);
            requestParams.put("investmentAmount", 500);
            requestParams.put("metalType", "gold");
            requestParams.put("quantity", 0.0468);
            requestParams.put("depositDate", "2025-08-07T09:58:27.012Z");
            requestParams.put("orderAmount", 500);
            requestParams.put("qtyAmtType", "amt");
            requestParams.put("type", "buy");
            requestParams.put("orderType", "buy");
            requestParams.put("quantityBased", false);
            requestParams.put("transactionType", "oneTime");
            requestParams.put("blockId", goldBlockID);  // ✅ updated blockId
            requestParams.put("lockPrice", "10367.24");
            requestParams.put("totalTax", 14.56);
            requestParams.put("totalAmount", 500);
            requestParams.put("depositAmount", 500);
            requestParams.put("paidFromWallet", 0);
            requestParams.put("paymentType", "upi");
            requestParams.put("modeOfPayment", "upi");
            requestParams.put("paymentGatewayId", 84);

            Reporter.log("Request body: " + requestParams.toJSONString(), true);
            extentTestChild.info("Request body: " + requestParams.toJSONString());

            // Step 3: Set headers
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", "Bearer " + token);
            System.out.println("token is============================================" +token);
            httpRequest.body(requestParams.toJSONString());

            // Step 4: Send POST request
            Response response = httpRequest.request(Method.POST, requestUri);
            int statusCode = response.getStatusCode();
            extentTestChild.info("Status Code: " + statusCode);

            if (statusCode != 200) {
                extentTestChild.fail("API returned non-200 response: " + statusCode);
             //   Assert.fail("API failed with HTTP code: " + statusCode);
            }

            // Step 5: Log response
            String responseBody = response.getBody().prettyPrint();
            extentTestChild.info("Response body: " + responseBody);

            // Step 6: Extract temp order ID
            JsonPath jsonPath = response.jsonPath();
            String tempOrderId = jsonPath.getString("tempOrderDetail.id");
            TempOrderId = tempOrderId;

            extentTestChild.pass("Temp Order ID: " + tempOrderId);
            Reporter.log("Temp Order ID: " + tempOrderId, true);


        Reporter.log("********************* finish line **************************", true);
    }

	  
	  @Test
	  
	  public void getGoldRates() {
		    extentTestChild = extentTest.createNode("Get Gold Rate From API");
		    extentTestChild.info("Get Gold Rate From API");
		    String requestUri = "https://gold-loan-backend-api.gfau.augmont.com/api/digital-gold/rates";
		 //  1. https://gold-loan-backend-api.gfau.augmont.com/api/customer/app/validate-pin-biometric/v2   -get token
		    //after pay now click hit below api
		  // 2 https://gold-loan-backend-api.gfau.augmont.com/api/customer/app/customer-wallet/pay      take temporderid
//3        https://gold-loan-backend-api.gfau.augmont.com/api/digital-gold/buy/status?id=18160      //pass token and temporderid
		 //  click on sucess
		    //hit 
		    //https://gold-loan-backend-api.gfau.augmont.com/api/digital-gold/buy/status?id=18160  
		    
		    extentTestChild.info("Request URI is:"+requestUri);
		    RequestSpecification httpRequest = RestAssured.given();
		    httpRequest.header("Accept", "application/json");
		    Response response = httpRequest.request(Method.GET, requestUri);
		    int statusCode = response.getStatusCode();

		    extentTestChild.info("Status Code: " + statusCode);
		    extentTestChild.info("Response Body:\n" + response.getBody().prettyPrint());

		    Assert.assertEquals(statusCode, 200, "Expected status code 200");
		    JsonPath jsonPath = response.jsonPath();

		    
//		        Double goldRate = response.jsonPath().getDouble("data[0].rate");
//		        System.out.println("Gold Rate: " + goldRate);
//				extentTestChild.info("Gold Rate: " + goldRate);
		

	  if (jsonPath.get("rate.blockId") != null) {
		    String blockId = jsonPath.getString("rate.blockId");
		    extentTestChild.pass("Block ID: " + blockId);
		    Reporter.log("Block ID: " + blockId, true);
		    goldBlockID=blockId;
    
		} else {
		    extentTestChild.fail("Block ID not found in response");
		    Assert.fail("Missing 'rate.blockId' in JSON response");
		}
	  }
}
