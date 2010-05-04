package com.redhat.rhn.harness.common.Space01.tasks;

import java.text.NumberFormat;
import java.text.ParseException;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.thoughtworks.selenium.SeleniumException;

public class RhnBase extends com.redhat.rhn.harness.common.Sat51.tasks.RhnBase implements IRhnBase {

	
	public int verifyEntitlementCounts(boolean openAndLogin,
									   	int entitlement,
									   	boolean consumed) {
		
		return task_YourRhn.verifyEntitlementCounts(openAndLogin, entitlement, consumed);
	}

	
	public boolean register_check_activation_key(){
		return task_TestPrep.register_check_activation_key();
	}
	
	public void createActivationKey(String name,String key,
									String usageLimit,
									String channel,
									boolean monitoring,
									boolean provisioning,
									boolean virt, 
									boolean virt_Platform,
									boolean unvDefault){
		task_ActivationKeys.createActivationKey(name, key, usageLimit, channel, monitoring, provisioning, virt, virt_Platform, unvDefault);
		
	}
	
	public void enableRHNConfigManag(String system, 
									boolean hosted,
									boolean openAndLogin){
		task_TestPrep.enableRHNConfigManag(system, hosted, openAndLogin);
	}
	
	public boolean check_CfgMang_Status(String system, boolean hosted) {
		return task_TestPrep.check_CfgMang_Status(system, hosted);
	}
	
	public void deleteAllCustomChannels(){
		task_Channels.deleteAllCustomChannels();
	}
	
	public void deleteCustomChannel(String clonedChannelName){
		task_Channels.deleteCustomChannel(clonedChannelName);
	}

	 public void deleteSysGroup(String sysGroup){
		 page_SatelliteSystems.open();
		 page_SatelliteSystems.clickLink_SystemGroups();
		 if(!sel.isElementPresent("id=list_total")) return;// there is no sys group, return.
		try{
		 	int sysGroupCount = NumberFormat.getInstance().parse(sel.getText("id=list_total").trim()).intValue();
			int sysgroupsInPage = NumberFormat.getInstance().parse(sel.getText("id=list_max").trim()).intValue();
			int pagesCount = sysGroupCount / sysgroupsInPage;
			if(sysGroupCount%sysgroupsInPage > 0) pagesCount++;
			for(int i=0;i<pagesCount;i++){
				for(int j=0;j<sysgroupsInPage;j++){
					// for the last page there could be less elements than the sysgroupsInPage has, but it's ok
					// as we are using the isElementPresent method...
					if(sel.isElementPresent("xpath=//table[@class='list']/tbody/tr["+(j+1)+"]/td[4]")){
						if(sel.getText("xpath=//table[@class='list']/tbody/tr["+(j+1)+"]/td[4]").equals(sysGroup)){
							sel.clickAndWait("xpath=//table[@class='list']/tbody/tr["+(j+1)+"]/td[4]/a");
							page_SatelliteSystems.clickLink_delete_group();
							page_SatelliteSystems.clickButton_Confirm_Deletion();
							return;
						}
					}
				}
				if(sel.isElementPresent("xpath=//input[@type='image' and @alt='Next Page']"))
					sel.clickAndWait("xpath=//input[@type='image' and @alt='Next Page']");
			}
		}catch(ParseException pex){
			return; // do nothing
		}
	 }
	
	public void verifyChannelExists(String channel, boolean exists){
		task_Channels.verifyChannelExists(channel, exists);
	}
	
	public void createChannelClone(String origChannel, String clonedChannelName,String clonedChannelLabel, int errata ){
		task_Channels.createChannelClone(origChannel, clonedChannelName, clonedChannelLabel, errata);
	}
	
	public void createCustomChannel(String channelName){
		task_Channels.createCustomChannel(channelName);
	}
	
	public void verifyPackageInChannel(String Channel, String Package){
		task_Channels.verifyPackageInChannel(Channel, Package);
	}
	
	public void deleteAllConfigChannels(boolean openAndLogin){
		task_ConfigMang.deleteAllConfigChannels(openAndLogin);
	}
	
	public String getSystemID(String system, boolean hosted,boolean searchForSystem) {
		return task_Search.getSystemID(system, hosted, searchForSystem);
	}
	
	public void advancedSearch(int quickSearchType, int fieldToSearch, String searchValue, boolean openAndLogin, boolean testResults){
		task_Search.advancedSearch(quickSearchType, fieldToSearch, searchValue, openAndLogin, testResults);
	}
	
	public void installPackage(String system, String pkg){
		task_Sdc.installPackage(system, pkg);
	}
	
	public void select_File_Checkbox(String file) {
		task_Sdc.select_File_Checkbox(file);
	}
	
	public void createActivationKey(String name,
									String key,
									String usageLimit,
									boolean monitoring,
									boolean provisioning,
									boolean virt, 
									boolean virt_Platform,
									boolean unvDefault){
	
		task_ActivationKeys.createActivationKey(name, key, usageLimit, monitoring, provisioning, virt, virt_Platform, unvDefault);
	}

	

	
	public void modifyOrgTrusts(String trustee, String trustor, boolean trust){
		task_SatelliteTools.modifyOrgTrusts(trustee, trustor, trust);
	}
	
	public boolean checkForSharedChannel(String channel, String org){
		return task_YourRhn.checkForSharedChannel(channel, org);
	}
	
	public void createCustomChannel(String channelName, int orgChannelSharingSetting, int orgChannelUserRestriction, String parentChannel){
		task_Channels.createCustomChannel(channelName, orgChannelSharingSetting, orgChannelUserRestriction, null);
	}
	
	
	
	
	}
