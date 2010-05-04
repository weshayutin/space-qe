package com.redhat.rhn.harness.common.Sat51.tasks;


import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class ErrataManagement extends com.redhat.rhn.harness.common.Sat50.tasks.ErrataManagement {
	


	
	 
	@Override
	public void addPackageToUnPubErrataAndPublishToChannel(String errata, String channel, String packageName){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_UnpublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Packages();
		log.info("Need to sleep to allow the errata cache to sort itself out");
		rh.sleepForSeconds(30);
		page_Errata.click_AddPackages();
		//ep.selectErrataViewChannel(channel);
		page_RhnCommon.setTxt_FilterBy(packageName);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_Errata.clickButton_AddPackages();
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
		log.info("Need to sleep to allow the errata cache to sort itself out");
		rh.sleepForSeconds(5);
		page_RhnCommon.clickButton_Confirm();
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
		Assert.assertTrue(rh.isTextPresent("package added to errata"));
		if(rh.isTextPresent(" commit your changes immediately")){
			rh.clickLink("link=commit your changes immediately", true);
		}
		page_Errata.click_Details();
		page_Errata.clickButton_PublishNewErratum();
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
//		page_RhnCommon.check_SelectAll_CheckBox();
//		page_Errata.clickButton_PublishNewErratum();
//		while(rh.isTextPresent("does not have the packages at all")){
//			page_RhnCommon.check_SelectAll_CheckBox();
//			page_RhnCommon.clickButton_Continue();
//		}
		
		sel.check(page_RhnCommon.checkboxInTableRowContainingText(channel));
		page_Errata.clickButton_PublishNewErratum();
		if (rh.isTextPresent("does not have the packages at all")){
			page_RhnCommon.check_SelectAll_CheckBox();
			page_RhnCommon.clickButton_Continue();
			Assert.assertTrue(rh.isTextPresent("package(s) were pushed to channel"));  
		}
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
		
		
		
		/*page_Errata.open();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Channels();
		page_RhnCommon.clickButton_SelectAll();
		page_Errata.clickButton_UpdateChannels();
		page_RhnCommon.check_SelectAll_CheckBox();
		page_RhnCommon.clickButton_Continue();*/
		
		
	}

	
	@Override
	public void DeleteAllErrata(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
	//	ep.click_PublishedErrataLink();

	   page_Errata.click_UnpublishedErrataLink();

		if(rh.isTextNotPresent("No Unpublished Errata")){
			page_Errata.clickButton_SelectAllErrata();
			page_Errata.clickButton_DeleteErratum();
			page_Errata.clickButton_ConfirmDelete();
			assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
			}
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		if(rh.isTextNotPresent("No Published Errata")){
			page_Errata.clickButton_SelectAllErrata();
			page_Errata.clickButton_SelectAllErrata();
			page_Errata.clickButton_DeleteErratum();
			page_Errata.clickButton_ConfirmDelete();
			assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
			}
	
	}
	
}
