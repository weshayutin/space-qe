package com.redhat.rhn.harness.common.Space01.tasks;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import org.testng.Assert;

import com.redhat.qe.auto.selenium.MyLevel;
import com.redhat.rhn.harness.Space01.pages.KickStartPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.qe.tools.ExecCommands;

public class KickStart extends com.redhat.rhn.harness.common.Sat52.tasks.KickStart {
	//Redefining the following variables, as new functionality added
	KickStartPage page_KickStart = (KickStartPage)instantiator.getVersionedInstance(KickStartPage.class);
	
	public final String customKSMarker = "/tmp/automation-custom-ks-complete";
	
	public boolean checkForActiveStatus(String profile){
		page_KickStart.open();
		page_KickStart.clickLink_Profiles();
		page_RhnCommon.setTxt_FilterBy(profile);
		page_RhnCommon.clickButton_Filter_Go();
		return page_KickStart.get_ProfileActiveStatus();
	}
	
	/**
	 * Subscribes a system to its respective RHN Tools channel by way of enabling
	 * configuration management, which automatically performs this task
	 * @param system
	 * @return completion status
	 */
	public boolean subscribeToRhnTools(String system){
		if (task_TestPrep.check_CfgMang_Status(system, false))
			return true;
		page_Configuration.open();
		page_Configuration.clickLink_EnableConfigMangOnSystems();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		//page_Configuration.select_Enable_CfgManagementTarget_Checkbox(system, false);
		page_Configuration.clickButton_EnableRHNConfigurationManagement();
		return true;
	}
	
	public boolean checkForRHNManagedStatus(String profile){
		page_KickStart.open();
		page_KickStart.clickLink_Profiles();
		page_RhnCommon.setTxt_FilterBy(profile);
		page_RhnCommon.clickButton_Filter_Go();
		return page_KickStart.get_RHNSatelliteManagedStatus();
	}
	
	public boolean verifyProfileExistence(String profile){
		page_KickStart.open();
		page_KickStart.clickLink_Profiles();
		page_RhnCommon.setTxt_FilterBy(profile);
		page_RhnCommon.clickButton_Filter_Go();
		return rh.isTextPresent(profile);
	}
	
	public boolean addHelperScriptsToKickstart(String profileName,
											   String sshKey){
		page_KickStart.open();
		page_KickStart.clickLink_Profiles();
		page_RhnCommon.setTxt_FilterBy(profileName);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.clickLink_GeneralLink(profileName);
		okick.addScriptsToKickstartProfile(sshKey);
		return true;
	}
	
	public boolean addCustomScriptToKickstart(String profileName, String interperter, String customScript){
		page_KickStart.open();
		page_KickStart.clickLink_Profiles();
		page_RhnCommon.setTxt_FilterBy(profileName);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.clickLink_GeneralLink(profileName);
		okick.addCustomScriptToKickstartProfile(interperter, customScript);
		return true;
	}
	
	public void setChildChannelInKickstart(String profileName, boolean check) {
		page_KickStart.open();
		page_KickStart.clickLink_Profiles();
		page_RhnCommon.setTxt_FilterBy(profileName);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.clickLink_GeneralLink(profileName);
		page_KickStart.clickLink_OperatingSystem();
		okick.setChildChannelInKickstartProfile(check);
	}
	
	public void enableRHNToolsAccessInKickstart(String profileName,
			String activationKey){
		// Custom distro doesn't have access to rhn-tools, so we need to copy
		// them over and install them at the end of the kickstart
		String customScript = "mkdir /tmp/custom-tools-channel; " + 
			"cd /tmp/custom-tools-channel; " + 
			"wget -r -l 1 -nd http://" + 
			HarnessConfiguration.RHN_HOST + "/" + 
			task_RhnBase.NEVRA_CUSTOM_TOOLS_PACKAGES_URL + ";" +
			"rpm --nosig -Uvh *.rpm; rhnreg_ks --serverUrl=http://" + 
			HarnessConfiguration.RHN_HOST + 
			"/XMLRPC --activationkey=" + activationKey + " --force";
		this.addCustomScriptToKickstart(profileName,
				"/bin/bash",
				customScript);
		// Add a marker we can check for
		customScript = "touch " + customKSMarker;
		this.addCustomScriptToKickstart(profileName,
				"/bin/bash",
				customScript);
	}
	
