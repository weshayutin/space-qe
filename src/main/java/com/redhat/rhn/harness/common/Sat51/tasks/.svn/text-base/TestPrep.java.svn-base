package com.redhat.rhn.harness.common.Sat51.tasks;



import java.io.IOException;
import java.util.logging.Level;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.ITestPrep;
import com.redhat.rhn.harness.common.HarnessConfiguration;

public class TestPrep extends com.redhat.rhn.harness.common.Sat50.tasks.TestPrep implements ITestPrep{


	String commandPath = HarnessConfiguration.RHN_SCRIPT_DIR;
	String urlxml = "/var/lib/tomcat5/webapps/rhn/WEB-INF/struts-config.xml";
	String urlDir = "/var/tmp/rhn/src/main/resources/";


	
	public void command_change_webForceUnentitlement(String system,boolean turnOn, boolean showlogs){
		ExecCommands exec = new ExecCommands();
		if(turnOn){
		try{
			exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system+" perl -p -e 's/web.force_unentitlement=0/web.force_unentitlement=1/' -i /etc/rhn/rhn.conf");
			} catch(IOException ioe){
			log.info("command failed");
			}
		}
		if(!turnOn){
			try{
				exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system+" perl -p -e 's/web.force_unentitlement=1/web.force_unentitlement=0/' -i /etc/rhn/rhn.conf");
				} catch(IOException ioe){
				log.info("command failed");
			}
		}
	}
	
	
	
	private void step01ConfigManagement(String system, boolean hosted) {
		enableProvisioning(system, false);
		removeRHNConfigFilesFromSystem(system);
		Assert.assertFalse(check_CfgMang_Status(system, hosted));
		page_Configuration.clickLink_Target_Systems();
		page_Configuration.select_Enable_CfgManagementTarget_Checkbox(system, hosted);
		page_Configuration.clickLink_Target_Systems();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_Configuration.clickButton_EnableRHNConfigurationManagement();

	}
	
	public void enableRHNConfigManag(String system, boolean hosted,
			boolean openAndLogin) {
		if (openAndLogin) {
			task_RhnBase.OpenAndLogIn();
		}
		page_SystemProvisioning.open();
		step01ConfigManagement(system, hosted);
		page_RhnCommon.clickSchedule();
		page_Schedule.clickLink_PendingActions();
		page_Schedule.clickLink_PackageInstall();
		page_Schedule.clickLink_InProgressSystems();
		Assert.assertTrue(rh.isTextPresent(system));
		command_runRhnCheckWithAT(system, true);
		rh.sleepForSeconds(30);

		if (!check_CfgMang_Status(system, hosted)) {
			rh.sleepForSeconds(30);
 			if (!check_CfgMang_Status(system, hosted)) {
				log.info("Enabling Config Management not done, checking, last time");
				rh.sleepForSeconds(60);
				Assert.assertTrue(check_CfgMang_Status(system, hosted));
			}
		} else
			log.info("Config Management is Enabled");

		try {
			ExecCommands exec = new ExecCommands();
			exec.submitCommandToLocalWithReturn(true, "ssh", "root@" + system+ " rhn-actions-control --enable-all");
		} catch (Exception e) {
			log.log(Level.INFO,"command failed", e);
		}
	}

	public void createCustomSystemInfo(String keyLabel, String keyDescription){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_CustomSystemInfo();
		if(rh.isTextNotPresent("No custom information keys defined")){
			if(rh.isTextPresent(keyLabel)){
			page_RhnCommon.clickLink_GeneralLink(keyLabel);
			page_SDC.clickLink_CustomInfo_deleteKey();
			page_SDC.clickButton_Delete();
			}
		}
		page_SatelliteSystems.clickLink_CustomSystemInfo_CreateNewKey();
		page_SatelliteSystems.setText_CustomKey_KeyLabel(keyLabel);
		page_SatelliteSystems.setText_CustomKey_KeyDescription(keyDescription);
		page_SatelliteSystems.clickButton_CustomInfo_CreateKey();
		
	}

public boolean check_CfgMang_Status(String system, boolean hosted) {
	boolean result = false;
	page_RhnCommon.clickConfiguration();
	page_Configuration.clickLink_Overview();
	page_Configuration.clickLink_EnableConfigMangOnSystems();
	page_Configuration.clickLink_Managed_Systems();
		if(rh.isTextNotPresent("No configuration managed systems.")){
			page_RhnCommon.setTxt_FilterBy(system);
			page_RhnCommon.clickButton_Filter_Go();
			if(rh.isTextPresent(system))
				result = true;
		}
		else{
			result = false;
		}
		
	
	return result;
}
	
}
