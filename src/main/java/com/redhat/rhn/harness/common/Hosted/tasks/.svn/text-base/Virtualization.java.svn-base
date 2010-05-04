package com.redhat.rhn.harness.common.Hosted.tasks;


public class Virtualization extends com.redhat.rhn.harness.common.Sat42.tasks.Virtualization{
	
	public void removeAllVirtGuestsFromSystem(String system, boolean openAndLogin){
		String status= null;
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		if(rh.isTextNotPresent("No virtual systems.")){
			page_RhnCommon.clickButton_SelectAll();
			//This method actually clicks the "Delete Selected Guests" button,
			//as the fancier virt control stuff doesn't exist in hosted
			page_SDC.select_Virt_GuestAction_Delete();
			page_SDC.clickButton_Confirm();
			assertTrue(rh.isTextPresent("No virtual systems."));
		}
		 
	}

}
