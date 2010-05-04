package com.redhat.rhn.harness.common.Sat50.tasks;

import com.redhat.rhn.harness.baseInterface.IActivationKeys;

public class ActivationKeys extends com.redhat.rhn.harness.common.Sat42.tasks.ActivationKeys implements IActivationKeys{


	



	public void createActivationKeyWithBaseChannel(String name,String key, String basechan, 
			   String usageLimit, boolean monitoring, boolean provisioning,
			   boolean virt, boolean virt_Platform,boolean unvDefault){
			page_ActivationKeys.open();
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
				page_ActivationKeys.select_UniversalDefault("Yes, use this token for all normal registrations");
			else
				page_ActivationKeys.select_UniversalDefault("No");
			page_ActivationKeys.clickButton_CreateKey();
	}

	public void createActivationKey(String name,String key, String usageLimit,
			boolean monitoring, boolean provisioning,
			boolean virt, boolean virt_Platform,boolean unvDefault){

			page_ActivationKeys.open();
			page_ActivationKeys.clickLink_CreateNewKey();
			page_ActivationKeys.setTxt_Description(name);
			page_ActivationKeys.setTxt_Key(key);
			page_ActivationKeys.setTxt_UsageLimit(usageLimit);
			page_ActivationKeys.checkBox_Monitoring(monitoring);
			page_ActivationKeys.checkBox_Provisioning(provisioning);
			page_ActivationKeys.checkBox_Virtualization(virt);
			page_ActivationKeys.checkBox_VirtualizationPlatform(virt_Platform);
			if(unvDefault)
			page_ActivationKeys.select_UniversalDefault("Yes, use this token for all normal registrations");
			else
			page_ActivationKeys.select_UniversalDefault("No");
			page_ActivationKeys.clickButton_CreateKey();
	}

}
