package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;
import java.util.logging.Level;

import com.redhat.qe.auto.testopia.Assert;
import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;
/**
 * A very important class, tasks used to setup and prep for any other test. For
 * example tasks like running rhn_check
 * 
 * @author whayutin
 * 
 */
public class TestPrep extends SeleniumSetup { //implements ITestPrep {

	protected static  RhnHelper rh = new RhnHelper();

	String commandPath = HarnessConfiguration.RHN_SCRIPT_DIR;
	String urlxml = "/var/lib/tomcat5/webapps/rhn/WEB-INF/struts-config.xml";
	String urlDir = "/var/tmp/rhn/src/main/resources/";

	public void removeRHNConfigFilesFromSystem(String system) {
		ssh.executeViaSSH(system,
				"rpm -e --nodeps rhncfg-management");
		ssh.executeViaSSH(system,
				"rpm -e --nodeps rhncfg rhncfg-client rhncfg-actions");
		ssh.executeViaSSH(system,
				"rpm -q rhncfg rhncfg-client rhncfg-actions rhncfg-management");
		
		command_updateSystemProfile(system);
	}
	
	public void command_updateSystemProfile(String system){
		if(task_TestPrep.command_check_RHEL_Version(system) == 4){
			ssh.executeViaSSH(system,"up2date -p");			// RHEL4
		}
		if(task_TestPrep.command_check_RHEL_Version(system) == 5){
			ssh.executeViaSSH(system,"rhn-profile-sync");	// RHEL5
			ssh.executeViaSSH(system,"yum clean all");	// RHEL5
		}	
	}

	public void command_wget(String fullUrl) {
		// This will download to /var/tmp/rhn
		ExecCommands exec = new ExecCommands();
		try {
			exec.submitCommandToLocalWithReturn(false, "wget", " " + fullUrl);
		} catch (IOException ioe) {
			log.log(Level.INFO, "command failed", ioe);
		}
	}

	public void unregisterSystem(String system, boolean login) {
		if (login){
			task_RhnBase.OpenAndLogIn();
		}
		removeSystemProfile(system);
	}

	public void unregisterAllProfilesOfSystem(String system, boolean openAndLogin) {
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		task_Search.goToSystem(system);
		while (rh.isTextPresent(system + "*")) {
			page_RhnCommon.clickLink_SystemName(system + "*");
			page_SatelliteSystems.clickLink_DeleteSystem();
			page_SatelliteSystems.clickButton_DeleteProfile();
			page_SatelliteSystems.open();
			page_SatelliteSystems.clickLink_AllSystems();
		}

		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_AllSystems();
		if (rh.isTextNotPresent("No systems.")) {
			page_RhnCommon.setTxt_FilterBy(system);
			page_RhnCommon.clickButton_Filter_Go();
		}
		Assert.assertFalse(task_Search.goToSystem(system));

		task_Search.goToSystem(HarnessConfiguration.RHN_TEST_PREFIX);
		while (rh.isTextPresent(HarnessConfiguration.RHN_TEST_PREFIX + "*")) {
			page_RhnCommon.clickLink_SystemName(HarnessConfiguration.RHN_TEST_PREFIX + "*");
			page_SatelliteSystems.clickLink_DeleteSystem();
			page_SatelliteSystems.clickButton_DeleteProfile();
			page_SatelliteSystems.open();
			page_SatelliteSystems.clickLink_AllSystems();
		}

		Assert.assertFalse(task_Search.goToSystem(HarnessConfiguration.RHN_TEST_PREFIX));

	}

	public void unregisterAllProfilesOfSystem(String system, String username,
			String password) {
		task_RhnBase.OpenAndLogIn(username, password);
		unregisterAllProfilesOfSystem(system, false);

	}
	
