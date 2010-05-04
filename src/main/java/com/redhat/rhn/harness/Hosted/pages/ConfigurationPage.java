package com.redhat.rhn.harness.Hosted.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class ConfigurationPage extends com.redhat.rhn.harness.Sat42.pages.ConfigurationPage {

	RhnHelper rh = new RhnHelper();

	public static String XPATH_CFG_MANG_TARGET_SYSTEM="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_HOSTED_TARGET_SYSTEM="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_CENT_CHANNEL_FILE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";


	public static String XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";


	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_CONFIGURATION_PAGE;
    }

	
}
