package com.redhat.rhn.harness.common.Sat52.tasks;

import org.testng.Assert;


public class ErrataManagement extends com.redhat.rhn.harness.common.Sat51.tasks.ErrataManagement {

	public void publishErrata(String errata, String channel, String packagename){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_UnpublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Packages();
		page_Errata.click_AddPackages();
		//ep.selectErrataViewChannel(channel);
		page_RhnCommon.setTxt_FilterBy(packagename);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_Errata.clickButton_AddPackages();
		page_RhnCommon.clickButton_Confirm();
		Assert.assertTrue(rh.isTextPresent("added to errata"));
		page_Errata.click_Details();
		page_Errata.clickButton_PublishNewErratum();
		page_RhnCommon.clickButton_SelectAll();
		page_Errata.clickButton_PublishNewErratum();
		while(rh.isTextPresent("does not have the packages at all")){
			page_RhnCommon.clickButton_SelectAll();
			page_RhnCommon.clickButton_Continue();
		}
		// the text is not displayed for Sat52
//		Assert.assertTrue(rh.isTextPresent("has been successfully assigned"));
	}
}
