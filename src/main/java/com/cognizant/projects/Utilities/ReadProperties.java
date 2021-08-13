package com.cognizant.projects.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	public static Properties readPropertiesFile() {
		FileInputStream fis = null;
		try {
			// configuration of the path
			String filePath = System.getProperty("user.dir") + "\\Resources\\application.properties";
			System.out.println(filePath);
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		try {
			prop.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
