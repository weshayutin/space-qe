package com.redhat.rhn.harness.common.Sat42.tasks;


import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.*;
import com.redhat.rhn.harness.baseInterface.IChannels;
import com.redhat.rhn.harness.baseInterface.IErrata;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used to create,delete,publish RHN Errata
 * @author whayutin
 *
 */
public class ErrataManagement extends SeleniumSetup { //implements IErrata {
	protected RhnHelper rh = new RhnHelper();
	
	/*public static String autoErrata01 = "autoerrata01";
	public static String autoErrata02 = "autoerrata02";
	public static String errataprep = "autoerrataprep";
	public static String autoErrataChannel01 = "autoerratachannel01";*/
	
	public void DisplayErrataOverviewPage(){
	    page_Errata.click_ErrataLink();
        Assert.assertTrue(rh.isTextPresent("Errata Overview"));
	}


	public void DisplayAllErrataPage(){
		page_Errata.click_ErrataLink();
        page_Errata.click_AllErrataLink();
        Assert.assertTrue(rh.isTextPresent("All Errata"));
	}


	public void DisplayReleventErrataPage(){
		page_Errata.click_ErrataLink();
		page_Errata.click_RelevantLink();
        Assert.assertTrue(rh.isTextPresent("Errata Relevant to Your Systems"));
       
	}
	
	public void DisplayManageErrataPage(){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
        Assert.assertTrue(rh.isTextPresent("Errata Management"));
        Assert.assertTrue(rh.isTextPresent("Published Errata"));

	}
	public void DisplayPublishedErrataPage(){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
        Assert.assertTrue(rh.isTextPresent("Errata Management"));
        Assert.assertTrue(rh.isTextPresent("Published Errata"));

	}
	public void DisplayUnpublishedErrataPage(){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_UnpublishedErrataLink();
        Assert.assertTrue(rh.isTextPresent("Errata Management"));
        //Assert.assertTrue(rh.isTextPresent("Published Errata"));

	}

	protected void CreateNewErrataCommon(String errataSynopsis){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
	
	   page_Errata.click_UnpublishedErrataLink();
	     if(rh.isTextPresent(errataSynopsis)){
	    	 page_Errata.check_List();
	    	 page_Errata.clickButton_DeleteErratum();
		     page_Errata.clickButton_ConfirmDelete();
	   }
		page_Errata.click_PublishedErrataLink();
		if(rh.isTextPresent(errataSynopsis)){
			page_Errata.check_List();
			//DeleteErrata();
			page_Errata.clickButton_DeleteErratum();
			page_Errata.clickButton_ConfirmDelete();
		}

		page_Errata.clickCreateNewErratum();
		//Assert.assertTrue(rh.isTextPresent("Errata Management"));
		//Assert.assertTrue(rh.isTextPresent("Create Errata"));
		page_Errata.setNewErrataSynopsis(errataSynopsis);
		page_Errata.setNewErrataAdvisory(errataSynopsis);
		page_Errata.setNewErrataAdvisoryRel("1");
		page_Errata.selectNewErrataTypeBugFix();
		page_Errata.setNewErrataTopic(errataSynopsis);
		page_Errata.setNewErrataProduct(errataSynopsis);
		page_Errata.setNewErrataDescription(errataSynopsis);
		page_Errata.setNewErrataSolution(errataSynopsis);
		page_Errata.clickButton_CreateNewErratum();
	}

	public void DeleteAllErrata(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
	
		page_Errata.click_UnpublishedErrataLink();

		if(rh.isTextNotPresent("No Unpublished Errata")){
			page_Errata.clickButton_SelectAllErrata();
			page_Errata.clickButton_DeleteErratum();
			page_Errata.clickButton_ConfirmDelete();
		}
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		
		if(rh.isTextNotPresent("No Published Errata")){
			page_Errata.clickButton_SelectAllErrata();
			page_Errata.clickButton_DeleteErratum();
			page_Errata.clickButton_ConfirmDelete();
		}
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
		
	}

	public void CloneErrata(){
		
	//	CloneErrata();

	}


	public void CreateNewErrata(String errataSynopsis){
		CreateNewErrataCommon(errataSynopsis);
		Assert.assertTrue(rh.isTextPresent(errataSynopsis));

	}

	public void PublishNewErrata(String errataSynopsis){
		CreateNewErrataCommon(errataSynopsis);
		page_Errata.clickButton_PublishNewErratum();
		page_Errata.check_List();
		page_Errata.clickButton_PublishNewErratum();
	}

