package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SystemSetManagerPage extends com.redhat.rhn.harness.Sat50.pages.SystemSetManagerPage {

	RhnHelper rh = new RhnHelper();
	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }

	public void clickLink_Configuation(){
		 rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/ssm/config/Deploy.do)]",true);
            }

	 public void clickLink_BaseChannels(){
			rh.clickLink("link=Base Channels",true);
		}
	 
	 public void clickButton_ConfirmSubscriptions(){
	        rh.clickButton("xpath=//input[@value='Confirm Subscriptions']",true);
	 }
	 
	 public void clickButton_AlterSubscriptions(){
	        rh.clickButton("xpath=//input[@value='Alter Subscriptions']",true);
	 }
	 
	 public void select_DesiredBaseChannel(String channel){
	    	rh.selectComboBoxItem("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr/td[3]/select","Desired Base Channel", channel, false);
	    	//							 /html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr/td[3]/select
	    	//example Default RH Base Channel
	 }
	 


}
