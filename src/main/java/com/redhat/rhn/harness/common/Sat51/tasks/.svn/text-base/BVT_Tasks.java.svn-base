package com.redhat.rhn.harness.common.Sat51.tasks;

import org.testng.Assert;

public class BVT_Tasks extends com.redhat.rhn.harness.common.Sat42.tasks.BVT_Tasks{


	
	

	public boolean createInitialSatAdminUser(){
		boolean result = false;
		page_RhnCommon.JustOpen();
		if(rh.isElementPresent("xpath=//input[@name='firstNames']", true)){
			page_Install.setTxt_DesiredLogin(RhnBase.USER);
			page_Install.setTxt_DesiredPassword(RhnBase.PASSWORD);
			page_Install.setTxt_ConfirmPassword(RhnBase.PASSWORD);
			page_Install.select_Prefix("Mr.");
			page_Install.setTxt_FirstName("auto");
			page_Install.setTxt_LastName("mation");
			page_Install.setTxt_Email("whayutin@redhat.com");
			page_Install.clickButton_CreateLogin();
			Assert.assertTrue(rh.isTextPresent("Your RHN"));
			result = true;
		}
		if(rh.isTextPresent("To add entitlements")){
			log.info("It appears that the admin user has already been created");
			result = true;
		}
		else if(!rh.isTextPresent("Red Hat Network")){
			log.info("Build may have failed");
			result = false;
		}

		return result;

	}

}
