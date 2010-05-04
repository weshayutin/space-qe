package com.redhat.rhn.harness.common.Space01.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IActivationKeys;
import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class ActivationKeys extends com.redhat.rhn.harness.common.Sat51.tasks.ActivationKeys implements IActivationKeys{

	public final String AK_XPATH = "xpath=/html/body/div/div[2]/div/table/tbody/tr/td[2]/form/table[2]";

	public void createActivationKey(String name,String key, String usageLimit,
			boolean monitoring, boolean provisioning,
			boolean virt, boolean virt_Platform,boolean unvDefault){

			
			page_ActivationKeys.open();
			if(rh.isTextNotPresent(name)){
				page_ActivationKeys.clickLink_CreateNewKey();
				page_ActivationKeys.setTxt_Description(name);
				page_ActivationKeys.setTxt_Key(key);
				page_ActivationKeys.setTxt_UsageLimit(usageLimit);
				page_ActivationKeys.select_BaseChannel(IRhnBase.RHN_CHANNEL01);
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
	
	public void addPackageToKey(String key, String pkg){
		goToKey(key);
		page_ActivationKeys.clickLink_Packages();
		page_ActivationKeys.setTxt_EnterPackageNames(pkg);
		page_ActivationKeys.clickButton_UpdateKey();
		Assert.assertTrue(rh.isTextPresent("Activation key "+key+" has been modified."));
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
	
	
	public void addProvisioningToKey(String key){
		task_RhnBase.OpenAndLogIn();
		goToKey(key);
		Assert.assertTrue(rh.isElementPresent("link=Packages", true));
		Assert.assertFalse(rh.isElementPresent("xpath=//a[contains(@href, '/rhn/activationkeys/configuration/List.do')]", true));
		page_ActivationKeys.checkBox_Provisioning(true);
		page_ActivationKeys.clickButton_UpdateActivationKey();
		//Assert.assertTrue(rh.isElementPresent("link=Packages"));
		Assert.assertTrue(rh.isElementPresent("xpath=//a[contains(@href, '/rhn/activationkeys/configuration/List.do')]", true));
		rh.isTextPresent("has been modified.");
	}	
}
