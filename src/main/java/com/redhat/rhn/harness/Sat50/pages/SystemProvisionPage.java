package com.redhat.rhn.harness.Sat50.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

public class SystemProvisionPage extends com.redhat.rhn.harness.Sat42.pages.SystemProvisionPage {

	RhnHelper rh = new RhnHelper();

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }
	
	public void check_Monitoring(){
		 rh.checkRadioButton("xpath=//input[@type='checkbox' and @name='monitoring_entitled']", true);
		 try{
			rh.checkRadioButton("name=monitoring_entitled",true);
		 }
		 catch (SeleniumException se){
			 ///html/body/div/div[4]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/input[2]
			 log.info(se.getMessage());
			 rh.selectItem("Monitoring","xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/input");
		 }
	}
}
