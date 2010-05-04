package com.redhat.rhn.harness.Hosted.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;


public class SatelliteSystemsPage extends  com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage {

	RhnHelper rh = new RhnHelper();


	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
	    }

	   public void clickButton_DeleteProfile(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='delete system']", true);
	   }
	   
	   public String getGuestStatus(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[2]");
	   }
}
