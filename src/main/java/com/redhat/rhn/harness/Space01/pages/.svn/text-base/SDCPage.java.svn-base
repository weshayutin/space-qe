package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.harness.common.RhnHelper;

public class SDCPage extends com.redhat.rhn.harness.Sat51.pages.SDCPage {

	RhnHelper rh = new RhnHelper();
	

	public void check_softwarePackage(boolean check, String packageName){
        rh.checkRadioButton("xpath=//input[@type='checkbox' and @value='*"+packageName+"*']",check);
    }
	
	public void clickLink_Virtualization_Provisioning(){
        rh.clickLink("xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div[2]/ul/li[2]/a",true);
    }

}