	public void createCustomSystemInfo(String keyLabel, String keyDescription){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_CustomSystemInfo();
		if(rh.isTextNotPresent("No Custom Info Keys Found")){
			page_RhnCommon.setTxt_FilterBy(keyLabel);
			page_RhnCommon.clickButton_Filter_Go();
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
	
	protected String registerMultipleProfiles_part1(String system,
												String testSystemRootName,
												int numberOfSystems,
												String command,
												boolean deleteOld,
												boolean hosted, 
												boolean openAndLogin){
		String systemName =null;
		numberOfSystems = numberOfSystems + 1;
		Character firstLetter = new Character ('A');
		Character lastLetter = new Character ('A');
		Character endLetter = new Character ('Z');
		if (openAndLogin)
			task_RhnBase.OpenAndLogIn();
		for (int i = 1; i < numberOfSystems; i++) {
			systemName = testSystemRootName + "__"+ firstLetter + "00" + lastLetter + "00" + i;
			lastLetter++;
			if(lastLetter.compareTo(endLetter) == 0){
				lastLetter = 'A';
				firstLetter++;
			}
			
			task_Search.goToSystem(systemName);

			if (rh.isTextPresent(systemName) && (deleteOld == true)) {
				page_RhnCommon.clickLink_SystemName(system);
				page_SatelliteSystems.clickLink_DeleteSystem();
				page_SatelliteSystems.clickButton_DeleteProfile();
			}
			if (rh.isTextNotPresent(systemName) || (deleteOld == true)) {
				ssh.executeViaSSH(system, command);

				page_SatelliteSystems.open();
				page_SatelliteSystems.clickLink_AllSystems();
				page_RhnCommon.setTxt_FilterBy(system);
				page_RhnCommon.clickButton_Filter_Go();
				try {
					page_RhnCommon.clickLink_SystemName(system);
				} catch (SeleniumException se) {
					if (rh.isTextPresent("No systems."))
						log.severe("ERROR: check registrations, no systems found");
					page_RhnCommon.clickLink_SystemName(system);
				}
				page_SystemProvisioning.clickLink_EditSystemProperties();
				page_SystemProvisioning.setText_ProfileName(systemName);
				page_SystemProvisioning.clickButton_UpdateProperties();
			}

			page_SatelliteSystems.open();
			page_SatelliteSystems.clickLink_AllSystems();
		
		}
		return systemName;
	}


		public void registerMultipleProfiles(String system,
											String testSystemRootName,
											int numberOfSystems,
											String command,
											boolean deleteOld,
											boolean hosted, 
											boolean openAndLogin) {
			//Search quickSearch = HarnessConfiguration.search;
			String systemName = null;
			systemName = registerMultipleProfiles_part1(system,
														testSystemRootName, 
														numberOfSystems,
														command, 
														deleteOld,
														hosted,
														openAndLogin);
			//task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS,systemName,false,true);
			task_Search.goToSystemSDC(systemName);

	}
		

		
	public boolean register_check_activation_key(){
		boolean isPresent = false;
		//stub for space01, see space01.TestPrep
		
		return isPresent;
	}
	

	public void registerSystemWithActKey(String system,
										 String profileName,
										 String activationKey,
										 boolean successful){
		
		String regCommand = "rhnreg_ks " + " --activationkey=" + activationKey +
			" --serverUrl=http://" + HarnessConfiguration.RHN_HOST+ "/XMLRPC" + 
			" --profilename=" + system + " --force";
		
		removeSystemProfile(system);
		ssh.executeViaSSH(system, regCommand);
		checkRegistration(system, profileName, successful);
	}

	public void registerSystem(String system, String command, boolean successful, boolean openAndLogin) {
		String profileName = null;
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		removeSystemProfile(system);
		ssh.executeViaSSH(system, command);
		checkRegistration(system, profileName, successful);
		}
	
	public void registerSystemToSatellite(String system, boolean successful, boolean openAndLogin) {
		String profileName = null;
		String command = IRhnBase.RHN_SAT_REG_CMD;
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		removeSystemProfile(system);
		ssh.executeViaSSH(system, command);
		checkRegistration(system, profileName, successful);
		}
	

	public void registerSystemCustomUserName(String system, String command, String username,String password, boolean successful) {
		String profileName = null;
		task_RhnBase.OpenAndLogIn(username, password);
		removeSystemProfile(system);
		log.info("REGISTER COMMAND= " + command);
		ssh.executeViaSSH(system, command);
		checkRegistration(system, profileName, successful);
		
	}
	
	public void registerSystemCustom(String system,
									 String profileName,
									 String additionalCommand,
									 String username,
									 String password,
									 boolean successful,
									 boolean login) {
		if (login){
			task_RhnBase.OpenAndLogIn(username, password);
		}
		removeSystemProfile(system);
		String fullCommand = "rhnreg_ks --force --username="
				+ username + " --password=" + password + " "
				+ additionalCommand + " --profilename=" + profileName;
		ssh.executeViaSSH(system, fullCommand);
		checkRegistration(system, profileName, successful);
	}
	
	private void removeSystemProfile(String system){
		if(task_Search.goToSystem(system)){
			page_RhnCommon.clickLink_SystemName(system);
			page_SatelliteSystems.clickLink_DeleteSystem();
			page_SatelliteSystems.clickButton_DeleteProfile();
		}
		assertFalse(task_Search.goToSystem(system));
	}
	
	private void checkRegistration(String system, String profileName, boolean successful){
		String nameToCheck = (profileName==null) ? system:profileName;
		
		if (successful){
			Assert.assertTrue(task_Search.goToSystem(nameToCheck),"System '"+nameToCheck+"' is present");
			command_turn_On_Off_GPG_Check(system, true);
			command_updateSystemProfile(system);
		}
		else {
			Assert.assertFalse(task_Search.goToSystem(nameToCheck),"System '" +nameToCheck+"' is not present");
		}
	}
	
	public void changeProfileName(String originalName, String newName, boolean openAndLogin){
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();

		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(originalName);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(originalName);
		page_SystemProvisioning.clickLink_EditSystemProperties();
		page_SystemProvisioning.setText_ProfileName(newName);
		page_SystemProvisioning.clickButton_UpdateProperties();
	}

	public void registerSystemNOGUI(String system, String profileName,
			String additionalCommand, String username, String password) {
		String fullCommand = " rhnreg_ks --username="
				+ username + " --password=" + password
				+ " --serverUrl=http://" + IRhnBase.SERVER_HOSTNAME
				+ "/XMLRPC" + " " + additionalCommand + " --profilename="
				+ profileName;
		log.info("REGISTER COMMAND= " + fullCommand);
		ssh.executeViaSSH(system, fullCommand);
		command_turn_On_Off_GPG_Check(system, true);
	}

	/**
	 * Simply removes all system profiles
	 */
	public void removeAllSystemProfiles(boolean openAndLogin) {
		if (openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		if (rh.isTextNotPresent("No systems.")) {
			page_RhnCommon.clickButton_SelectAll_img();
			page_SSM.clickButton_Manage();
			page_SSM.clickLink_Delete();
			page_SSM.clickButton_ConfirmDeletions();
		}
		page_SatelliteSystems.open();
		Assert.assertTrue(rh.isTextPresent("No systems."));

	}

	/**
	 * Removes all system profiles of specified system
	 * @param system
	 */
	public void removeAllProfilesOfASystem(String system){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_AllSystems();
		
		// System must already have been deleted when "No systems." exist
		if (rh.isTextPresent("No systems.")) {return;}
		
		// filter for system and if present, delete it
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		if (rh.isTextPresent(system)) {
			page_RhnCommon.clickLink_SystemName(system);
			page_SatelliteSystems.clickLink_DeleteSystem();
			page_SatelliteSystems.clickButton_DeleteProfile();
			Assert.assertTrue(rh.isTextPresent("System profile deleted."),"System '"+system+"' was deleted.");
			
			// recurse to delete the next registration of this system
			this.removeAllProfilesOfASystem(system);
		}
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

	public void enableProvisioning(String system, boolean openSystemPage) {
		sdcEnableEntitlement(system, openSystemPage);
		page_SystemProvisioning.check_Provisioning();
		page_SystemProvisioning.clickButton_UpdateProperties();
	}
	
	public void fillInSystemLocation(String system, boolean openSystemPage) {
		sdcEnableEntitlement(system, openSystemPage);
		page_SDC.setTxt_FacilityAddress("1801 Varsity");
		page_SDC.setTxt_FacilityCity("Raleigh");
		page_SDC.setTxt_FacilityState("NC");
		page_SDC.setTxt_FacilityBuilding("Building1");
		page_SDC.setTxt_FacilityRoom("Room1");
		page_SDC.setTxt_FacilityRack("Rack1");
		page_SystemProvisioning.clickButton_UpdateProperties();
	}

	public void enableVirtualizationPlatform(String system,
			boolean openSystemPage) {
		sdcEnableEntitlement(system, openSystemPage);
		page_SystemProvisioning.check_VirtualizationPlatform();
		page_SystemProvisioning.clickButton_UpdateProperties();
		ssh.executeViaSSH(system, "rhn_check -vvvvv");
		String out = ssh.executeViaSSHWithReturn(system, "uname -a")[0];
		if(!out.contains("xen")){
			ssh.executeViaSSH(system, "yum install xen kernel-xen -y");
			ssh.executeViaSSH(system, "/usr/bin/perl -p -e 's/default=[0-9]/default=0/' -i /etc/grub.conf");
			ssh.executeViaSSH(system, "chkconfig xend on");
			ssh.executeViaSSH(system, "rhn_check -vvvvv");
			this.rebootSystem(system, false);
		}
	}
	
	

	public void enableMonitoringEntitlement(String system,
			boolean openSystemPage) {
		sdcEnableEntitlement(system, openSystemPage);
		page_SystemProvisioning.check_Monitoring();
		page_SystemProvisioning.clickButton_UpdateProperties();
	}

	private void step01ConfigManagement(String system, boolean hosted) {
		enableProvisioning(system, false);
		check_CfgMang_Status(system, hosted);
		Assert.assertFalse(check_CfgMang_Status(system, hosted));
		page_Configuration.select_Enable_CfgManagementTarget_Checkbox(system, hosted);
		page_Configuration.clickButton_EnableRHNConfigurationManagement();
	}

	public boolean check_CfgMang_Status(String system, boolean hosted) {
		boolean result;
		page_RhnCommon.clickConfiguration();
		page_Configuration.clickLink_Overview();
		page_Configuration.clickLink_EnableConfigMangOnSystems();

		try {
			page_RhnCommon.setTxt_FilterBy(system);
			page_RhnCommon.clickButton_Filter_Go();
			page_RhnCommon.check_FirstItemInList();
		} catch (SeleniumException se) {
			log.info("FILTER TXT BUG 232302");
		}
		log.info(system + " status ="
				+ page_Configuration.getText_ConfigMange_Status(system, hosted));
		if (page_Configuration.getText_ConfigMange_Status(system, hosted)
				.equalsIgnoreCase("No")) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public void changePaginationSettings(String entriesPerPage,boolean openAndLogin) {
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_YourRhn.open();
		page_YourRhn.clickLink_YourPreferences();
		page_YourRhn.select_NumberOfEntries(entriesPerPage);
		page_YourRhn.clickButton_SavePreferences();
		page_YourRhn.entriesPerPage = entriesPerPage;
	}
	
	public void changeLanguageSettingFromEnglish(boolean openAndLogin, String language) {
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_YourRhn.open();
		page_YourRhn.clickLink_LocalePreferences();
		page_YourRhn.checkBox_LanguageSetting(true, language);
		page_YourRhn.clickButton_SavePreferences();
	}

	public void changeLanguageSettingFromGerman(boolean openAndLogin, String language) {
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_YourRhn.open();
		page_YourRhn.clickLink_LocalePreferences();
		page_YourRhn.checkBox_LanguageSetting(true, language);
		page_YourRhn.clickButton_SavePreferencesInGerman();
	}
	
	
	
	public void command_runRhnCheckWithAT(String system, boolean showlogs) {
		if (HarnessConfiguration.RHN_OSAD_ENABLED == 0) {
			ssh.executeViaSSH(system,
					"echo \"rhn_check -vvv\" > /tmp/autoRHNCHECKCommand");
			ssh.executeViaSSH(system,
					"at now -f /tmp/autoRHNCHECKCommand");
			rh.sleepForSeconds(5);
			ssh.executeViaSSH(system, "ps -ef | grep rhn_check");
			//command_tailLog(system, "/var/log/up2date");
			log.info("RHN_CHECK executed");
		} else {
			log.info("OSAD is enabled, skipping rhn_check command");
		}
	}
	
	
	public void command_runCommandWithAT(String system,String command, boolean showlogs) {
		ssh.executeViaSSH(system,
				"echo \""+ command +"\" > /tmp/autoCommand");
		ssh.executeViaSSH(system,
				"at now -f /tmp/autoCommand");
		log.info("AT command executed");
	}

	public void command_migrateSystemProfile(String satellite, String systemId, String toOrgID ){
		String command = "migrate-system-profile --satellite "+satellite+" --systemId="+systemId+" --to-org-id="+toOrgID+ " -u "+IRhnBase.USER+" -p "+IRhnBase.PASSWORD;
		ssh.executeViaSSH(satellite,command);
	}
	
	public void command_cancelShutdown(String system, boolean showlogs) {
		if (HarnessConfiguration.RHN_OSAD_ENABLED == 0) {
			ssh.executeViaSSH(system, "shutdown -c");
		}
	}

	public void command_rebootSystem(String system, boolean showlogs) {
		if (HarnessConfiguration.RHN_OSAD_ENABLED == 0) {
			ssh.executeViaSSH(system, "reboot");
		}
	}

	public void command_runRhnCheckInBackGround(String system, boolean showlogs) {
		ssh.executeViaSSH(system, "ps -ef | grep rhn_check");
		ssh.executeViaSSH(system, "nohup /usr/sbin/rhn_check &");
	}

	public void command_runRhnCheckInForeground(String system, boolean showlogs) {
		ssh.executeViaSSH(system, "ps -ef | grep rhn_check");
		ssh.executeViaSSH(system, "/usr/sbin/rhn_check -vvv");
	}

	public void command_xmList(String system, boolean showlogs) {
		ssh.executeViaSSH(system, "xm list");
	}

	public void command_RemoveSSHKnownHosts(String system) {
		ssh.executeViaSSH(system, "rm -Rf ~/.ssh/known_hosts");
	}

	public String getRedHat_Release(String system) {
		String release = null;
		release = ssh.executeViaSSHWithReturn(system,
				"cat /etc/redhat-release")[0];
		return release;
	}

	private void command_RestartTomcat(String system) {
		ssh.executeViaSSH(system,
				"/bin/echo \"/etc/init.d/tomcat5 restart\" > /tmp/restartTomCat");
		ssh.executeViaSSH(system, "at now -f /tmp/restartTomCat");
		ssh.executeViaSSH(system, "/bin/touch /tmp/FINISHED_tomcat_restart");
	}
	
	private void command_RestartOracle(String system) {
		ssh.executeViaSSH(system,
				"/bin/echo \"/etc/init.d/oracle restart\" > /tmp/restartOracle");
		ssh.executeViaSSH(system, "at now -f /tmp/restartOracle");
		log.info("sleep 30 seconds while oracle restarts");
		task_TestPrep.sleepForSeconds(30);
		ssh.executeViaSSH(system, "/bin/touch /tmp/FINISHED_oracle_restart");
	}

	public void command_RestartSatellite(String system, boolean showlogs, boolean restartTomcatOnly, boolean restartOracle, boolean restartALL) {
		
		if(restartTomcatOnly){
			command_RestartTomcat(system);
			waitForRestart(system);
		}
		
		if(restartOracle) {
			command_RestartOracle(system);
			command_RestartTomcat(system);
			waitForRestart(system);
		}
		
		
	}
	
	private void waitForRestart(String system){
		for (int i = 0; i < 20; i++) {
			try {
				page_YourRhn.open();
			} catch (SeleniumException se) {
				log.info("Selenium may throw exception n Temporarily Unavailble");
			}
			if (rh.isTextPresent("Service Temporarily Unavailable"))
				break;
		}
		for (int i = 0; i < 240; i++) {
			try {
				page_YourRhn.openPub();
				rh.sleepForSeconds(30);
				page_YourRhn.open();
				} catch (SeleniumException se) {
					log.log(Level.SEVERE, "Selenium may throw exception n Temporarily Unavailble", se);
				}
				if (rh.isTextPresent("Service Temporarily Unavailable")){
					log.info("waiting for tomcat to restart");
					rh.sleepForSeconds(30);
				}
				
				if(rh.isTextPresent("Sign Out") && rh.isTextNotPresent("500 Error")) {
					log.fine("Found link for Sign Out, double checking");
					command_tailLog(system, "/var/log/tomcat5/catalina.out");
					rh.sleepForSeconds(30);
					page_YourRhn.openPub();
					page_YourRhn.open();
					if(rh.isTextPresent("Sign Out")) {
						command_tailLog(system, "/var/log/tomcat5/catalina.out");
						log.fine("SUCCESS: Tomcat restarted");
						break;
					}
				}
			}
	}

	public void command_generic(String command, String arguments,
			String system, boolean showlogs) {
		ssh.executeViaSSH(system, command + " " + arguments);
	}

	public void command_generic(String command, String arguments,
			String system, String user, boolean showlogs) {
		ssh.executeViaSSH(system, user, command + " " + arguments);
	}

	
	public void command_scp(String origFile, String destFile, 
			String system, String user, boolean showlogs) {
		scp.sendFile(system, user, origFile, destFile);
	}
	public void command_rpm(String arguments, String system, boolean showlogs) {
		ssh.executeViaSSH(system, "rpm " + arguments);
	}

	public void command_kill_Firefox_OnSeleniumServer() {
		ssh.executeViaSSH(HarnessConfiguration.SELENIUM_SERVER,
				"killall -9 firefox-bin");
		rh.sleepForSeconds(3);
	}

	public void command_up2date(String arguments, String system,
			boolean showlogs) {
		ssh.executeViaSSH(system,
				"up2date --nox " + arguments);
	}

	public void command_yum(String arguments, String system, boolean showlogs) {
		ssh.executeViaSSH(system,
				"yum " + arguments);
	}

	public void command_test(String command, String arguments, String system,
			boolean showlogs) {
		ExecCommands exec = new ExecCommands();
		try {
			exec.submitCommandToLocalWithReturn(true, command, arguments);
		} catch (IOException ioe) {
			log.log(Level.INFO, "command failed", ioe);
		}
	}

	public void command_remove_localKnownHosts(boolean showlogs) {
		ExecCommands exec = new ExecCommands();
		try {
			exec.submitCommandToLocalWithReturn(showlogs, "ls", ""
					+ System.getProperty("user.home") + "/.ssh/known_hosts");
			exec.submitCommandToLocalWithReturn(showlogs, "rm", "-f "
					+ System.getProperty("user.home") + "/.ssh/known_hosts");
			exec.submitCommandToLocalWithReturn(showlogs, "ls", ""
					+ System.getProperty("user.home") + "/.ssh/known_hosts");
			// exec.submitCommandToLocal(" ", " ");
		} catch (Exception e) {
			log.log(Level.INFO,"command failed", e);
		}
	}
	
	public void command_remove_localKnownHost(String hostname, boolean showlogs) {
		ExecCommands exec = new ExecCommands();
		try {
			exec.submitCommandToLocalWithReturn(showlogs, 
					"sed", "-i \"/" + hostname + "/d " +  
					System.getProperty("user.home") + "/.ssh/known_hosts");
		} catch (Exception e) {
			log.log(Level.INFO,"command failed", e);
		}
	}
	
	public void command_getSatelliteSSLCert(String Satellite_Hostname, String Client_Hostname){
		ssh.executeViaSSH(Client_Hostname, "rm -Rf /root/RHN-ORG-TRUSTED-SSL-CERT*");
		ssh.executeViaSSH(Client_Hostname, "wget http://"+Satellite_Hostname+"/pub/RHN-ORG-TRUSTED-SSL-CERT");
		ssh.executeViaSSH(Client_Hostname, "mv /root/RHN-ORG-TRUSTED-SSL-CERT /usr/share/rhn");
	}
	
	public void command_turn_On_Off_GPG_Check(String system, boolean showlogs) {
		if(HarnessConfiguration.OCTOKICK_SIGNED_PACKAGES == 0){
			ssh.executeViaSSH(system,
					"perl -p -e 's/useGPG=1/useGPG=0/' -i /etc/sysconfig/rhn/up2date");
			ssh.executeViaSSH(system,
					"perl -p -e 's/gpgcheck = 1/gpgcheck = 0/' -i /etc/yum/pluginconf.d/rhnplugin.conf");
			ssh.executeViaSSH(system,
					"perl -p -e 's/gpgcheck=1/gpgcheck=0/' -i /etc/yum.conf");
		}
		
		else if(HarnessConfiguration.OCTOKICK_SIGNED_PACKAGES == 1){
			ssh.executeViaSSH(system,
					"perl -p -e 's/useGPG=0/useGPG=1/' -i /etc/sysconfig/rhn/up2date");
			ssh.executeViaSSH(system,
					"perl -p -e 's/gpgcheck = 0/gpgcheck = 1/' -i /etc/yum/pluginconf.d/rhnplugin.conf");
			ssh.executeViaSSH(system,
					"perl -p -e 's/gpgcheck=0/gpgcheck=1/' -i /etc/yum.conf");
		}
		else{
			throw new SeleniumException(
					"please check your OCTOKICK_SIGNED_PACKES setting in localhost.properties, should be set to 1 or 0");
		}
	}
	
	public void command_Oracle_open_Listener_ora(String system, boolean showlogs) {
		ssh.executeViaSSH(system,	"perl -p -e 's/127.0.0.1/0.0.0.0/' -i /opt/apps/oracle/web/product/9.2.0/network/admin/listener.ora");
		ssh.executeViaSSH(system,	"perl -p -e 's/127.0.0.1/0.0.0.0/' -i /opt/apps/oracle/web/product/10.2.0/db_1/network/admin/listener.ora");
		log.info("requires oracle to be restarted");
	}

	// default=1

	public void command_GrubDefaultBoot0(String system, boolean showlogs) {
		ssh.executeViaSSH(system,
				"perl -p -e 's/default=1/default=0/' -i /etc/grub.conf");
	}

	public void command_importRPMKeys(String system, boolean showlogs) {
		ssh.executeViaSSH(system,
				"rpm  --import /usr/share/rhn/RPM-GPG-KEY");
		ssh.executeViaSSH(system,
				"rpm  --import /etc/pki/rpm-gpg/RPM-GPG-KEY-redhat-release");
		ssh.executeViaSSH(system,
				"rpm  --import /etc/pki/rpm-gpg/RPM-GPG-KEY-redhat-former");
	}

	public void getSatelliteUrls(String system, boolean showlogs) {
		ExecCommands exec = new ExecCommands();
		// String command = " grep \"\\<action path=\"
		// /var/lib/tomcat5/webapps/rhn/WEB-INF/struts-config.xml | awk -F\'\"\'
		// \'{print \"https://'HOSTNAME'/rhn\" $2 \".do\"}'| sort | uniq";
		String command = " \"\\<action path=\" "
				+ urlDir
				+ "struts-config.xml | awk -F\'\"\' \'{print \"https://'HOSTNAME'/rhn\" $2 \".do\"}'| sort | uniq";
		// String command1 =" \"\\<action path=\"
		// /var/tmp/rhn/src/main/resources/struts-config.xml | awk -F\'\"\'
		// \'{print \"https://'HOSTNAME'/rhn\" $2 \".do\"}'| sort | uniq >
		// /var/tmp/rhn/src/main/resources/sat.out";
		String command2 = urlDir + "getUrl01.sh";
		String command3 = urlDir + "getUrl02.sh";
		log.info("command = " + command);
		try {
			exec.submitCommandToLocalWithReturn(showlogs, "scp", "root@"
					+ system + ":" + urlxml + " " + urlDir);
			exec.submitCommandToLocalWithReturn(showlogs, command2, "");
			exec.submitCommandToLocalWithReturn(showlogs, command3, "");

		} catch (IOException ioe) {
			log.log(Level.INFO, "command failed", ioe);
		}
	}
	
	public void rebootSystem(String system, boolean checkKickstart){
		ssh.executeViaSSH(system, "reboot");
		while(!okick.checkIfSSHOpen(system, checkKickstart))
			this.sleepForSeconds(20);
	}

	public boolean command_rpmQuery(String system, String rpm) {
		boolean rpmInstalled = false;
		String[] std = ssh.executeViaSSHWithReturn(system,"rpm -q "+rpm);
		String stdout = std[0].trim();
		log.info("result = '"+stdout+"'");
		rpmInstalled = (stdout.matches(rpm + ".*"));
		return rpmInstalled;
	}

	public void command_rpmCommandWithReturn(String system, String rpmCmd) {
		String result = "";
		result = ssh.executeViaSSHWithReturn(system,
				rpmCmd)[0];
		log.info("result = " + result);
	}

	public int checkRHELVersion(String system) {
		int release = 0;
		String result = "";
		String x = "";
		result = ssh.executeViaSSHWithReturn(system,
				"cat /etc/redhat-release")[0];
		log.info("result = " + result);
		if (result.matches("Red Hat Enterprise Linux Server release 5"
				+ ".*"))
			release = 5;
		else
			release = 4;
		return release;
	}

	public void disablePkgSkipListForUp2date(String system, boolean showlogs) {
		int n = 100;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		ssh.executeViaSSH(system,
				"cat /etc/sysconfig/rhn/up2date | " +
				"grep -v SkipList > /etc/sysconfig/rhn/up2date.new");
		ssh.executeViaSSH(system,
				"mv -f /etc/sysconfig/rhn/up2date /etc/sysconfig/rhn/up2date.orig" +
				rand);
		ssh.executeViaSSH(system,
				"mv -f /etc/sysconfig/rhn/up2date.new /etc/sysconfig/rhn/up2date");
		ssh.executeViaSSH(system,
				"echo \"pkgSkipList=;\" >> /etc/sysconfig/rhn/up2date");
		ssh.executeViaSSH(system,
		"echo \"removeSkipList=;\" >> /etc/sysconfig/rhn/up2date");
				
			// exec.submitCommandToLocalWithReturn(showlogs, "ssh",
			// "root@"+system +" perl -npe
			// 's/pkgSkipList=kernel*;/pkgSkipList=;/g' -i
			// /etc/sysconfig/rhn/up2date");
	}

	public void disableYumGPGCheck(String system, boolean showlogs) {
		int n = 100;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		ssh.executeViaSSH(system,
				"cat /etc/yum/pluginconf.d/rhnplugin.conf | " +
				"grep -v gpgcheck > /etc/yum/pluginconf.d/rhnplugin.conf.new");
		ssh.executeViaSSH(system,
				"mv -f /etc/yum/pluginconf.d/rhnplugin.conf /etc/yum/pluginconf.d/rhnplugin.conf" +
				rand);
		ssh.executeViaSSH(system,
				"mv -f /etc/yum/pluginconf.d/rhnplugin.conf.new /etc/yum/pluginconf.d/rhnplugin.conf");
		ssh.executeViaSSH(system,
				"echo \"gpgcheck = 0\" >> /etc/yum/pluginconf.d/rhnplugin.conf");
	}

	public void modifyServerParent(String system, boolean showlogs, String value) {
		int n = 100;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		ssh.executeViaSSH(system,
				"cat /etc/sysconfig/rhn/up2date | grep -v serverURL= > /etc/sysconfig/rhn/up2date.new");
		ssh.executeViaSSH(system,
				" mv -f /etc/sysconfig/rhn/up2date /etc/sysconfig/rhn/up2date.orig"+
				rand);
		ssh.executeViaSSH(system,
				"mv -f /etc/sysconfig/rhn/up2date.new /etc/sysconfig/rhn/up2date");
		ssh.executeViaSSH(system,
				" echo \"" + value
					+ " \" >> /etc/sysconfig/rhn/up2date");
	}

	public void command_tailLog(String system, String logName) {
		ssh.executeViaSSH(system,
				"tail " + logName);
	}
	
	public void command_clear_SELinux_AuditLog(String system) {
		if(HarnessConfiguration.RHN_SELINUX.equalsIgnoreCase("1")){
		
	   ssh.executeViaSSHWithReturn(IRhnBase.SERVER_HOSTNAME,"cat /dev/null > /var/log/audit/audit.log", Level.FINER);
		}
		else{
			log.finer("SELinux testing is off");
		}
	}
	
	public boolean command_check_SELinux_AuditLog(String system) {
		boolean cleanLogFile=true;
		if(HarnessConfiguration.RHN_SELINUX.equalsIgnoreCase("1")){
					
			String result2[] = ssh.executeViaSSHWithReturn(IRhnBase.SERVER_HOSTNAME, 
					"grep 'avc:  denied' /var/log/audit/audit.log", Level.FINE);
			
			for(int i=0; i < result2.length; i++){
				//log.info("out ="+result1[i]);
				if(result2[i].contains("denied")){
					log.info("found SELinux denial, failed");
					cleanLogFile = false;
					throw new SeleniumException("SELinux denial found, test fails");
				}
			}
		}
		return cleanLogFile;
	}
	
	/**
	 * cats a file and compares the results w/ the expected results
	 * @param system
	 * @param file
	 * @param expectedResult (can be a java regular expression)
	 */
	public void command_check_fileContents(String system,String file, String expectedResult) {
			String result="";
			result = ssh.executeViaSSHWithReturn(system,"cat "+file)[0];
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(expectedResult));
	}
	
	/**
	 * returns the major level of a RHEL box, ie.. 5,4,3,2
	 * @param system
	 * @return an int
	 */
	public int command_check_RHEL_Version(String system) {
			int majorRelease = 0;
			String result2 = ssh.executeViaSSHWithReturn(system,"cat /etc/redhat-release")[0].trim();
			log.info("result2 ="+result2);

				if(result2.matches("Red Hat Enterprise Linux Server release 5.*")){
					majorRelease = 5;
			
				}
				else if(result2.matches("Red Hat Enterprise Linux AS release 4.*")){
					majorRelease = 4;
		
				}
				else if(result2.matches(".*release 3")){
					majorRelease = 3;
				
				}
				else if(result2.matches(".*Server release 2.*")){
					majorRelease = 2;
				}
				
				else {
					log.info("version not found");
					majorRelease = 0;
				}
			log.info("RHEL major release =" +majorRelease);
			return majorRelease;
	}
	
	public boolean command_start_RHEL_Service(String system, String service){
		boolean startedSuccessfully = false;
		String result = ssh.executeViaSSHWithReturn(system,"service "+service+" start")[0].trim();
		log.info("result ="+result);
		
		if(result.contains("OK")){
			startedSuccessfully = true;
		}
		if(result.contains("failed")){
			startedSuccessfully = false;
		}
		
		return startedSuccessfully;
	}
	
	public void screenShot(){
		String screenShotPath = null;
		InetAddress addr = null;
		String localhost = null;
		String selServer = null; //the selenium server 
		String host = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (java.net.UnknownHostException e1) {
			e1.printStackTrace();
		}
		localhost = addr.getHostName();
		selServer = HarnessConfiguration.SELENIUM_SERVER;
		
		if(!localhost.equalsIgnoreCase(selServer)){
			host = selServer;
		}
		else{
			host = localhost;
		}
		try{
			screenShotPath = sel.screenCapture("/var/www/html/screenshots").trim();  //this is the full path w/ name
			//log.log(Level.FINE, "Success: capture screenshot success");
		}
		catch(Exception screencapture){
				log.log(Level.FINE, "capture screenshot failed",screencapture);
		}
		try{
			String dirName = "/var/www/html/screenshots";//System.getProperty("user.dir") + File.separator+ "screenshots";
			log.info("Captured ScreenShot to http://"+host+"/screenshots"+screenShotPath.substring(dirName.length()));
			}
			catch(Exception e){
				String dirName = "/tmp";
				log.info("Captured ScreenShot to http://"+host+"/screenshots"+screenShotPath.substring(dirName.length()));
				//log.log(Level.FINE, "moving the screenshot failed, screenshot may be found @ "+screenShotPath);
			}			
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
		ssh.executeViaSSH(system, "rhn-actions-control --enable-all");
	}
	
	public void sleepForSeconds(int seconds) {
		log.info("Sleeping for " + seconds + " seconds...");
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
		}
	}
	
	public void verifySystemProfile(String system){
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_SystemName(system);
		
	}
	

	public void command_change_webForceUnentitlement(String system,boolean turnOn, boolean showlogs){
		throw new SeleniumException("wrong version of method");
	}
	
	public void enableRHNConfigManagNoAsserts(String system, boolean hosted,	boolean openAndLogin) {
		throw new SeleniumException("wrong version of method");
	}
	
	

}
