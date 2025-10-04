package com.augmont.utility;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.augmont.base.BaseTest;
import com.aventstack.extentreports.ExtentTest;



public class TokenReader extends BaseTest {
	public static String Token;
    public void tokenReadder()  {
	    extentTestChild = extentTest.createNode("Get Token");

        String path = "C:\\Users\\Kamlesh Diwan\\AppData\\Local\\Google\\AndroidStudio2024.3\\device-explorer\\android\\_\\data\\data\\com.augmont.augmont_android_customer_app\\app_flutter\\augmont.bak";
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
}
