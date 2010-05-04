package com.redhat.rhn.harness.satellite51;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.octokick.OKickStart;
import com.thoughtworks.selenium.SeleniumException;

@Test(groups="tests")
public class KickStartAdvanced extends SeleniumSetup{
	
	protected RhnHelper rh = new RhnHelper();
	
	OKickStart okick = new com.redhat.rhn.harness.common.octokick.OKickStart();
	
	String key = "kickkey";
	
	
	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,HarnessConfiguration.RHN_SAT_REG_CMD, true, false);
		try{
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, true, true, IRhnBase.VIRT_GUEST_STOP);
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false, true, IRhnBase.VIRT_GUEST_DESTROY);
			}
			catch(SeleniumException se){
				se.printStackTrace();
			}
			//rh.stopSelenium();
			task_TestPrep.command_generic("xm ", "destroy "+"automation_"+IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("rm " , " -Rf /var/lib/xen/images/*", IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("virsh " , "undefine automation", IRhnBase.SYSTEM_HOSTNAME02, false);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_KickStart.DeleteKickstartProfile("automation", false);
		task_ActivationKeys.deleteActivationKey(key, false);
		
	}


	@Test(groups = { "testplan-Kickstart" })
	public void test06PrepCreateKey(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.createActivationKey(key, "123456", "1", false, true, false, false, false);
	}
	
	@Test(dependsOnMethods="test06PrepCreateKey",groups = { "testplan-Kickstart" })
	public void test07CreateKSProfile01(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
				"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
				"ks-rhel-i386-server-5-u1", "automation",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"true", true);
		
	}
	
	@Test(dependsOnMethods="test07CreateKSProfile01",groups = { "testplan-Kickstart" })
	public void test08AddKeyToKSProfile(){
		task_KickStart.AddActivationKeyToKickstartProfile("automation");
	}

	@Test(dependsOnMethods="test08AddKeyToKSProfile",groups = { "testplan-Kickstart" })
	public void test09InitiateKickstart_ActivationKey(){
		task_RhnBase.OpenAndLogIn();
		okick.KickStartVirtSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation");
	}
	
	@Test(dependsOnMethods="test09InitiateKickstart_ActivationKey",groups = { "testplan-Kickstart" })
	public void test10WaitForKickstart_ActivationKey(){
		task_RhnBase.OpenAndLogIn();
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
	}
		
	@Test(dependsOnMethods="test10WaitForKickstart_ActivationKey",groups = { "testplan-Kickstart" })
	public void test11SSHtoGuest_RHEL5Guest(){
		String virtGuestHostName = null;
		task_RhnBase.OpenAndLogIn();
		virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		okick.checkIfSSHOpen(virtGuestHostName, true);
		Assert.assertTrue(okick.checkIfSSHOpen(virtGuestHostName, true));
	}
	
	@Test(dependsOnMethods="test11SSHtoGuest_RHEL5Guest",groups = { "testplan-Kickstart" })
	public void test12TestIfSystemActivated(){
		String virtGuestHostName = null;
		task_RhnBase.OpenAndLogIn();
		virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		assertTrue(task_RhnBase.isSystemActivated(virtGuestHostName, key));
	}
	
}
