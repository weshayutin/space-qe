package com.redhat.rhn.harness.common.Sat50.tasks;

import com.redhat.rhn.harness.baseInterface.IChannels;

public class Channels extends com.redhat.rhn.harness.common.Sat42.tasks.Channels implements IChannels{
	
	

	
	public void deleteAllCustomChannels(){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		while(rh.isElementPresent("//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr[1]/td/a", true)){
			rh.clickLink("//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr[1]/td/a", true);
			page_Channels.click_DeleteSoftwareChannel();
			page_Channels.clickButton_DeleteChannel();
			if(rh.isTextPresent("A channel cannot be deleted")){
				page_Channels.click_ManageSoftwareChannels();
				rh.clickLink("//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr[2]/td/a", true);
				page_Channels.click_DeleteSoftwareChannel();
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
				rh.clickLink("//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr[1]/td/a", true);
				page_Channels.click_DeleteSoftwareChannel();
				page_Channels.clickButton_DeleteChannel();
				}
			page_RhnCommon.clickChannels();
			page_Channels.click_ManageSoftwareChannels();
		}
	}
	
	public void verifyPatchInChannel(String Channel, String Patch){
		//Strip off '.mpm', as this text is not included in patch descriptors
		String patchString = Patch.replaceAll(".mpm", "");
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPatches();
		page_Channels.click_listRemovePatches();
		page_RhnCommon.setTxt_FilterBy(patchString);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+patchString+"*", true);
	}
}
