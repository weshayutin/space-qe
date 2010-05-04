package com.redhat.rhn.harness.common.Sat50.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat50.pages.ActivationKeysPage;
import com.redhat.rhn.harness.Sat50.pages.ChannelsPage;
import com.redhat.rhn.harness.Sat50.pages.RhnCommon;
import com.redhat.rhn.harness.Sat50.pages.SDCPage;
import com.redhat.rhn.harness.Sat50.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat50.pages.SatelliteToolsPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat50.tasks.ActivationKeys;
import com.redhat.rhn.harness.common.Sat50.tasks.Channels;
import com.redhat.rhn.harness.common.Sat50.tasks.RhnBase;
import com.redhat.rhn.harness.common.Sat50.tasks.Sdc;
import com.redhat.rhn.harness.common.Sat50.tasks.Search;


/**
 * These are some basic Solaris functionality tests.  They're designed to be run
 * upon the following environment:
 * - RHN Satellite >= 420
 * - SPARC or x86 machines or a mixture running Solaris 8,9,10
 * - passwordless rshd must be enabled for the root user on each Solaris box
 * @author ssalevan
 */
public class Solaris extends com.redhat.rhn.harness.common.Sat42.tasks.Solaris {

	/*
	*//**
	 * Sends a file to a remote machine via RCP
	 * @param hostname hostname of remote machine
	 * @param fileloc location of local file
	 * @param destination destination on remote machine
	 * @return stdout/stderr from command
	 *//*
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
	
	*//**
	 * Enables Solaris support on an RHN Satellite and
	 * verifies that the enabling occurred
	 *//*
	public void enableSolarisSupport(){
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		sattpage.check_EnableSolarisSupport(true);
		rb.restartSatellite();
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		assertTrue(sel.isChecked("name=web|enable_solaris_support"));
	}
	
	*//**
	 * Disables Solaris support on an RHN Satellite and
	 * verifies that the disabling occurred
	 *//*
	public void disableSolarisSupport(){
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		sattpage.check_EnableSolarisSupport(false);
		rb.restartSatellite();
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		assertFalse(sel.isChecked("name=web|enable_solaris_support"));
	}
	
	*//**
	 * Prepares a Solaris machine to run RHN-related commands through
	 * Brandon's magical Solaris preparation script
	 * NOTE: The machine must have the rshd service enabled and users
	 * must be able to log in without a password to the root account.
	 * To learn how to accomplish this task, refer to Satellite wiki
	 * @param hostname hostname of Solaris machine
	 *//*
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
	
	*//**
	 * Registers a Solaris machine to an RHN Satellite
	 * @param hostname hostname of Solaris machine
	 *//*
	public void registerSolarisSystem(String hostname,
								  	  String activationKey,
								  	  String channel){
		this.prepareSystem(hostname,
				           activationKey,
				           channel);
		assertTrue(rb.isSystemActivated(hostname, activationKey));
	}
	
	*//**
	 * Creates an activation key for a Solaris box, given the name
	 * of a base channel
	 * @param channel name of base channel
	 * @return string containing activation key number
	 *//*
	public String createSolarisActivationKey(String channel){
		String activationKey = "" + Math.abs(rand.nextInt());
		String name = channel;
		
		//the only applicable add-on entitlement for a Solaris box is
		//Provisioning, hence the hard-coding of the following boolean values
		
		ak.createActivationKeyWithBaseChannel(name, 
				                              activationKey,
				                              channel,
				                              "",
				                              false,
				                              true,
				                              false,
				                              false,
				                              false);
		rb.setTxt_FilterBy(channel);
		rb.clickButton_Filter_Go();
		activationKey = rh.getTableData("xpath=/html/body/div/div[5]/table/tbody/tr/td[2]/form/table", channel, 3);
		return activationKey;
	}
	
	*//**
	 * Pushes an individual patch or cluster from a Solaris
	 * machine into a channel on an RHN Satellite
	 * @param hostname hostname of Solaris machine
	 * @param patchloc location of patch on Solaris machine
	 * @param channel name of channel to push patch into
	 *//*
	public ArrayList<String> pushSolarisPatch(String hostname,
							     			  String patchloc,
							     			  String patchname,
							     			  String channel){
		ArrayList<String> filenames = rb.rhnPushPackageToChannelViaRsh(hostname,
							   			 							   patchloc,
							   			 							   patchname,
							   			 							   channel);
		for (int i=0; i<filenames.size(); i++)
			rb.verifyPatchInChannel(channel,
							  	  	filenames.get(i));
		return filenames;
	}
	
	*//**
	 * Runs the 'showrev -p' command on a Solaris machine and
	 * checks for the presence of the patch ID number supplied
	 * @param hostname hostname of Solaris machine
	 * @param patchname patch ID number
	 * @return boolean value denoting presence of patch on system
	 *//*
	protected boolean patchIsInstalled(String hostname,
									   String patchname){
		String output = this.rshCommand(hostname, 
										"showrev -p");
		return output.contains(patchname);
	}
	
	*//**
	 * Applies a patch to a Solaris machine residing on an
	 * RHN Satellite
	 * Code cheerfully ganked from Wes' SdcSoftware class
	 * @param hostname hostname of Solaris machine
	 * @param patch name of patch
	 *//*
	public void applySolarisPatch(String hostname,
							      String patchname){
		syspage.open();
		qs.goToSystem(hostname);
		rc.clickLink_GeneralLink(hostname);
		sdc.clickLink_Software();
		sdc.clickLink_Packages();
		sdc.clickLink_Install();
		sdc.setTxt_SDCFilterBy(patchname);
		sdc.clickButton_FilterGo();
		rc.check_FirstItemInList();
		sdc.clickButton_InstallSelectedPackages();
		sdc.clickButton_Confirm();
		//apply patch
		this.rshCommand(hostname, "rhn_check -vvvvv");
		assertTrue(this.patchIsInstalled(hostname, patchname));
	}*/
}