	public void CreateNewErrataErrorCheck1(){
		task_RhnBase.OpenAndLogIn();
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		page_Errata.clickCreateNewErratum();
		//Assert.assertTrue(rh.isTextPresent("Errata Management"));
		//Assert.assertTrue(rh.isTextPresent("Create Errata"));

		page_Errata.clickButton_CreateNewErratum();

		Assert.assertTrue(rh.isTextPresent("Product is required."));
		Assert.assertTrue(rh.isTextPresent("Synopsis is required."));
		Assert.assertTrue(rh.isTextPresent("Advisory is required."));
		Assert.assertTrue(rh.isTextPresent("Topic is required."));
		Assert.assertTrue(rh.isTextPresent("Description is required."));
		Assert.assertTrue(rh.isTextPresent("Solution is required."));

	}

	public void CreateNewErrataErrorCheck2(){
		task_RhnBase.OpenAndLogIn();
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		page_Errata.clickCreateNewErratum();
		page_Errata.setNewErrataSynopsis("Error Check2");
		page_Errata.clickButton_CreateNewErratum();

		Assert.assertTrue(rh.isTextPresent("Product is required."));
		//Assert.assertTrue(rh.isTextPresent("Synopsis is required."));
		Assert.assertTrue(rh.isTextPresent("Advisory is required."));
		Assert.assertTrue(rh.isTextPresent("Topic is required."));
		Assert.assertTrue(rh.isTextPresent("Description is required."));
		Assert.assertTrue(rh.isTextPresent("Solution is required."));

	}

	public void CreateNewErrataErrorCheck3(){
		task_RhnBase.OpenAndLogIn();
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		page_Errata.clickCreateNewErratum();
		page_Errata.setNewErrataAdvisory("Error Check2");
		page_Errata.clickButton_CreateNewErratum();

		Assert.assertTrue(rh.isTextPresent("Product is required."));
		Assert.assertTrue(rh.isTextPresent("Synopsis is required."));
	    //Assert.assertTrue(rh.isTextPresent("Advisory is required."));
		Assert.assertTrue(rh.isTextPresent("Topic is required."));
		Assert.assertTrue(rh.isTextPresent("Description is required."));
		Assert.assertTrue(rh.isTextPresent("Solution is required."));

	}

	public void CreateNewErrataErrorCheck4(){
		task_RhnBase.OpenAndLogIn();
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		page_Errata.clickCreateNewErratum();
		page_Errata.setNewErrataProduct("Error Check2");
		page_Errata.clickButton_CreateNewErratum();

		//Assert.assertTrue(rh.isTextPresent("Product is required."));
		Assert.assertTrue(rh.isTextPresent("Synopsis is required."));
		Assert.assertTrue(rh.isTextPresent("Advisory is required."));
		Assert.assertTrue(rh.isTextPresent("Topic is required."));
		Assert.assertTrue(rh.isTextPresent("Description is required."));
		Assert.assertTrue(rh.isTextPresent("Solution is required."));

	}

	public void CreateNewErrataErrorCheck5(){
		task_RhnBase.OpenAndLogIn();
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		page_Errata.clickCreateNewErratum();
		page_Errata.setNewErrataTopic("Error Check2");
		page_Errata.clickButton_CreateNewErratum();

		Assert.assertTrue(rh.isTextPresent("Product is required."));
		Assert.assertTrue(rh.isTextPresent("Synopsis is required."));
		Assert.assertTrue(rh.isTextPresent("Advisory is required."));
		//Assert.assertTrue(rh.isTextPresent("Topic is required."));
		Assert.assertTrue(rh.isTextPresent("Description is required."));
		Assert.assertTrue(rh.isTextPresent("Solution is required."));

	}

	public void CreateNewErrataErrorCheck6(){
		task_RhnBase.OpenAndLogIn();
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		page_Errata.clickCreateNewErratum();
		page_Errata.setNewErrataDescription("Error Check2");
		page_Errata.clickButton_CreateNewErratum();

		Assert.assertTrue(rh.isTextPresent("Product is required."));
		Assert.assertTrue(rh.isTextPresent("Synopsis is required."));
		Assert.assertTrue(rh.isTextPresent("Advisory is required."));
		Assert.assertTrue(rh.isTextPresent("Topic is required."));
		//Assert.assertTrue(rh.isTextPresent("Description is required."));
		Assert.assertTrue(rh.isTextPresent("Solution is required."));

	}
	public void CreateNewErrataErrorCheck7(){
		task_RhnBase.OpenAndLogIn();
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		page_Errata.clickCreateNewErratum();
		page_Errata.setNewErrataSolution("Error Check2");
		page_Errata.clickButton_CreateNewErratum();

		Assert.assertTrue(rh.isTextPresent("Product is required."));
		Assert.assertTrue(rh.isTextPresent("Synopsis is required."));
		Assert.assertTrue(rh.isTextPresent("Advisory is required."));
		Assert.assertTrue(rh.isTextPresent("Topic is required."));
		Assert.assertTrue(rh.isTextPresent("Description is required."));
		//Assert.assertTrue(rh.isTextPresent("Solution is required."));

	}

