package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.thoughtworks.selenium.SeleniumException;

public class ConfigurationPage extends com.redhat.rhn.harness.Sat51.pages.ConfigurationPage {



	public static String XPATH_CFG_MANG_TARGET_SYSTEM="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_HOSTED_TARGET_SYSTEM="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_CENT_CHANNEL_FILE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_CENT_CHANNEL_COMPARE="xpath=//tr/td[2]/form/table[2]/tbody/tr/td[4]/a";
																//	/html/body/div[2]/div/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[4]/a


	public static String XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";


	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_CONFIGURATION_PAGE;
    }
	

	public void clickLink_EnableConfigMangOnSystems(){
		rh.clickLink("link=Enable Configuration Management on Systems", true);
	}
	
	public void clickButton_EnableRHNConfigurationManagement(){
		String sxpath_1 = "xpath=//input[@type='submit' and @value='Enable Spacewalk Configuration Management']";
		String sxpath_2 = "xpath=//input[@type='submit' and @value='Enable RHN Satellite Configuration Management']"; // Satellite 5.3.0
		String sxpath_3 = "xpath=//input[@type='submit' and @value='Enable RHN Configuration Management']"; // Satellite 5.2.0
		if(sel.isElementPresent(sxpath_1))
			rh.clickButton(sxpath_1, true);
		else if(sel.isElementPresent(sxpath_2))
			rh.clickButton(sxpath_2, true);
		else if(sel.isElementPresent(sxpath_3))
			rh.clickButton(sxpath_3, true);
		else
			throw new SeleniumException("Unknown xpath detected. Please find ant fix it.");
	}
	
	 public void clickLink_ViewComparison(String channel, boolean hosted){
			rh.clickLink("link=View Comparison",true);
		/* if(hosted)
			 rh.clickLink_InRow(XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE, channel, 4);
		 else
		 	rh.clickLink_InRow(XPATH_CFG_MANG_CENT_CHANNEL_COMPARE, channel, 4);*/
		}
	 
	 public void clickLink_DeleteChannel(){
		 rh.clickLink("link=delete channel",true);
	    }
	
	 
	 public void setTxt_FileContents(String txt){
		 	sel.uncheck("id=edit_area_toggle_checkbox_contents");
		 	rh.sleepForSeconds(1);
		 	sel.setText("name=contents", txt);
	    }
}
