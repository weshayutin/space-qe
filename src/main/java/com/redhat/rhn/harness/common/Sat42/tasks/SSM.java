package com.redhat.rhn.harness.common.Sat42.tasks;

import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

public class SSM extends SeleniumSetup { // implements ISSM{
	
	protected RhnHelper rh = new RhnHelper();

	public static String XPATH_PKG_INSTALL = "xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/form/table";
	
	public void SSM_TagSystems(String TagName){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Provisioning();
		page_SSM.clickLink_TopBar_TagSystem();
		page_SSM.setTxt_TagName(TagName);
		page_SSM.clickButton_TagCurrentSnapshots();

	}
	
	public void SSM_ChangeBaseChannel(String channel){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Channels();
		page_SSM.clickLink_BaseChannels();
		page_SSM.select_SSM_BaseChannel_1(channel);
		page_SSM.select_SSM_BaseChannel_2(channel);
		page_SSM.clickButton_ConfirmSubscriptions();
		page_SSM.clickButton_AlterSubscriptions();
		page_SSM.open();
		page_SSM.clickLink_TopBar_Channels();
		assertTrue(rh.isTextPresent(channel, true));
	}
	
	public void SSM_InstallPackage(String Channel,String Package){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Packages();
		page_SSM.clickLink_Install();
		page_RhnCommon.clickLink_GeneralLink(Channel);
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_SSM.clickButton_InstallPackages();
		page_SSM.clickButton_ScheduleUpdates();
		assertTrue(rh.isTextPresent("Package installations scheduled."));
		
	}
	
	public void SSM_InstallPackageWithArch(String Channel,String Package,String arch){
		throw new SeleniumException("not implemented in this version");
	}
	
	public void SSM_RemovePackages(String Package){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Packages();
		page_SSM.clickLink_Remove();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		assertTrue(rh.isTextPresent(Package));
		page_RhnCommon.clickButton_SelectAll();
		page_SSM.clickButton_RemovePackages();
		page_SSM.clickButton_ScheduleRemovals();
			
	}
	
	public void SSM_RemovePackagesWithArch(String Package,String arch){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Packages();
		page_SSM.clickLink_Remove();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		assertTrue(rh.isTextPresent(Package));
		rh.selectItemInRow(XPATH_PKG_INSTALL, arch);
		page_SSM.clickButton_RemovePackages();
		page_SSM.clickButton_ScheduleRemovals();
	}
	
	public void SSM_TestForPackages(String Package, boolean installed){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Packages();
		page_SSM.clickLink_Remove();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		if(installed)
			assertTrue(rh.isTextPresent(Package));
		else
			assertTrue(rh.isTextPresent("No packages for removal."));		
	}
	
	public void SSM_VerifyPackages(String Package){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Packages();
		page_SSM.clickLink_Verify();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.clickButton_SelectAll();
		page_SSM.clickButton_VerifyPackage();
		page_SSM.clickButton_ScheduleVerifications();
		assertTrue(rh.isTextPresent("Package verifications scheduled."));
		
	}
	

}
