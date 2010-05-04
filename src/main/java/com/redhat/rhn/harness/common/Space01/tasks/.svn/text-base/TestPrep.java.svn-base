package com.redhat.rhn.harness.common.Space01.tasks;



import java.util.logging.Level;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ITestPrep;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.thoughtworks.selenium.SeleniumException;

public class TestPrep extends com.redhat.rhn.harness.common.Sat51.tasks.TestPrep implements ITestPrep{

	
	String commandPath = HarnessConfiguration.RHN_SCRIPT_DIR;
	String urlxml = "/var/lib/tomcat5/webapps/rhn/WEB-INF/struts-config.xml";
	String urlDir = "/var/tmp/rhn/src/main/resources/";


	public boolean register_check_activation_key(){
		boolean isPresent = false;
		if(IRhnBase.SPACEWALK == 0){
			log.finer("This is not a spacewalk install, no key is ok");
			return true;
		}
		else if(IRhnBase.SPACEWALK == 1){
			task_ActivationKeys.goToKey(IRhnBase.SPACEWALK_ACTIVATION_KEY_NAME);
			if(rh.isTextPresent(IRhnBase.SPACEWALK_ACTIVATION_KEY_NAME)){
				isPresent = true;
			}
		}
		else{
			isPresent = false;
			throw new SeleniumException("You are trying to register a system to spacewalk that requires an activation key, and there is no key present");
		}
				
		return isPresent;
	}
	
	public String getActicationKeyNumber(String activationKeyName){
		String key_number;
		task_ActivationKeys.goToKey(activationKeyName);
		key_number = "placeholder";
		return key_number;
	}
	
	public void enableRHNConfigManag(String system, boolean hosted,	boolean openAndLogin) {
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
		//command_runRhnCheckWithAT(system, true);
		command_runRhnCheckInForeground(system, true);
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
	
	public void enableRHNConfigManagNoAsserts(String system, boolean hosted,	boolean openAndLogin) {
		if (openAndLogin) {
			task_RhnBase.OpenAndLogIn();
		}
		page_SystemProvisioning.open();
		step01ConfigManagementNoAsserts(system, hosted);
		page_RhnCommon.clickSchedule();
		page_Schedule.clickLink_PendingActions();
		page_Schedule.clickLink_PackageInstall();
		page_Schedule.clickLink_InProgressSystems();
		command_runRhnCheckWithAT(system, true);
		rh.sleepForSeconds(30);

		if (!check_CfgMang_Status(system, hosted)) {
			rh.sleepForSeconds(30);
 			if (!check_CfgMang_Status(system, hosted)) {
				log.info("Enabling Config Management not done, checking, last time");
				rh.sleepForSeconds(60);
				check_CfgMang_Status(system, hosted);
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
	
	private void step01ConfigManagementNoAsserts(String system, boolean hosted) {
		enableProvisioning(system, false);
		removeRHNConfigFilesFromSystem(system);
		check_CfgMang_Status(system, hosted);
		page_Configuration.clickLink_Target_Systems();
		page_Configuration.select_Enable_CfgManagementTarget_Checkbox(system, hosted);
		page_Configuration.clickLink_Target_Systems();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_Configuration.clickButton_EnableRHNConfigurationManagement();

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
	
	public boolean check_CfgMang_Status(String system, boolean hosted) {
		boolean result = false;
		page_RhnCommon.clickConfiguration();
	//	cp.clickLink_Overview();
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
