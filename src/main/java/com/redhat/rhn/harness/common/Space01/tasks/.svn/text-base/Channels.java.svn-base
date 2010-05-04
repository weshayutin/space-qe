package com.redhat.rhn.harness.common.Space01.tasks;

import java.io.IOException;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.IChannels;
import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class Channels extends com.redhat.rhn.harness.common.Sat51.tasks.Channels implements IChannels{
	
	protected String XPATH_FILE_SYSTEM_PATH = "//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[3]/table/tbody/tr[7]/td";
	protected String XPATH_MD5SUM = "//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[3]/table/tbody/tr[6]/td";
	protected String XPATH_PKG_PROVIDER = "//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[3]/table/tbody/tr[5]/td";
	
	public void deleteAllCustomChannels() {
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		while(rh.isElementPresent("//tr/td[2]/form/table/tbody/tr/td/a", true)){
			rh.clickLink("//tr/td[2]/form/table/tbody/tr/td/a", true);
			page_Channels.click_DeleteSoftwareChannel();
			page_Channels.clickButton_DeleteChannel();
			if(rh.isTextPresent("This channel has child channels associated with it. You must delete those channels first before deleting the parent.")){
				page_Channels.open();
				page_Channels.click_ManageSoftwareChannels();
				rh.clickLink("//tr/td[2]/form/table/tbody/tr[2]/td/a", true);
				page_Channels.click_DeleteSoftwareChannel();
				page_Channels.CheckRadio_UnsubscribeSystems(true);
				page_Channels.clickButton_DeleteChannel();
			}
			if(rh.isTextPresent("You cannot delete")){
				rh.clickLink("link=here", "here", true);
				page_RhnCommon.clickButton_SSM_Clear();
				page_RhnCommon.clickButton_SelectAll();
				page_RhnCommon.clickButton_SSM_Manage();
				page_SSM.clickLink_ChannelMemberships();
				page_SSM.clickLink_BaseChannels();
				page_SSM.select_DesiredBaseChannel("System Default Base Channel");
				page_SSM.clickButton_ConfirmSubscriptions();
				page_SSM.clickButton_AlterSubscriptions();

				page_RhnCommon.clickChannels();
				page_Channels.click_ManageSoftwareChannels();
				rh.clickLink("//tr/td[2]/form/table/tbody/tr/td/a", true);
				page_Channels.click_DeleteSoftwareChannel();
				page_Channels.clickButton_DeleteChannel();
				}
			if(rh.isTextPresent("There are currently systems subscribed to this channel.")){
				page_Channels.CheckRadio_UnsubscribeSystems(true);
				page_Channels.clickButton_DeleteChannel();
				if(rh.isTextPresent("This channel has child channels associated with it. You must delete those channels first before deleting the parent.")){
					page_Channels.open();
					page_Channels.click_ManageSoftwareChannels();
					rh.clickLink("//tr/td[2]/form/table/tbody/tr[2]/td/a", true);
					page_Channels.click_DeleteSoftwareChannel();
					page_Channels.CheckRadio_UnsubscribeSystems(true);
					page_Channels.clickButton_DeleteChannel();
				}
				page_Channels.open();
				page_Channels.click_ManageSoftwareChannels();
				/*if(rh.isTextPresent("Package Management")){
					page_RhnCommon.clickButton_SelectAll();
					page_Channels.clickButton_ConfirmDeletion();
					page_Channels.clickButton_DeleteChannel();
				}*/
			}
			page_Channels.open();
			page_Channels.click_ManageSoftwareChannels();
		}
	}
	
		
	
	public void deleteCustomChannel(String clonedChannelName){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		if(rh.isTextPresent(clonedChannelName)){
			rh.clickLink("link="+clonedChannelName, true);
			page_Channels.click_DeleteSoftwareChannel();
			page_Channels.clickButton_DeleteChannel();
			if(rh.isTextPresent("You cannot delete")){
				rh.clickLink("link=here", "here", true);
				page_RhnCommon.clickButton_SSM_Clear();
				page_RhnCommon.clickButton_SelectAll();
				page_RhnCommon.clickButton_SSM_Manage();
				page_SSM.clickLink_ChannelMemberships();
				page_SSM.clickLink_BaseChannels();
				page_SSM.select_DesiredBaseChannel("System Default Base Channel");
				page_SSM.clickButton_ConfirmSubscriptions();
				page_SSM.clickButton_AlterSubscriptions();

				page_Channels.open();
				page_Channels.click_ManageSoftwareChannels();
				rh.clickLink("link="+clonedChannelName, true);
				page_Channels.click_DeleteSoftwareChannel();
				page_Channels.clickButton_DeleteChannel();
				}
		}
	}
	
	
	public void addSubscribersToChannel(String channel, String user){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		if(rh.isTextPresent(channel)){
			rh.clickLink("link="+channel, true);
		}
		if(rh.isElementPresent(page_Channels.link_Subscribers(), true));
			page_Channels.click_Subscribers();
		}
	
	public void verifyChannelExists(String channel, boolean exists){
		page_Channels.open();
		Assert.assertTrue(rh.isTextPresent("Full Software Channel List"));
		page_Channels.click_AllChannels();
		page_RhnCommon.setTxt_FilterBy(channel);
		page_RhnCommon.clickButton_Filter_Go();
		if(exists){
		Assert.assertTrue(rh.isTextPresent(channel));
		rh.clickLink("link="+channel, true);
		Assert.assertTrue(rh.isTextPresent(channel));
		}
		if(!exists){
			Assert.assertTrue(rh.isTextNotPresent(channel));
		}

	}
	
	public void createChannelClone(String origChannel, String clonedChannelName,String clonedChannelLabel, int errata ){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		page_Channels.click_CloneChannel();
		Assert.assertTrue(rh.isTextPresent("Clone Channel"));
		//the select box can not be selected from selenium
		page_Channels.select_CloneFrom(origChannel);

		//errata 0 = Current (all errata)
		//errata 1 = Original (no errata)
		//errata 2 = Select
		if(errata == 0)
			page_Channels.CheckRadio_ChannelAllErrata(true);
		if(errata == 1)
			page_Channels.CheckRadio_ChannelNoErrata(true);
		if(errata == 2)
			page_Channels.CheckRadio_SelectErrata(true);

		page_Channels.clickButton_CreateChannel();

		if(errata == 0)
			Assert.assertTrue(rh.isTextPresent("Current state of the channel"));
		if(errata == 1)
			Assert.assertTrue(rh.isTextPresent("Original channel with no updates"));
		if(errata == 2)
			Assert.assertTrue(rh.isTextPresent("Select errata"));

		page_Channels.setTxt_ClonedChannelName(clonedChannelName);
		page_Channels.setTxt_ClonedChannelLabel(clonedChannelLabel);
		page_Channels.clickButton_CreateChannel_longWait();
		Assert.assertTrue(rh.isTextPresent("Create or edit software channels"));
	}
	
	public void createCustomChannel(String channelName){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		page_Channels.click_createNewChannel();
		page_Channels.setTxt_ChannelName(channelName);
		page_Channels.setTxt_ChannelLabel(channelName);
		page_Channels.setTxt_ChannelSummary(channelName);
		page_Channels.clickButton_CreateChannel();
		Assert.assertTrue(rh.isTextPresent("Channel "+channelName+" created."));
	}
	
	public void createCustomChannel(String channelName, int orgChannelSharingSetting, int orgChannelUserRestriction, String parentChannel){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		page_Channels.click_createNewChannel();
		page_Channels.setTxt_ChannelName(channelName);
		page_Channels.setTxt_ChannelLabel(channelName);
		if(!(parentChannel == null)){
			page_Channels.select_ParentChannel(parentChannel);
		}
		page_Channels.setTxt_ChannelSummary(channelName);
		if(orgChannelSharingSetting == IRhnBase.CHANNEL_SHARE_PRIVATE)
			page_Channels.CheckRadio_OrgSharing_private(true);
		if(orgChannelSharingSetting == IRhnBase.CHANNEL_SHARE_PROTECTED)
			page_Channels.CheckRadio_OrgSharing_protected(true);
		if(orgChannelSharingSetting == IRhnBase.CHANNEL_SHARE_PUBLIC)
			page_Channels.CheckRadio_OrgSharing_public(true);
		if(orgChannelUserRestriction == IRhnBase.CHANNEL_SHARE_ALL_USERS)
			page_Channels.CheckRadio_OrgSharing_AllUsers(true);
		if(orgChannelUserRestriction == IRhnBase.CHANNEL_SHARE_SELECT_USERS)
			page_Channels.CheckRadio_OrgSharing_SelectedUsers(true);
		
		
		page_Channels.clickButton_CreateChannel();
		Assert.assertTrue(rh.isTextPresent("Channel "+channelName+" created."));
	}
	
	public void modifyChannelAccessControl(String channelName, int orgChannelSharingSetting,int orgChannelUserRestriction, boolean grantAccess ){
		page_Channels.open();
		rh.sleepForSeconds(2);
		page_Channels.click_ManageSoftwareChannels();
		Assert.assertTrue(rh.isTextPresent(channelName));
		rh.clickLink("link="+channelName, true);
		
		if(orgChannelSharingSetting == IRhnBase.CHANNEL_SHARE_PRIVATE)
			page_Channels.CheckRadio_OrgSharing_private(true);
		if(orgChannelSharingSetting == IRhnBase.CHANNEL_SHARE_PROTECTED)
			page_Channels.CheckRadio_OrgSharing_protected(true);
		if(orgChannelSharingSetting == IRhnBase.CHANNEL_SHARE_PUBLIC)
			page_Channels.CheckRadio_OrgSharing_public(true);
		if(orgChannelUserRestriction == IRhnBase.CHANNEL_SHARE_ALL_USERS)
			page_Channels.CheckRadio_OrgSharing_AllUsers(true);
		if(orgChannelUserRestriction == IRhnBase.CHANNEL_SHARE_SELECT_USERS)
			page_Channels.CheckRadio_OrgSharing_SelectedUsers(true);
		
		page_Channels.clickButton_UpdateChannel();
		if(rh.isElementPresent(page_Channels.Button_Confirm(),true)){
			page_Channels.clickButton_Confirm();
		}
		
		if(orgChannelSharingSetting == IRhnBase.CHANNEL_SHARE_PROTECTED){
			if(grantAccess)
				page_Channels.clickButton_GrantAccessAndConfirm();
			else
				page_Channels.clickButton_DenyAccessAndConfirm();
			
		}
		
		rh.isTextPresent("Channel "+channelName+ " updated.");
	}
	
	public void modifyTrustedOrgAccess(String channelName, String organizationName, boolean grantAccess ){
		//under construction
		page_Channels.open();
		rh.sleepForSeconds(2);
		page_Channels.click_ManageSoftwareChannels();
		Assert.assertTrue(rh.isTextPresent(channelName));
		rh.clickLink("link="+channelName, true);
		
		page_RhnCommon.setTxt_FilterBy(organizationName);
		page_RhnCommon.clickButton_Filter_Go();
		page_Channels.CheckBox_ChannelOrgAccess(grantAccess, organizationName);
		page_Channels.clickButton_ModifyAccess();
		
		
		assertTrue(rh.isTextPresent("Channel "+channelName+ " updated."));
	}
	
	
	public void verifyPackageInChannel(String Channel, String Package){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_listRemovePackages();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+Package+"*", true);

	}
	
	public void verifyFileSystemPathOfPackageInChannel(String Channel, String Package) {
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_listRemovePackages();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+Package+"*", true);
		String filePath = sel.getText(XPATH_FILE_SYSTEM_PATH);
		log.info("FilePath is " + filePath);
		// TODO: move /var/satellite to config option in localhost-settings.properties
		filePath = "/var/satellite/" + filePath;
		
		log.info("Will attempt to verify file exists at: " + filePath);
		ExecCommands exec = new ExecCommands();
		String retVal = null;
		try{
			String lsCommand = "ls " + filePath;
			retVal = exec.submitCommandToLocalWithReturn(true, "ssh ", "root@"+IRhnBase.SERVER_HOSTNAME +" " + lsCommand);
			log.info("Return Value is : " + retVal);
			
		} catch(IOException ioe){
			log.info("command failed");			
		}
		Assert.assertFalse(retVal.contains("No such file or directory"));
	}
	
	public void verifyMd5sumOfPackageInChannel(String Channel, String Package) {
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_listRemovePackages();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+Package+"*", true);
		String filePath = sel.getText(XPATH_FILE_SYSTEM_PATH);
		log.info("FilePath is " + filePath);
		String md5sum = sel.getText(XPATH_MD5SUM);
		log.info("md5sum is " + md5sum);
		filePath = "/var/satellite/" + filePath;
		log.info("Will attempt to get md5sum for: " + filePath);
		ExecCommands exec = new ExecCommands();
		String retVal = null;
		try{
			String md5sumCommand = "md5sum " + filePath;
			retVal = exec.submitCommandToLocalWithReturn(true, "ssh ", "root@"+IRhnBase.SERVER_HOSTNAME +" " + md5sumCommand);
			log.info("Return Value is : " + retVal);
			
		} catch(IOException ioe){
			log.info("command failed");
		}
		Assert.assertTrue(retVal.contains(md5sum));
	}
	
	
	
	public void removePackageFromFileSystem(String Channel, String Package) {
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_listRemovePackages();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+Package+"*", true);
		String filePath = sel.getText(XPATH_FILE_SYSTEM_PATH);
		log.info("FilePath is " + filePath);
		// TODO: move /var/satellite to config option in localhost-settings.properties
		filePath = "/var/satellite/" + filePath;
		log.info("Will attempt to remove: " + filePath);
		ExecCommands exec = new ExecCommands();
		String retVal = null;
		try{
			String md5sumCommand = "rm  " + filePath;
			retVal = exec.submitCommandToLocalWithReturn(true, "ssh ", "root@"+IRhnBase.SERVER_HOSTNAME +" " + md5sumCommand);
			log.info("Return Value is : " + retVal);
			
		} catch(IOException ioe){
			log.info("command failed");
		}
		Assert.assertFalse(retVal.contains("No such file or directory"));
	}
	
	public void verifyProviderOfPackageInChannel(String Channel, String Package, String Provider) {
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_listRemovePackages();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+Package+"*", true);
		String pkgProvider = sel.getText(XPATH_PKG_PROVIDER);
		log.info("Package provider from webui is: " + pkgProvider);
		Assert.assertTrue(pkgProvider.compareTo(Provider) == 0); 
	}
	
	public void verifyNEVRAisEnabled() {
		ExecCommands exec = new ExecCommands();
		String retVal = null;
		try{
			String grepCommand = "grep 'enable_nvrea' /etc/rhn/rhn.conf";
			retVal = exec.submitCommandToLocalWithReturn(true, "ssh ", "root@"+IRhnBase.SERVER_HOSTNAME +" " + grepCommand);
			log.info("Return Value is : " + retVal);
			
		} catch(IOException ioe){
			log.info("command failed");
		}
		Assert.assertTrue(retVal.compareTo("enable_nvrea=1") == 0, "NEVRA Support Disabled, " + 
				"to change this edit /etc/rhn/rhn.conf: 'enable_nvrea=0' to 'enable_nvrea=1'" + 
				" then restart httpd");
	}
	
	public void gotoManageCustomChannel(String Channel){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		while(!rh.isElementPresent("link="+Channel,false) && rh.isElementPresent("xpath=//input[@type='image' and @alt='Next Page']",false) ){
			sel.clickAndWait("xpath=//input[@type='image' and @alt='Next Page']");
		}
		rh.clickLink("link="+Channel, true);
	}
}
