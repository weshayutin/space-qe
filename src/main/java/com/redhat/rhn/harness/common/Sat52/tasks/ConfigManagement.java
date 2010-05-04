package com.redhat.rhn.harness.common.Sat52.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IConfigManagement;


public class ConfigManagement extends com.redhat.rhn.harness.common.Sat51.tasks.ConfigManagement implements IConfigManagement{

	public void subscribeSystemToChannel(String channel,String system, boolean removeExisting){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        if(removeExisting){
            goToChannel(channel, false);
        	deleteMyChannel(channel);
            page_Configuration.clickCreateNewConfigChannel();
            page_Configuration.setNewConfigChannelName(channel);
            page_Configuration.setNewConfigChannelLabel(channel);
            page_Configuration.setNewConfigChannelDescription(channel);
            page_Configuration.clickButton_CreateConfigChannel();        	
        }
        
        goToChannel(channel, false);
        page_Configuration.clickLink_SystemsForConfigChannel();
        page_Configuration.clickLink_TargetSystems();

        page_RhnCommon.setTxt_FilterBy(system);
        page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_SubscribeSystems();
        Assert.assertTrue(rh.isTextPresent("Successfully subscribed 1 system(s)."));
        page_Configuration.clickLink_SubscribedSystems();
        Assert.assertTrue(rh.isTextPresent(system));
	}
	private void deleteMyChannel(String channel){
		if(rh.isTextPresent(channel)){
			page_Configuration.clickLink_DeleteChannel();
			page_Configuration.clickButton_DeleteConfigChannel();
			page_Configuration.clickLink_ConfigurationChannels();
		}
	}	
}
