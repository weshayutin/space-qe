package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class ErrataPage extends com.redhat.rhn.harness.Sat50.pages.ErrataPage{

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }

	RhnHelper rh = new RhnHelper();
	
	 public void clickButton_SelectAllErrata(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Select All']",true);
	    }

	
}
