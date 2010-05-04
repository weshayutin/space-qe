package com.redhat.rhn.harness.Space01;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.octokick.OKickStart;

/*
 * prereq: requires that SYSTEM_HOSTNAME02 be a virt capable box (RHEL 5)
 * yum install kernel-xen 
 * reboot with the console so you can choose the correct kernel in (/etc/grub.conf)
 * "uname -a" will indicate if the xen kernel has been booted
 * from a satellite, add the Virtualization entitlement
 * rhn_check -vvv  (on the host)
 * yum install xen
 * service xend start
 * service libvirtd start
 * sudo chkconfig xend on
 * also make sure koan is installed - yum install koan
 * Now the virtual kickstarts can be executed against SYSTEM_HOSTNAME02
 * 
 * TODO The execution ordering of the Virtual guests should be run first so that 
 * the host kernel does not get clobbered
 */
@Test(groups="tests")
public class KickStart extends com.redhat.rhn.harness.common.Space01.tasks.KickStart{
	
	public static final int MAX_TICKS = 50;
	public static final int TICK_SECONDS = 60;
	
	public static String actKeyName = null;
	public static String actKeyLabel = null;
	public static String activationKey = null;
	public static String ksLabel = null;
	public static String customTreeName = null;
	
	public final String baseChannel = "Red Hat Enterprise Linux (v. 5 for 32-bit x86)";
	public final String ksTree = "ks-rhel-i386-server-5-u3";
	public final String virtType_None = "None";
	public final String virtType_PVHost = "Para-Virtualized Host";
	public final String virtType_Guest = "Xen Para-Virtualized Guest";
	public final String customKSMarker = "/tmp/automation-custom-ks-complete";
	
	public final String baseChannelRHEL4 = "Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)";
	public final String ksTreeRHEL4 = "ks-rhel-i386-as-4-u7";
	
