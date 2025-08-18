package com.augmont.utility;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;

import com.augmont.constant.*;

public class ConfigDataProvider {

	Properties properties;
	public  ConfigDataProvider() {
		try {
			File src=new File("./config/"+TestConstants.GLOBAL_PROPERTIES_FILE);
			FileInputStream inStream=new FileInputStream(src);
			if (inStream != null) {
				properties=new Properties();
				properties.load(inStream);
			} 
		}catch(Throwable t) {
			Assert.assertTrue(false, "Error in setup proprties files " + getClass().getName() + ", reason: " + t.getMessage());
		}
	}

	public String getReportLocation() {
		return properties.getProperty(TestConstants.PROP_TEST_REPORT_FOLDER);
	}
}
