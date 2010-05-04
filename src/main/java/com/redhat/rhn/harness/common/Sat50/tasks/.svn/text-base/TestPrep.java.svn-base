package com.redhat.rhn.harness.common.Sat50.tasks;



import java.util.logging.Level;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ITestPrep;
import com.redhat.rhn.harness.common.HarnessConfiguration;

public class TestPrep extends com.redhat.rhn.harness.common.Sat42.tasks.TestPrep implements ITestPrep{

	


	String commandPath = HarnessConfiguration.RHN_SCRIPT_DIR;
	String urlxml = "/var/lib/tomcat5/webapps/rhn/WEB-INF/struts-config.xml";
	String urlDir = "/var/tmp/rhn/src/main/resources/";

/*	public void registerMultipleProfiles(String system,
										String testSystemRootName,
										int numberOfSystems,
										String command,
										boolean deleteOld,
										boolean hosted, 
										boolean openAndLogin) {
		
	String systemName = null;
	systemName = registerMultipleProfiles_part1(system,
												testSystemRootName, 
												numberOfSystems,
												command, 
												deleteOld,
												hosted,
												openAndLogin);

	quickSearch.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS,
							systemName,
							false,
							true);

	}
	*/
	public void enableMonitoringEntitlement(String system,
			boolean openSystemPage) {
		sdcEnableEntitlement(system, openSystemPage);
		page_SystemProvisioning.check_Monitoring();
		page_SystemProvisioning.clickButton_UpdateProperties();
	}
	
	protected void sdcEnableEntitlement(String system, boolean openSystemPage) {
		if (openSystemPage) {
			page_SatelliteSystems.open();
		}
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SystemProvisioning.clickLink_EditSystemProperties();
	}
	
	private void step01ConfigManagement(String system, boolean hosted) {
		enableProvisioning(system, false);
		check_CfgMang_Status(system, hosted);
		Assert.assertFalse(check_CfgMang_Status(system, hosted));
		page_Configuration.select_Enable_CfgManagementTarget_Checkbox(system, hosted);
		page_Configuration.clickButton_EnableRHNConfigurationManagement();
	}
	
	public void enableRHNConfigManag(String system, boolean hosted,
			boolean openAndLogin) {
		if (openAndLogin) {
			task_RhnBase.OpenAndLogIn();
		}
		command_rpm(" -e --nodeps rhncfg ", IRhnBase.SYSTEM_HOSTNAME01, true);
		command_rpm(" -e --nodeps rhncfg-client", IRhnBase.SYSTEM_HOSTNAME01,
				true);
		command_rpm(" -e --nodeps rhncfg-actions", IRhnBase.SYSTEM_HOSTNAME01,
				true);
		page_SystemProvisioning.open();

		step01ConfigManagement(system, hosted);

		page_RhnCommon.clickSchedule();
		page_Schedule.clickLink_PendingActions();
		page_Schedule.clickLink_PackageInstall();
		page_Schedule.clickLink_InProgressSystems();
		Assert.assertTrue(rh.isTextPresent(system));

		ExecCommands exec = new ExecCommands();
		try {
			command_runRhnCheckWithAT(system, true);
		} catch (Exception ioe) {
			log.log(Level.INFO, "command failed", ioe);
		}

		if (!check_CfgMang_Status(system, hosted)) {
			log.info("Enabling Config Management not done, checking one more time");
			if (!check_CfgMang_Status(system, hosted)) {
				log.info("Enabling Config Management not done, checking, last time");
				Assert.assertTrue(check_CfgMang_Status(system, hosted));
			}
		} else
			log.info("Config Management is Enabled");

		try {
			exec.submitCommandToLocalWithReturn(true, "ssh", "root@" + system
					+ " rhn-actions-control --enable-all");
		} catch (Exception e) {
			log.log(Level.INFO,"command failed", e);
		}
	}
	
	public void removeAllSystemProfiles(boolean openAndLogin) {
		if (openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		if (rh.isTextNotPresent("No systems.")) {
			page_RhnCommon.clickButton_SelectAll();
			page_SSM.clickButton_Manage();
			page_SSM.clickLink_Delete();
			page_SSM.clickButton_ConfirmDeletions();
		}
		page_SatelliteSystems.open();
		Assert.assertTrue(rh.isTextPresent("No systems."));

	}



}
