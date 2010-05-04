package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

/**
 * Collection of objects (links,buttons,etc..) from the RHN Satellite Schedule Page
 * @author whayutin
 *
 */
public class SchedulePage extends RhnPage{


	RhnHelper rh = new RhnHelper();

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SCHEDULE_PAGE;
    }

	public void clickLink_InProgressSystems(){
        rh.clickLink("link=In Progress Systems",true);
    }

	public void clickLink_Details(){
        rh.clickLink("Details",true);
    }

	public void clickLink_PendingActions(){
        rh.clickLink("link=Pending Actions",true);
    }

	public void clickLink_PackageInstall(){
        rh.clickLink("link=Package Install",true);
    }

	public void clickLink_DeployConfigFilesToSystem(){
        rh.clickLink("link=Deploy config files to system",true);
    }

	public void clickButton_UnscheduleAction(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Unschedule Action']", true);
	}

}
