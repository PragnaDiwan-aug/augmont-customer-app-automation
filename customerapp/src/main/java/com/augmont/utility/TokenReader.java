package com.augmont.utility;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import com.augmont.base.BaseTest;



public class TokenReader extends BaseTest {
	public static String Token;
	public static String Signature;

    public void tokenReadder()  {
	    extentTestChild = extentTest.createNode("Get Token");

        String path = "C:\\Users\\Kamlesh Diwan\\AppData\\Local\\Google\\AndroidStudio2024.3\\device-explorer\\android-34\\_\\data\\data\\com.augmont.augmont_android_customer_app\\app_flutter\\augmont.bak";
        JSONParser parser = new JSONParser();
        JSONObject json;
		try {
			json = (JSONObject) parser.parse(new FileReader(path));
	        System.out.println("Token: " + json.get("token"));
	        Token=(String) json.get("token");
		    extentTestChild.info("Get Token:"+Token);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public static String generateSignature(String bodyJson, String url) {
        try {
            String dataToSign = "{\"url\":\"" + url.replace("%20", " ") + "\"}";

            // If bodyJson is not empty, merge it before serializing
            if (bodyJson != null && !bodyJson.isEmpty() && !bodyJson.equals("{}")) {
                dataToSign = bodyJson.substring(0, bodyJson.length() - 1) +
                        ",\"url\":\"" + url.replace("%20", " ") + "\"}";
            }

            String secretKey = "3056301006072a8648ce3d020106052b8104000a0342000499c5f442c3264bcdfb093b0bc820e3f0f6546972856ebec2f8ccc03f49abdb47ffcfcaf4f37e0ec53050760e74014767e30a8a3e891f4db8c83fa27627898f15";

            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac.init(secretSpec);

            byte[] hashBytes = mac.doFinal(dataToSign.getBytes("UTF-8"));

            // Convert to hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getSignature (){
        String bodyJson = "{}"; // Or provide body in JSON format
        String url = "/api/sip/sip-data/all-sip?from=1&to=25&customerId=9458";
        String signature = generateSignature(bodyJson, url);
        Signature=signature;
        extentTestChild.info("Generated Signature: " + signature);
        Reporter.log("Generated Signature: " + signature,true);
        // Now you can pass this signature in your API headers in Appium
    }
}
