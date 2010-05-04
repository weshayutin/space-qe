package com.redhat.rhn.harness.common.Sat51.tasks;

import com.redhat.rhn.harness.baseInterface.IMonitoring;

public class Monitoring extends com.redhat.rhn.harness.common.Sat42.tasks.Monitoring implements IMonitoring{




	public void enableMonitoringScout(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		page_SatelliteTools.clickLink_Monitoring();
		page_SatelliteTools.check_EnableMonitoringScout(true);
		page_SatelliteTools.clickButton_UpdateConfig();
		if(rh.isTextPresent("Configuration updated, Monitoring services restarted."))
			log.fine("Scout enabled for the first time");
		if(rh.isTextPresent("No changes made."))
			log.info("Scout was previously enabled");
	}
	
	
	public String monitoring_getSSHPublicKey(boolean openAndLogin,boolean proxy){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}

		page_Monitoring.open();
		page_Monitoring.clickLink_ScoutConfigPush();
		if(proxy){
			page_Monitoring.clickLink_RHN_Proxy_Scout();
		}
		else{
			page_Monitoring.clickLink_RHN_Monitoring_Scout();
		}
		String mykey = page_Monitoring.getTxt_RHNMD_public_key_for_Scout();
		log.info("mykey = "+mykey);
		return mykey;
	}
	
	
	
	
	

}