	public void addPackageToUnPubErrataAndPublishToChannel(String errata, String channel, String packageName){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_UnpublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Packages();
		page_Errata.click_AddPackages();
		//ep.selectErrataViewChannel(channel);
		page_RhnCommon.setTxt_FilterBy(packageName);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_Errata.clickButton_AddPackages();
		page_RhnCommon.clickButton_Confirm();
		Assert.assertTrue(rh.isTextPresent("added to errata"));
		page_Errata.click_Details();
		page_Errata.clickButton_PublishNewErratum();
		//page_RhnCommon.clickButton_SelectAll();
		sel.check(page_RhnCommon.checkboxInTableRowContainingText(channel));
		page_Errata.clickButton_PublishNewErratum();
		while(rh.isTextPresent("does not have the packages at all")){
			page_RhnCommon.clickButton_SelectAll();
			page_RhnCommon.clickButton_Continue();
		}
		Assert.assertTrue(rh.isTextPresent("has been successfully assigned"));
	}
	
	
	public void addPackageToPublishedErrata(String errata, String channel, String packageName){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Packages();
		page_Errata.click_AddPackages();
		//ep.selectErrataViewChannel(channel);
		page_RhnCommon.setTxt_FilterBy(packageName);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		log.info("sleep to allow errata cache to sort itself out");
		rh.sleepForSeconds(10);
		page_Errata.clickButton_AddPackages();
		rh.sleepForSeconds(3);
		page_RhnCommon.clickButton_Confirm();
		Assert.assertTrue(rh.isTextPresent("added to errata"));
		page_RhnCommon.check_SelectAll_CheckBox();
		page_RhnCommon.clickButton_Continue();
		Assert.assertTrue(rh.isTextPresent("were pushed to channel"));
		
		
		page_Errata.open();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Channels();
		page_RhnCommon.clickButton_SelectAll();
		page_Errata.clickButton_UpdateChannels();
		page_RhnCommon.check_SelectAll_CheckBox();
		page_RhnCommon.clickButton_Continue();
		
		
	}
	
	
	public void deletePackageFromPublishedErrata(String errata, String channel, String packageName){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Packages();
		page_Errata.click_DeletePackages();
		//ep.selectErrataViewChannel(channel);
		page_RhnCommon.setTxt_FilterBy(packageName);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_Errata.clickButton_DeletePackages();
		page_RhnCommon.clickButton_Confirm();
		Assert.assertTrue(rh.isTextPresent("removed from errata"));
	}
	
	
	public void addChannelToPublishedErrata(String errata, String channel){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Channels();
		//page_RhnCommon.check_SelectAll_CheckBox();
		sel.check(page_RhnCommon.checkboxInTableRowContainingText(channel));
		page_Errata.clickButton_UpdateChannels();
		Assert.assertTrue(rh.isTextPresent("successfully assigned"));
	}
	
	
	public void deleteChannelFromPublishedErrata(String errata, String channel){
		page_Errata.click_ErrataLink();
		page_Errata.click_ManageErrataLink();
		page_Errata.click_PublishedErrataLink();
		rh.clickLink("link="+errata, true);
		page_Errata.click_Channels();
		//page_RhnCommon.uncheck_FirstItemInList();
		sel.check(page_RhnCommon.checkboxInTableRowContainingText(channel));
		page_Errata.clickButton_UpdateChannels();
		Assert.assertTrue(rh.isTextPresent("successfully assigned"));
	}
	
	public void deletePackageFromChannel(String packages, String channel){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_listRemovePackages();
		page_RhnCommon.check_FirstItemInList();
		page_Channels.clickButton_ConfirmRemoval();
		page_Channels.clickButton_Remove();
		Assert.assertTrue(rh.isTextPresent("removed from channel"));
		//TO DO: Check package counts
	}
	
	public void addPackageToChannel(String packages, String channel){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_addPackages();
		//select channel
		page_Channels.clickButton_ConfirmAddition();
		page_Channels.clickButton_ConfirmAddPackages();
		Assert.assertTrue(rh.isTextPresent("added to channel"));
		//TO DO: Check package counts
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
			page_RhnCommon.clickButton_SelectAll();
			page_RhnCommon.clickButton_Continue();
		}
		Assert.assertTrue(rh.isTextPresent("has been successfully assigned"));
	}

	
	public void publishNewErrata(String errataSynopsis, String advisory, String advisoryRelease, 
			String topic, String description, String solution, String product, int errataType) 
	{
		throw new SeleniumException("wrong version of method");
	}

}
