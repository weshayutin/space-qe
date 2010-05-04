package com.redhat.rhn.harness.satellite50;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.octokick.OKickStart;

@Test(groups="tests")
public class KickStart extends SeleniumSetup{


	protected RhnHelper rh = new RhnHelper();
	
	protected OKickStart okick = new com.redhat.rhn.harness.common.octokick.OKickStart();
	
	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,HarnessConfiguration.RHN_SAT_REG_CMD, true, false);
		task_TestPrep.command_remove_localKnownHosts(false);
		if(HarnessConfiguration.OCTOKICK_SIGNED_PACKAGES == 0){
		}
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_KickStart.DeleteKickstartProfile("automation", false);
	}

	@Test(groups = { "testplan-Kickstart" })
	public void test05CreateKSProfile01_RHEL5(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
				"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
				"ks-rhel-i386-server-5-u1", "automation",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"false", true);
	}

	@Test(dependsOnMethods="test05CreateKSProfile01_RHEL5",groups = { "testplan-Kickstart" })
	public void testKS06InitiateKickstart_RHEL5(){
		boolean kickstartSuccess = false;
		task_RhnBase.OpenAndLogIn();
		kickstartSuccess = (okick.KickStartSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation", true));
		assertTrue(kickstartSuccess);
		if(kickstartSuccess){
			task_TestPrep.command_rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		}
		
	}

	@Test(dependsOnMethods="testKS06InitiateKickstart_RHEL5",groups = { "testplan-Kickstart" })
	public void testKS07WaitForKickstart_RHEL5(){
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
	}

	@Test(dependsOnMethods="testKS07WaitForKickstart_RHEL5",groups = { "testplan-Kickstart" })
	public void testKS08SSHtoGuest_RHEL5(){
		task_RhnBase.OpenAndLogIn();
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		Assert.assertTrue(okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true));
	}


	//kickstart to RHEL 4

	@Test(groups = { "testplan-Kickstart" })
	public void testKS09EnableProvisioning(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_remove_localKnownHosts(false);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_KickStart.DeleteKickstartProfile("automation", true);
	}

	@Test(dependsOnMethods="testKS09EnableProvisioning",groups = { "testplan-Kickstart" })
	public void testKS11CreateKSProfile01_RHEL4(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
				"Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)",
				"ks-rhel-i386-as-4-u6", "automation",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"false", true);

	}

	@Test(dependsOnMethods="testKS11CreateKSProfile01_RHEL4",groups = { "testplan-Kickstart" })
	public void testKS12InitiateKickstart_RHEL4(){
		boolean kickstartSuccess = false;
		task_RhnBase.OpenAndLogIn();
		kickstartSuccess = (okick.KickStartSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation", true));
		assertTrue(kickstartSuccess);
		if(kickstartSuccess){
			task_TestPrep.command_rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		}
		
	}
	
	@Test(dependsOnMethods="testKS12InitiateKickstart_RHEL4",groups = { "testplan-Kickstart" })
	public void testKS13WaitForKickstart_RHEL4(){
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
	}

	@Test(dependsOnMethods="testKS13WaitForKickstart_RHEL4",groups = { "testplan-Kickstart" })
	public void testKS14SSHtoGuest_RHEL4(){
		task_RhnBase.OpenAndLogIn();
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		Assert.assertTrue(okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true));
	}

	
}
