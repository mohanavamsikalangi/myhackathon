package com.cognizant.projects.Utilities;

import java.util.Properties;

public class AccessProperties {
	static Properties prop = ReadProperties.readPropertiesFile();

	public static String getBaseUrl() {
		return prop.getProperty("baseUrl");
	}

	public static String getChromeProperty() {
		return prop.getProperty("chrome");
	}

	public static String getFirefoxProperty() {
		return prop.getProperty("firefox");
	}

	public static String getUserInput() {
		return prop.getProperty("userinput");
	}

	public static String SelectstorageType() {
		return prop.getProperty("SelectedstorageType");
	}

	public static String getPopupXpath() {
		return prop.getProperty("xpath_for_popup_to_be_closed");
	}

	public static String getXpathForLivingButtonOnNavBar() {
		return prop.getProperty("xpath_for_selecting_Living_option_on_NavBar");
	}

	public static String getXpathforBookshelvesOption() {
		return prop.getProperty("xpath_for_selecting_BookShelves_option");
	}

	public static String getCssSelectorToSetStorageType() {
		return prop.getProperty("cssSelector_to_set_storage_type_as_open");
	}

	public static String getXpathToSetPrice() {
		return prop.getProperty("xpath_to_set_price");
	}

	public static String StartPriceRange() {
		return prop.getProperty("xpath_For_Setting_PriceRange_start");

	}

	public static String SendersName() {
		return prop.getProperty("SendersName");
	}

	public static String SendersEmail() {
		return prop.getProperty("SendersEmail");
	}

	public static String SendersPhoneNumber() {
		return prop.getProperty("SendersPhoneNumber");
	}

	public static String ReciversName() {
		return prop.getProperty("ReciversName");
	}

	public static String ReciversEmail() {
		return prop.getProperty("ReciversEmail");
	}
}