	public boolean createNewKickstartProfile(String profileName,
											 String baseChannel,
											 String kickstartableTree,
											 String virtualizationType,
											 String rootPassword,
											 String sshKey){
		log.info("createNewKickstartProfile(" + profileName + ", " + baseChannel +
				", " + kickstartableTree + ", " + virtualizationType + ", " + 
				rootPassword + ", " + sshKey);
		page_KickStart.open();
		page_KickStart.clickLink_CreateNewKickstartProfile();
		page_KickStart.setTxt_KickstartProfileLabel(profileName);
		page_KickStart.select_BaseChannel(baseChannel, true);
		page_KickStart.select_KickstartableTree(kickstartableTree);
		page_KickStart.select_VirtualizationType(virtualizationType);
		page_KickStart.clickButton_Next();
		page_KickStart.clickButton_Next();
		page_KickStart.setTxt_RootPassword(rootPassword);
		page_KickStart.setTxt_RootPasswordConfirm(rootPassword);
		page_KickStart.clickButton_Finish();
		assertTrue(this.verifyProfileExistence(profileName));
		assertTrue(this.checkForActiveStatus(profileName));
		assertTrue(this.addHelperScriptsToKickstart(profileName, sshKey));
		//assertTrue(this.checkForRHNManagedStatus(profileName));
		return true;
	}
	
