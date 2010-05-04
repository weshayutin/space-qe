package com.redhat.rhn.harness.common.Sat51.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IActivationKeys;
import com.thoughtworks.selenium.SeleniumException;

public class ActivationKeys extends com.redhat.rhn.harness.common.Sat50.tasks.ActivationKeys implements IActivationKeys{

	public final String AK_XPATH = "xpath=/html/body/div/div[5]/table/tbody/tr/td[2]/form/table";


	public void createActivationKey(String name,String key, String usageLimit,
			boolean monitoring, boolean provisioning,
			boolean virt, boolean virt_Platform,boolean unvDefault){

			page_ActivationKeys.open();
			if(rh.isTextNotPresent(name)){
				page_ActivationKeys.clickLink_CreateNewKey();
				page_ActivationKeys.setTxt_Description(name);
				page_ActivationKeys.setTxt_Key(key);
				page_ActivationKeys.setTxt_UsageLimit(usageLimit);
				page_ActivationKeys.checkBox_Monitoring(monitoring);
				page_ActivationKeys.checkBox_Provisioning(provisioning);
				page_ActivationKeys.checkBox_Virtualization(virt);
				page_ActivationKeys.checkBox_VirtualizationPlatform(virt_Platform);
				if(unvDefault)
				//akp.select_UniversalDefault("Yes, use this token for all normal registrations");
				page_ActivationKeys.checkBox_UniversalDefault(true);
				else
				//akp.select_UniversalDefault("No");
				page_ActivationKeys.checkBox_UniversalDefault(false);
				page_ActivationKeys.clickButton_CreateKey();
			}
			else{
				log.info("Activation key "+ name +" already exists");
			}
		}

	public void createActivationKeyWithOSAD(String name,String key, String usageLimit){
		page_ActivationKeys.open();
		if(rh.isTextNotPresent("No activation keys available")){
		page_RhnCommon.setTxt_FilterBy(name);
		page_RhnCommon.clickButton_Filter_Go();
		}
		if(rh.isTextNotPresent(name)){
			page_ActivationKeys.clickLink_CreateNewKey();
			page_ActivationKeys.setTxt_Description(name);
			page_ActivationKeys.setTxt_Key(key);
			page_ActivationKeys.setTxt_UsageLimit(usageLimit);
			page_ActivationKeys.checkBox_Provisioning(true);
			page_ActivationKeys.checkBox_UniversalDefault(false);
			page_ActivationKeys.clickButton_CreateKey();
			page_RhnCommon.clickLink_GeneralLink(name);
			page_ActivationKeys.clickLink_ChildChannels();
			try{
				log.info("Selecting tools channels");
				sel.addSelection("token_child_channels", "Red Hat Network Tools for RHEL AS (v.4 for x86)");
				sel.addSelection("token_child_channels", "Red Hat Network Tools for RHEL Server (v.5 32-bit x86)");
			}
			catch(SeleniumException se){
				log.info("Selecting child channels failed");
			}
			page_ActivationKeys.clickButton_UpdateKey();
			
			page_ActivationKeys.clickLink_Packages();
			page_ActivationKeys.setTxt_EnterPackageNames("osad");
			page_ActivationKeys.clickButton_UpdateKey();
		}
		else{
			log.info("Activation key "+ name + "already created");
		}
	}
	
	public void createActivationKeyWithBaseChannel(String name,String key, String basechan, 
			   String usageLimit, boolean monitoring, boolean provisioning,
			   boolean virt, boolean virt_Platform,boolean unvDefault){
		page_ActivationKeys.open();
		if(rh.isTextNotPresent(name)){
			page_ActivationKeys.clickLink_CreateNewKey();
			page_ActivationKeys.setTxt_Description(name);
			page_ActivationKeys.setTxt_Key(key);
			page_ActivationKeys.setTxt_UsageLimit(usageLimit);
			page_ActivationKeys.select_BaseChannel(basechan);
			page_ActivationKeys.checkBox_Monitoring(monitoring);
			page_ActivationKeys.checkBox_Provisioning(provisioning);
			page_ActivationKeys.checkBox_Virtualization(virt);
			page_ActivationKeys.checkBox_VirtualizationPlatform(virt_Platform);
			if(unvDefault)
			//akp.select_UniversalDefault("Yes, use this token for all normal registrations");
			page_ActivationKeys.checkBox_UniversalDefault(true);
			else
			//akp.select_UniversalDefault("No");
			page_ActivationKeys.checkBox_UniversalDefault(false);
			page_ActivationKeys.clickButton_CreateKey();
		}
		else{
			log.info("Activation key "+ name +" already exists");
		}
	}
	
	public void addProvisioningToKey(String key){
		task_RhnBase.OpenAndLogIn();
		goToKey(key);
		//Assert.assertTrue(rh.isTextNotPresent("Packages"));
		Assert.assertFalse(rh.isElementPresent("link=Packages", true));
	//	Assert.assertTrue(rh.isTextNotPresent("Configuration"));
		page_ActivationKeys.checkBox_Provisioning(true);
		page_ActivationKeys.clickButton_UpdateActivationKey();
		//Assert.assertTrue(rh.isElementPresent("link=Packages"));
		//Assert.assertTrue(rh.isElementPresent("link=Configuration"));
		rh.isTextPresent("has been modified.");
	}
	
	public void goToKey(String key){
		page_ActivationKeys.open();
		//rc.setTxt_FilterBy(key);
		page_RhnCommon.setTxt_FilterBy(key);
		page_RhnCommon.clickButton_Filter_Go();
		if(rh.isTextPresent(key)){
			page_RhnCommon.clickLink_GeneralLink(key);
		}
	}
	
	public String getAKFromKeyName(String keyName){
		page_ActivationKeys.open();
		page_RhnCommon.setTxt_FilterBy(keyName);
		page_RhnCommon.clickButton_Filter_Go();		
		String ak = 
			rh.getTableData(
					AK_XPATH,
					keyName, 3);
		log.info("Activation Key is : " + ak);
		return ak;
	}
	
	public boolean isSystemActivated(String system,String key){
		boolean activated = false;
		page_ActivationKeys.open();
		goToKey(key);
		page_ActivationKeys.clickLink_ActivatedSystem();
		if(rh.isTextPresent(system))
			activated=true;
		else
			activated=false;
		return activated;
	}
	
	public void deleteActivationKey(String name, boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_ActivationKeys.open();
		if(rh.isTextNotPresent("No activation keys available")){
		page_RhnCommon.setTxt_FilterBy(name);
		page_RhnCommon.clickButton_Filter_Go();
		while(rh.isTextPresent(name)){
			if(rh.isTextPresent(name)){
				page_RhnCommon.clickLink_GeneralLink(name);
				page_ActivationKeys.clickLink_DeleteKey();
				page_ActivationKeys.clickButton_Delete();
				Assert.assertTrue(rh.isTextPresent("has been deleted"));
			}
			page_ActivationKeys.open();
			if(rh.isTextNotPresent("No activation keys available")){
				page_RhnCommon.setTxt_FilterBy(name);
				page_RhnCommon.clickButton_Filter_Go();
			}
			//Assert.assertTrue(rh.isTextNotPresent(name));
			}
		}
	}
	
	public void addPackageToKey(String key, String pkg){
		goToKey(key);
		page_ActivationKeys.clickLink_Packages();
		page_ActivationKeys.setTxt_EnterPackageNames(pkg);
		page_ActivationKeys.clickButton_UpdateKey();
		Assert.assertTrue(rh.isTextPresent("Activation Key "+key+" has been modified."));
	}

	
}
