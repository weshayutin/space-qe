package com.redhat.rhn.harness.Hosted.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SystemSetManagerPage extends com.redhat.rhn.harness.Sat42.pages.SystemSetManagerPage {

	RhnHelper rh = new RhnHelper();
	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }

	public void clickLink_Configuation(){
		 rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/ssm/config/Deploy.do)]",true);
            }



}
