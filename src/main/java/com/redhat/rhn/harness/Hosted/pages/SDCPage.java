package com.redhat.rhn.harness.Hosted.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SDCPage extends com.redhat.rhn.harness.Sat42.pages.SDCPage {

	RhnHelper rh = new RhnHelper();

	public static String XPATH_CFG_MANG_SYSTEM_FILE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table";
	public static String XPATH_LIST_PACKAGES="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[3]";
	public static String XPATH_VIRT_GUEST_LIST="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody";///tr/td[2]";
													///html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[2]


	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }

	
	public void select_Virt_GuestAction_Delete(){
        rh.clickButton("xpath=//input[@value='Delete Selected Guests']",true);
    }

	
	public String getTxt_VirtGuest_Hostname(){
		return (sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[2]/a"));
	}
}