	@BeforeClass(groups = { "setup" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
	}
	
	@BeforeClass(groups = {"testplan-Kickstart", "customKickstart"})
	public void test01Prep01_Custom(){
		task_RhnBase.OpenAndLogIn();
		if (HarnessConfiguration.isNevraTestingEnabled() == false) {
			return;
		}
		actKeyName = "Automation Custom AK-" + task_RhnBase.generate_randomLabel();
		actKeyLabel = "automation-custom-key01-" + task_RhnBase.generate_randomLabel();
		task_ActivationKeys.deleteActivationKey(actKeyName,
				false);
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME04,
				false);
		task_ActivationKeys.createActivationKeyWithBaseChannel(actKeyName,
				actKeyLabel, 
				IRhnBase.NEVRA_CUSTOM_BASE_CHANNEL_NAME,
				"",
				false,
				true,
				false,
				false,
				false);
		task_ActivationKeys.addChildChannelToKey(actKeyName,
				task_RhnBase.NEVRA_CUSTOM_CHILD_CHANNEL_NAME);
		activationKey = task_ActivationKeys.getAKFromKeyName(actKeyName);
		assertTrue("".compareTo(activationKey) != 0);
		task_TestPrep.registerSystemWithActKey(task_RhnBase.SYSTEM_HOSTNAME04,
				task_RhnBase.SYSTEM_HOSTNAME04,
				activationKey,
				true);
		

		task_TestPrep.enableProvisioning(task_RhnBase.SYSTEM_HOSTNAME04,
				true);
		task_TestPrep.command_runRhnCheckInForeground(task_RhnBase.SYSTEM_HOSTNAME04,
				false);
		this.deleteFile(task_RhnBase.SYSTEM_HOSTNAME04,
				customKSMarker);
	}


	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(
				task_TestPrep.command_check_SELinux_AuditLog(
						IRhnBase.SERVER_HOSTNAME));
	}
	
	@AfterClass(groups = { "teardown" })
	public void cleanUp(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.deleteActivationKey(actKeyName, false);
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME04,
				false);
		this.deleteKickstartDistribution(customTreeName);
		this.DeleteKickstartProfile(ksLabel, false);
	}
	
	@Test(groups = { "testplan-Kickstart", "Koan_Kickstart"})
	public void test_KoanKickstart(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true, false);
		this.prepareSystemForProvisioning(task_RhnBase.SYSTEM_HOSTNAME02);
		String profileName = "koankick-" + task_RhnBase.generate_randomLabel();
		assertTrue(this.createNewKickstartProfile(profileName, 
				baseChannel, 
				ksTree, 
				virtType_None,
				task_RhnBase.PASSWORD,
				task_RhnBase.SSH_PUBLIC_KEY));
		assertTrue(this.bareMetalKSFromKoan(task_RhnBase.SYSTEM_HOSTNAME01,
											profileName));
		task_RhnBase.sleepForSeconds(60);
		assertTrue(this.waitForSSH(task_RhnBase.SYSTEM_HOSTNAME01));
	}
	
	@Test(groups = { "testplan-Kickstart", "Koan_Kickstart"})
	public void test_KoanVirtKickstart(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true, false);
		this.prepareSystemForProvisioning(task_RhnBase.SYSTEM_HOSTNAME02);
		String profileName = "koanvirtkick-" + task_RhnBase.generate_randomLabel();
		String systemName = task_RhnBase.generate_randomLabel();
		assertTrue(this.createNewKickstartProfile(profileName, 
				baseChannel, 
				ksTree, 
				virtType_Guest,
				task_RhnBase.PASSWORD,
				task_RhnBase.SSH_PUBLIC_KEY));
		assertTrue(this.virtKSFromKoan(task_RhnBase.SYSTEM_HOSTNAME02,
				profileName,
				systemName));

		//TODO Need to verify the virt system???
		//Manual steps:
		//to verify that the virtualized system has been appropriately provisioned, perform an
		//'xm list' on the system and locate the virt guest that was created.  When you have that information,
		//run 'virsh console <guestname>' to verify that provisioning succeeded.
	}
	
	@Test(groups = { "testplan-Kickstart", "WebUI_Kickstart"})
	public void test_WebUIKickstartRHEL4(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true, false);
		this.prepareSystemForProvisioning(task_RhnBase.SYSTEM_HOSTNAME02);
		String profileName = "rhel4kick-" + task_RhnBase.generate_randomLabel();
		assertTrue(this.createNewKickstartProfile(profileName, 
				baseChannelRHEL4, 
				ksTreeRHEL4, 
				virtType_None,
				task_RhnBase.PASSWORD,
				task_RhnBase.SSH_PUBLIC_KEY));
		okick.KickStartSystem(task_RhnBase.SYSTEM_HOSTNAME02,
				profileName,
				true);
		
		if(okick.checkIfSuccessfulPackageInstall(IRhnBase.SYSTEM_HOSTNAME02) == OKickStart.PKG_COMPLETE){
			task_TestPrep.rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		}
		assertTrue(this.waitForSSH(task_RhnBase.SYSTEM_HOSTNAME02));
	}
	
	@Test(groups = { "testplan-Kickstart", "WebUI_Kickstart"})
	public void test_WebUIKickstartRHEL5(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true, false);
		this.prepareSystemForProvisioning(task_RhnBase.SYSTEM_HOSTNAME02);
		String profileName = "rhel5kick-" + task_RhnBase.generate_randomLabel();
		assertTrue(this.createNewKickstartProfile(profileName, 
				baseChannel, 
				ksTree, 
				virtType_None,
				task_RhnBase.PASSWORD,
				task_RhnBase.SSH_PUBLIC_KEY));
		okick.KickStartSystem(task_RhnBase.SYSTEM_HOSTNAME02,
				profileName,
				true);

		if(okick.checkIfSuccessfulPackageInstall(IRhnBase.SYSTEM_HOSTNAME02) == OKickStart.PKG_COMPLETE){
			task_TestPrep.rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		}
		
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, profileName, false);
		int ticks = 0;
		while (!okick.checkIfSSHOpen(task_RhnBase.SYSTEM_HOSTNAME02, true)){
			ticks++;
			if (ticks==MAX_TICKS)
				fail("Machine failed to come up from Kickstart during " +
						"Kickstart File-based Kickstart");
			task_RhnBase.sleepForSeconds(TICK_SECONDS);
		}
		assertTrue(this.waitForSSH(task_RhnBase.SYSTEM_HOSTNAME02));

	}
	
	@Test(groups = {"testplan-Kickstart", "WebUI_Kickstart"})
	public void test_UploadKickstartFile(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true, false);
		this.prepareSystemForProvisioning(task_RhnBase.SYSTEM_HOSTNAME02);
		String label = task_RhnBase.generate_randomLabel();
		assertTrue(this.uploadKickstartFile(label,
				System.getProperty("user.dir") + "/src/main/resources/testKs.cfg", 
				virtType_None, 
				ksTree));
		
		okick.KickStartSystem(task_RhnBase.SYSTEM_HOSTNAME02,
				label,
				true);
		if(okick.checkIfSuccessfulPackageInstall(IRhnBase.SYSTEM_HOSTNAME02) == OKickStart.PKG_COMPLETE){
			task_TestPrep.rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		}
		assertTrue(this.waitForSSH(task_RhnBase.SYSTEM_HOSTNAME02));
		
		String motd = 
			ssh.executeViaSSHWithReturn(task_RhnBase.SYSTEM_HOSTNAME02, 
					"cat /etc/motd")[0];
		assertTrue(motd.contains("Successful Kickstart File"));
	}
	
	@Test(groups = {"testplan-Kickstart", "WebUI_Kickstart"})
	public void test_VirtKickstart(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true, false);
		this.prepareSystemForProvisioning(task_RhnBase.SYSTEM_HOSTNAME02);
		String profileName = "virtkick-" + task_RhnBase.generate_randomLabel();
		assertTrue(this.createNewKickstartProfile(profileName, 
				baseChannel, 
				ksTree, 
				virtType_Guest,
				task_RhnBase.PASSWORD,
				task_RhnBase.SSH_PUBLIC_KEY));
		task_TestPrep.enableVirtualizationPlatform(task_RhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_Virt.removeAllVirtGuestsFromSystem(IRhnBase.SYSTEM_HOSTNAME02, false);
		assertTrue(okick.KickStartVirtSystem(task_RhnBase.SYSTEM_HOSTNAME02, 
											 profileName));
		//TODO Need to verify the virt system.  see TODO in test_KoanVirtKickstart
	}
	
	@Test(groups = { "testplan-Kickstart", "customKickstart" })
	public void test010CreateCustomKickstartDistro(){
		task_RhnBase.OpenAndLogIn();
		if (HarnessConfiguration.isNevraTestingEnabled() == false) {
			return;
		}
		customTreeName = "Automation-Custom-Distro-"
			+ task_RhnBase.generate_randomLabel();
		String ksTreePath = task_RhnBase.NEVRA_CUSTOM_KS_TREE_PATH01; 
		String ksChannelName = task_RhnBase.NEVRA_CUSTOM_BASE_CHANNEL_NAME;
		String installerGeneration = "Generic RPM";
		
		this.deleteKickstartDistribution(customTreeName);
		this.createKickstartDistribution(customTreeName,
				ksTreePath,
				ksChannelName,
				installerGeneration);
	}
	
	@Test(dependsOnMethods="test010CreateCustomKickstartDistro",
			groups = { "testplan-Kickstart", "customKickstart" })
	public void test011CreateCustomKickstartProfile(){
		task_RhnBase.OpenAndLogIn();
		if (HarnessConfiguration.isNevraTestingEnabled() == false) {
			return;
		}
		ksLabel = "customkick-" + task_RhnBase.generate_randomLabel();
		task_KickStart.DeleteKickstartProfile(ksLabel, false);
		this.createNewKickstartProfile(ksLabel,
				task_RhnBase.NEVRA_CUSTOM_BASE_CHANNEL_NAME,
				customTreeName,
				virtType_None,
				task_RhnBase.PASSWORD,
				task_RhnBase.SSH_PUBLIC_KEY);
		
		// Add child channel to ks profile
		this.setChildChannelInKickstart(ksLabel, true);
		this.enableRHNToolsAccessInKickstart(ksLabel,
				activationKey);
	}
	
	@Test(dependsOnMethods="test011CreateCustomKickstartProfile",
			groups = { "testplan-Kickstart", "customKickstart" })
	public void test02InitiateKickstart_Custom(){
		task_RhnBase.OpenAndLogIn();
		if (HarnessConfiguration.isNevraTestingEnabled() == false) {
			return;
		}
		boolean kickstartSuccess = false;
		task_RhnBase.OpenAndLogIn();
		kickstartSuccess = (okick.KickStartSystem
				(IRhnBase.SYSTEM_HOSTNAME04,
						ksLabel,
						true));
		assertTrue(kickstartSuccess);
		task_TestPrep.command_rebootSystem(task_RhnBase.SYSTEM_HOSTNAME04,
				true);
	}

	@Test(dependsOnMethods="test02InitiateKickstart_Custom",
			groups = { "testplan-Kickstart", "customKickstart" })
	public void test03WaitForKickstart_Custom(){
		task_RhnBase.OpenAndLogIn();
		if (HarnessConfiguration.isNevraTestingEnabled() == false) {
			return;
		}
		task_RhnBase.command_remove_localKnownHost(task_RhnBase.SYSTEM_HOSTNAME04,
				false);
		//task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME04, "automation", true);
		// The custom distro lacks rhn-tools during the kickstart (They only get installed after)
		// Therefore we aren't able to properly indicate a KS is complete.
		// Approach is to wait for ssh to come up, then verify our marker file is present.
		assertTrue(this.waitForSSH(task_RhnBase.SYSTEM_HOSTNAME04));
		assertTrue(verifyFile(IRhnBase.SYSTEM_HOSTNAME04, customKSMarker));
	}
}
