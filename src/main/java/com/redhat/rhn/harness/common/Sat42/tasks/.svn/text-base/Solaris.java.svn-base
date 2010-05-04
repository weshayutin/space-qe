package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.SolUnit;
import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class Solaris extends SeleniumSetup{
	//tasks
	protected RhnHelper rh = new RhnHelper();
	protected Random rand = new Random();
	
	/**
	 * Submits a command to a remote machine via RSH
	 * To enable rshd on a Solaris machine, refer to Satellite wiki
	 * @param hostname hostname of remote machine
	 * @param command command to run
	 * @return stdout/stderr string from command
	 */
	protected String rshCommand(String hostname,
			                    String command){
		log.info("Executing the following command via RSH: " + command);
		ExecCommands exec = new ExecCommands();
		String output = "";
		try{
			output = exec.submitCommandToLocalWithReturn(true, "rsh -l root "+hostname, command);
		}
		catch (IOException ioe) {
			log.info("RSH command failed!");
		}
		return output;
	}
	
	/**
	 * Sends a file to a remote machine via RCP
	 * @param hostname hostname of remote machine
	 * @param fileloc location of local file
	 * @param destination destination on remote machine
	 * @return stdout/stderr from command
	 */
	protected String rcpFile(String hostname,
			                 String fileloc,
			                 String destination){
		log.info("Copying over the following file via RCP: "+fileloc);
		log.info("To the following destination: "+destination);
		ExecCommands exec = new ExecCommands();
		String output = "";
		try{
			output = exec.submitCommandToLocalWithReturn(true, "rcp "+fileloc, "root@"+hostname+":"+destination);
		}
		catch (IOException ioe) {
			log.info("RCP command failed!");
		}
		return output;
	}
	
	/**
	 * Enables Solaris support on an RHN Satellite and
	 * verifies that the enabling occurred
	 */
	public void enableSolarisSupport(){
		page_RhnCommon.clickSatelliteTools();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		page_SatelliteTools.check_EnableSolarisSupport(true);
		page_SatelliteTools.clickButton_Update();
		task_SatelliteTools.restartSatellite();
		
		page_RhnCommon.clickSatelliteTools();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		assertTrue(sel.isChecked("name=web|enable_solaris_support"));
	}
	
	/**
	 * Disables Solaris support on an RHN Satellite and
	 * verifies that the disabling occurred
	 */
	public void disableSolarisSupport(){
		page_RhnCommon.clickSatelliteTools();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		page_SatelliteTools.check_EnableSolarisSupport(false);
		task_SatelliteTools.restartSatellite();
		page_RhnCommon.clickSatelliteTools();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		assertFalse(sel.isChecked("name=web|enable_solaris_support"));
	}
	
	/**
	 * Prepares a Solaris machine to run RHN-related commands through
	 * Brandon's magical Solaris preparation script
	 * NOTE: The machine must have the rshd service enabled and users
	 * must be able to log in without a password to the root account.
	 * To learn how to accomplish this task, refer to Satellite wiki
	 * @param hostname hostname of Solaris machine
	 */
	protected void prepareSystem(String hostname,
								 String activationKey,
								 String channel){
		this.rcpFile(hostname,
				     System.getProperty("user.dir", "") + "/src/main/resources/rhn_unix_setup.sh",
				     "/");
		this.rcpFile(hostname,
			     System.getProperty("user.dir", "") + "/src/main/resources/yes",
			     "/usr/bin");
		this.rshCommand(hostname,
				        String.format("sh /rhn_unix_setup.sh %s %s %s %s %s",
				        			  IRhnBase.SERVER_HOSTNAME,
				        			  IRhnBase.USER,
				        			  activationKey,
				        			  IRhnBase.PASSWORD,
				        			  channel));
	}
	
	/**
	 * Registers a Solaris machine to an RHN Satellite
	 * @param hostname hostname of Solaris machine
	 */
	public void registerSolarisSystem(String hostname,
								  	  String activationKey,
								  	  String channel){
		task_TestPrep.removeAllProfilesOfASystem(hostname);
		this.prepareSystem(hostname,
				           activationKey,
				           channel);
		//assertTrue(sat.isSystemActivated(hostname, activationKey));
	}
	
	public void createSolarisConfigChannel(String channelName){
		task_RhnBase.DeleteConfigChannel(channelName,
										 true);
		task_ConfigMang.CreateNewConfigChannel(channelName,
											   true,
											   false,
											   true);
	}
	
	public void enableSolarisConfigMgmt(String hostname){
		ExecCommands exec = new ExecCommands();
		exec.rcpAndExecuteViaRsh(hostname,
				"rhn-actions-control --enable-all");
	}
	
	public void pushFileToSolarisCfgChannel(String hostname,
											String channelName,
											String fileLoc){
		ExecCommands exec = new ExecCommands();
		exec.rcpAndExecuteViaRsh(hostname,
				"rhncfg-manager add -c"+channelName+
				" " + fileLoc);
	}
	
	public boolean testSolarisConfigMgmt(String hostname){
		ExecCommands exec = new ExecCommands();
		this.createSolarisConfigChannel(hostname);
		this.enableSolarisConfigMgmt(hostname);
		task_ConfigMang.subscribeSystemToChannel(hostname,
				hostname);
		this.pushFileToSolarisCfgChannel(hostname,
										 hostname,
										 "/etc/motd");
		this.rshCommand(hostname,
				"echo \"NOTMANAGED\" >> /etc/motd");
		task_ConfigMang.sdcScheduleDeployAllFiles(hostname);
		exec.rcpAndExecuteViaRsh(hostname,
				"rhn_check -vvvvv");
		String motd = this.rshCommand(hostname,
				"echo /etc/motd");
		assertFalse(motd.contains("NOTMANAGED"));
		return true;
	}
	
	/**
	 * Creates an activation key for a Solaris box, given the name
	 * of a base channel
	 * @param channel name of base channel
	 * @return string containing activation key number
	 */
	public String createSolarisActivationKey(String channel){
		String activationKey = "" + Math.abs(rand.nextInt());
		String name = channel;
		
		//the only applicable add-on entitlement for a Solaris box is
		//Provisioning, hence the hard-coding of the following boolean values
		
		task_ActivationKeys.createActivationKeyWithBaseChannel(name, 
				                              activationKey,
				                              channel,
				                              "",
				                              false,
				                              true,
				                              false,
				                              false,
				                              false);
		page_ActivationKeys.open();
		page_RhnCommon.setTxt_FilterBy(channel);
		page_RhnCommon.clickButton_Filter_Go();
		activationKey = task_ActivationKeys.getAKFromKeyName(channel);
		//activationKey = rh.getTableData("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table", channel, 3);                      
		//activationKey = rh.getTableData("xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/form/table[2]", channel, 3);
		return activationKey;
	}
	
	public boolean testHardwareProfile(SolUnit su){
		ExecCommands exec = new ExecCommands();
		String hostname = su.getHostname();
		
		this.rshCommand(hostname,
			"hostname test.com");
		exec.rcpAndExecuteViaRsh(hostname,
			"up2date --hardware");
		
		page_SatelliteSystems.open();
		task_Search.goToSystem(hostname);
		page_RhnCommon.clickLink_GeneralLink(hostname);
		page_SDC.clickLink_Details();
		page_SDC.clickLink_Hardware();
		assertTrue(rh.isTextPresent("test.com"));
		
		this.rshCommand(hostname,
			"hostname " + hostname);
		exec.rcpAndExecuteViaRsh(hostname,
			"up2date --hardware");
		
		page_SatelliteSystems.open();
		task_Search.goToSystem(hostname);
		page_RhnCommon.clickLink_GeneralLink(hostname);
		page_SDC.clickLink_Details();
		page_SDC.clickLink_Hardware();
		assertTrue(rh.isTextPresent(hostname));
		
		return true;
	}
	
	public boolean testUp2dateQuirks(SolUnit su){
		ExecCommands exec = new ExecCommands();
		String version = exec.rcpAndExecuteViaRsh(su.getHostname(),
				"up2date --version");
		assertTrue(version.contains("up2date"));
		String help = exec.rcpAndExecuteViaRsh(su.getHostname(),
				"up2date --help");
		assertTrue(help.contains("package"));
		String channels = exec.rcpAndExecuteViaRsh(su.getHostname(),
				"up2date --show-channels");
		assertTrue(channels.contains(su.getCustomChannel()));
		assertTrue(channels.contains(su.getCustomChildChannel()));
		
		return true;
	}
	
	public void testUp2datePackages(SolUnit su) {
		ExecCommands exec = new ExecCommands();
		String all = exec.rcpAndExecuteViaRsh(su.getHostname(),
			"up2date --showall");
		
		for (String pkg: su.getClustFNs()){
			assertTrue(all.contains(pkg));
			grabRawPackageFromUp2date(su.getHostname(),
					pkg);
		}
		for (String pkg: su.getPatchFNs()){
			assertTrue(all.contains(pkg));
			grabRawPackageFromUp2date(su.getHostname(),
					pkg);
		}
	}
	
	private void grabRawPackageFromUp2date(String hostname,
			String pkg){
		ExecCommands exec = new ExecCommands();
		exec.rcpAndExecuteViaRsh(hostname,
			"up2date -v --get " + pkg);
		String dir = this.rshCommand(hostname,
			"ls");
		assertTrue(dir.contains(pkg));
	}
	
	
	/**
	 * Pushes an individual patch or cluster from a Solaris
	 * machine into a channel on an RHN Satellite
	 * @param hostname hostname of Solaris machine
	 * @param patchloc location of patch on Solaris machine
	 * @param channel name of channel to push patch into
	 */
	public ArrayList<String> pushSolarisPatch(String hostname,
							     			  String patchloc,
							     			  String patchname,
							     			  String channel){
		ArrayList<String> filenames = task_Channels.rhnPushPackageToChannelViaRsh(hostname,
							   			 							   patchloc,
							   			 							   patchname,
							   			 							   channel);
		for (int i=0; i<filenames.size(); i++)
			task_RhnBase.verifyPatchInChannel(channel,
							  	  			  filenames.get(i));
		return filenames;
	}

	/**
	 * Runs the 'showrev -p' command on a Solaris machine and
	 * checks for the presence of the patch ID number supplied
	 * @param hostname hostname of Solaris machine
	 * @param patchname patch ID number
	 * @return boolean value denoting presence of patch on system
	 */
	protected boolean patchIsInstalled(String hostname,
									   String patchname){
		String output = this.rshCommand(hostname, 
										"showrev -p");
		return output.contains(patchname);
	}
	
	/**
	 * Applies a patch to a Solaris machine residing on an
	 * RHN Satellite
	 * Code cheerfully ganked from Wes' SdcSoftware class
	 * @param hostname hostname of Solaris machine
	 * @param patch name of patch
	 */
	public void applySolarisPatch(String hostname,
							      String patchname){
		ExecCommands exec = new ExecCommands();
		page_SatelliteSystems.open();
		task_Search.goToSystem(hostname);
		page_RhnCommon.clickLink_GeneralLink(hostname);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Install();
		page_SDC.setTxt_SDCFilterBy(patchname);
		page_SDC.clickButton_FilterGo();
		page_RhnCommon.check_FirstItemInList();
		page_SDC.clickButton_InstallSelectedPackages();
		page_SDC.clickButton_Confirm();
		//apply patch
		exec.rcpAndExecuteViaRsh(hostname, "rhn_check -vvvvv");
		assertTrue(this.patchIsInstalled(hostname, patchname));
	}
}
