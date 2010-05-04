package com.redhat.rhn.harness.common.Sat52.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IActivationKeys;

public class ActivationKeys extends com.redhat.rhn.harness.common.Sat51.tasks.ActivationKeys implements IActivationKeys{

	public void createActivationKey(String name,String key, String usageLimit, String channel,
			boolean monitoring, boolean provisioning,
			boolean virt, boolean virt_Platform,boolean unvDefault){

		page_ActivationKeys.open();
		if(rh.isTextNotPresent(name)){
			page_ActivationKeys.clickLink_CreateNewKey();
			page_ActivationKeys.setTxt_Description(name);
			page_ActivationKeys.setTxt_Key(key);
			page_ActivationKeys.setTxt_UsageLimit(usageLimit);
			page_ActivationKeys.select_BaseChannel(channel);
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


	/**
	 * Applies config channel to an activation key.
	 * @param key String activation key name
	 * @param channel String Configuration channel name
	 */
	public void addConfigChannelToKey(String key,String channel){
		goToKey(key);
		Assert.assertTrue(rh.isElementPresent("link=Packages", true));
		Assert.assertTrue(rh.isElementPresent("xpath=//a[contains(@href, '/network/account/activation_keys/namespaces.pxt')]", true));
		page_ActivationKeys.clickLink_Configuration();
		page_ActivationKeys.clickLink_SubscribeToChannels();
		page_RhnCommon.setTxt_FilterBy(channel);
		page_RhnCommon.clickButton_Filter_Go();
		page_ActivationKeys.checkBox_firstConfigChannel(true);
		page_ActivationKeys.clickButton_ContinueFilterConfigChannel();
		page_ActivationKeys.clickButton_UpdateRankingConfigChannel(); // will do nothing if the button could not be found
	}
}