	public boolean uploadKickstartFile(String profileName,
									   String fileLocation,
									   String virtualizationType,
									   String kickstartableTree){
		String fileContents = "";
		
		//read in kickstart template using Java's awkward file handlers
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileLocation));
			String ksLine = br.readLine();
			while (ksLine != null){
				fileContents += ksLine + '\n';
				ksLine = br.readLine();
			}
		}
		catch(Exception e){
			log.log(MyLevel.INFO, "Error in reading KS file:", e);
			return false;
		}
		
		//add public key into kickstart file template
		String sshKey = task_RhnBase.SSH_PUBLIC_KEY;
		fileContents += "\n%post\n" +
				"mkdir /root/.ssh; " +
				"echo \""+sshKey+"\" >> /root/.ssh/authorized_keys\n";
		
		//create KS profile based off of file
		page_KickStart.open();
		page_KickStart.clickLink_UploadNewKickstartFile();
		page_KickStart.setTxt_KickstartProfileLabel(profileName);
		page_KickStart.select_KickstartableTree(kickstartableTree);
		page_KickStart.select_VirtualizationType(virtualizationType);
		page_KickStart.setTxt_FileContents(fileContents);
		page_KickStart.clickButton_Create();
		assertTrue(this.verifyProfileExistence(profileName));
		return true;
	}
	
	public boolean bareMetalKSFromKoan(String system, String profile){
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		rh.clickSystemProfileLink(system);
		page_SDC.clickLink_Provisioning();
		page_RhnCommon.setTxt_FilterBy(profile);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInListSDC();
		String cobbler_prof = page_KickStart.clickButton_CreateCobblerSystemRecord();
		ssh.executeViaSSH(system, "yum install spacewalk-koan -y");
		ssh.executeViaSSH(system, "up2date spacewalk-koan");
		ssh.executeViaSSH(system, "koan -r -s " + hc.RHN_HOST + " -p " + cobbler_prof);
		ssh.executeViaSSH(system, "reboot");
		return true;
	}
	
	protected boolean deleteFile(String system, String filePath) {
		String deleteFile = "rm -f " + filePath;
		String retVal = ssh.executeViaSSHWithReturn(system, deleteFile)[0];
		log.info("Return Value is : " + retVal);
		if (retVal.compareTo("") == 0) {
			return true;
		}
		return false;
	}
	
	public boolean verifyFile(String system, String filePath) {
		String verifyFile = "ls " + filePath;
		String retVal = ssh.executeViaSSHWithReturn(system, verifyFile)[0];
		log.info("Return Value is : " + retVal);
		if (retVal.compareTo("/tmp/marker-file") == 0) {
			return true;
		}
		return false;
	}
	
	public boolean waitForSSH(String system){
		boolean sshOpen = false;
		for(int i=0;i<15;i++){
			sshOpen = okick.checkIfSSHOpen(system, true);
			if(sshOpen)
				break;
			//sleep for 2 minutes if ping succeeds but SSH is not open
			task_RhnBase.sleepForSeconds(120);
		}
		return sshOpen;
	}
	
	public void prepareSystemForProvisioning(String system){
		task_TestPrep.command_rpm(" -e --nodeps rhncfg ", system, true);
		task_TestPrep.command_rpm(" -e --nodeps rhncfg-client", system,
				true);
		task_TestPrep.command_rpm(" -e --nodeps rhncfg-actions", system,
				true);
		//task_TestPrep.registerSystemSimple(system);  register before call to this method
		this.subscribeToRhnTools(system);
	}
	
	public boolean virtKSFromKoan(String system, String profile, String virtName){
		String[] output = ssh.executeViaSSHWithReturn(hc.RHN_HOST, "cobbler profile list");
		String[] outlines = output[0].split("\n");
		String fullProfileName = "";
		for (String line:outlines)
			if (line.contains(profile))
				fullProfileName = line;
		if (fullProfileName == "")	return false;
		ssh.executeViaSSH(system, "yum install koan -y");
		ssh.executeViaSSH(system, "up2date koan");
		ssh.executeViaSSH(system, "koan --virt -s " + hc.RHN_HOST + " -p " + fullProfileName + " --virt-name=" + virtName); // + "--virt-bridge=xenbr2");
		return true;
	}
	
	
	public void createKickstartDistribution(String label, String externalUrl,String baseChannel, String installerGeneration){
		page_SatelliteSystems.open();
        page_KickStart.open();
        page_KickStart.clickLink_Distributions();
        page_KickStart.clickLink_create_new_distribution();
        page_KickStart.setTxt_DistributionLabel(label);
        page_KickStart.setTxt_ExternalLocationUrl(externalUrl);
        page_KickStart.select_DistributionBaseChannel(baseChannel, true);
        page_KickStart.select_KickStart_InstallerGeneration(installerGeneration, false);
        page_KickStart.clickButton_CreateKickstartDistribution();
        assertTrue(rh.isTextPresent("Kickstart Distribution Created"));
	}
	
	/**
	 * Applies a text to the end of the "Partition Details" script.
	 * @param profileName Kickstart profile.
	 * @param textToAdd The text to be added.
	 */
	public void appendTextToPartitionDetails(String profileName, String textToAdd){
		page_KickStart.open();
		page_KickStart.referToKsProfile(profileName);
		page_KickStart.clickLink_SystemDetails();
		page_KickStart.clickLink_Partitioning();
		sel.focus("xpath=//textarea[@name='partitions']");
		sel.click("xpath=//textarea[@name='partitions']");
		sel.controlKeyDown();
		sel.keyPressNative(Integer.toString(KeyEvent.VK_END)); // Simulate "Ctrl+End"
		sel.controlKeyUp();
		sel.keyPressNative(Integer.toString(KeyEvent.VK_ENTER)); // type "ENTER"
		sel.typeKeys("xpath=//textarea[@name='partitions']", textToAdd);
		page_KickStart.clickButton_UpdatePartitions();
		assertTrue(sel.isTextPresent("Kickstart profile options updated successfully."));		
	}
	
	
	public void appendSoftwarePackages(String profileName, String[] packagesToAdd){
		String selpath_packageList = "xpath=//textarea[@name='packageList']";
		page_KickStart.open();
		page_KickStart.referToKsProfile(profileName);
		page_KickStart.clickLink_Software();
		page_KickStart.clickLink_PackageGroups();
		sel.focus(selpath_packageList);
		sel.click(selpath_packageList);
		sel.controlKeyDown();
		sel.keyPressNative(Integer.toString(KeyEvent.VK_END)); // Simulate "Ctrl+End"
		sel.controlKeyUp();
		for(int i=0;i<packagesToAdd.length;i++){
			sel.keyPressNative(Integer.toString(KeyEvent.VK_ENTER)); // type "ENTER"
			sel.typeKeys(selpath_packageList, packagesToAdd[i]);
		}
		page_KickStart.clickButton_UpdatePackages();
		assertTrue(sel.isTextPresent("Kickstart profile package groups updated successfully."));		
	}
	
}
