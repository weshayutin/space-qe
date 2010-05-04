package com.redhat.rhn.harness.common.Space01.tasks;


import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class ErrataManagement extends com.redhat.rhn.harness.common.Sat51.tasks.ErrataManagement {
	
	

	
	
	public void publishNewErrata(String errataSynopsis, String advisory, String advisoryRelease, 
			String topic, String description, String solution, String product, int errataType) 
	{
		CreateNewErrataCommon(errataSynopsis, errataSynopsis, "1", errataSynopsis, errataSynopsis, 
				errataSynopsis, errataSynopsis, IRhnBase.ERRATA_TYPE_BUGFIX);
		rh.sleepForSeconds(5);
		page_Errata.clickButton_PublishNewErratum();
		//page_Errata.click_UnpublishedErrataLink();
		page_Errata.check_List();
		page_Errata.clickButton_PublishNewErratum();
	}
	
	public void publishNewErrata(String advisory) {
		CreateNewErrataCommon(advisory);
		page_Errata.clickButton_PublishNewErratum();
		page_Errata.check_List();
		page_Errata.clickButton_PublishNewErratum();


	}	
	
	public void CreateNewErrataCommon(String errataSynopsis){
		CreateNewErrataCommon(errataSynopsis, errataSynopsis, "1", errataSynopsis, errataSynopsis, 
				errataSynopsis, errataSynopsis, IRhnBase.ERRATA_TYPE_BUGFIX);
	}

	public void CreateNewErrataCommon(String errataSynopsis, String advisory, String advisoryRelease, 
			String topic, String description, String solution, String product, int errataType){
		
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
	//	ep.click_PublishedErrataLink();
	   page_Errata.click_UnpublishedErrataLink();
	     if(rh.isTextPresent(errataSynopsis)){
	    	 //page_Errata.check_List();
	    	 sel.check(page_RhnCommon.checkboxInTableRowContainingText(errataSynopsis));
	    	 page_Errata.clickButton_DeleteErratum();
	    	 assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
		     page_Errata.clickButton_ConfirmDelete();
		     assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
	   }
	    page_Errata.open();
	    page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		if(rh.isTextPresent(errataSynopsis)){
			//page_Errata.check_List();
			//page_RhnCommon.clickButton_SelectAll();
			sel.check(page_RhnCommon.checkboxInTableRowContainingText(errataSynopsis));
			//DeleteErrata();
			page_Errata.clickButton_DeleteErratum();
			assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
			page_Errata.clickButton_ConfirmDelete();
			assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
		}

		page_Errata.clickCreateNewErratum();
		//Assert.assertTrue(rh.isTextPresent("Errata Management"));
		//Assert.assertTrue(rh.isTextPresent("Create Errata"));
		page_Errata.setNewErrataSynopsis(errataSynopsis);
		page_Errata.setNewErrataAdvisory(advisory);
		page_Errata.setNewErrataAdvisoryRel(advisoryRelease);
		
		switch(errataType) {
    		case IRhnBase.ERRATA_TYPE_SECURITY: {page_Errata.selectNewErrataTypeSecurityAdv(); break;}
			case IRhnBase.ERRATA_TYPE_BUGFIX: {page_Errata.selectNewErrataTypeBugFix(); break;}
			case IRhnBase.ERRATA_TYPE_ENHANCE: {page_Errata.selectNewErrataTypeProdEnhance(); break;}
		}
		
		page_Errata.setNewErrataTopic(topic);
		page_Errata.setNewErrataProduct(product);
		page_Errata.setNewErrataDescription(description);
		page_Errata.setNewErrataSolution(solution);
		page_Errata.clickButton_CreateNewErratum();
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);

	}
	
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
//			page_RhnCommon.clickButton_SelectAll();
			page_RhnCommon.check_SelectAll_CheckBox();
			page_RhnCommon.clickButton_Continue();
		}
		Assert.assertTrue(
				rh.isTextPresent("has been successfully assigned") ||
				rh.isTextPresent("were pushed to channel")); // in case there were new packages pushed to the channel(s)
	}
	
}
