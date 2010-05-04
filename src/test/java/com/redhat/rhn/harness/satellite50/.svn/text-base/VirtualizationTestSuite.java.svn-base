package com.redhat.rhn.harness.satellite50;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.octokick.OKickStart;
import com.thoughtworks.selenium.SeleniumException;

	
	/*
	 * Prereqs:
	 * rhn.system02 must be a xen capable install     e.g. uname -a => Linux rlx-3-06.rhndev.redhat.com 2.6.18-128.el5xen #1 SMP Wed Dec 17 12:22:24 EST 2008 i686 i686 i386 GNU/Linux
	 */

	@Test(groups="tests")
	public class VirtualizationTestSuite extends SeleniumSetup{
		protected RhnHelper rh = new RhnHelper();
		
		protected OKickStart okick = new com.redhat.rhn.harness.common.octokick.OKickStart();
		
		

		
		@BeforeClass(groups = { "setup" })
		public void test01Prep01(){
			task_TestPrep.removeAllSystemProfiles(true);
				
			try{
				task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false, true, IRhnBase.VIRT_GUEST_DESTROY);
				}
				catch(SeleniumException se){
					log.info(se.getMessage());
					log.info("Clean up, did not find virt guest w/ name automation"+IRhnBase.SYSTEM_HOSTNAME02);
				}
				task_TestPrep.command_generic("xm ", "destroy "+"automation_"+IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02, false);
				task_TestPrep.command_generic("rm " , " -Rf /var/lib/xen/images/*", IRhnBase.SYSTEM_HOSTNAME02, false);
				task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,HarnessConfiguration.RHN_SAT_REG_CMD, true, false);
		}
		
		//rhel 5 guest
		
		@Test(groups = { "testplan-Virtualization-Satellite" })
		public void test03_Enable_PVHost(){
			boolean xenKernelInstalled = false;
			task_RhnBase.OpenAndLogIn();
			task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
			task_TestPrep.enableVirtualizationPlatform(IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
			if(!task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME02, "kernel-xen")){
				task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME02,"kernel-xen");
			}
			else{
				xenKernelInstalled = true;
			}
			if(!task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME02, "xen")){
				task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME02,"xen");
			}
			task_RhnBase.command_GrubDefaultBoot0(IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
			if(!xenKernelInstalled){
				rh.sleepForSeconds(300);
			}

		}

		
		@Test(dependsOnMethods="test03_Enable_PVHost",groups = { "testplan-Virtualization-Satellite" })
		public void test04_deleteKSProfile(){
			task_KickStart.DeleteKickstartProfile("automation", true);
		}

		@Test(dependsOnMethods="test04_deleteKSProfile",groups = { "testplan-Virtualization-Satellite" })
		public void test05_createKSProfile01_RHEL5Guest(){
			task_RhnBase.OpenAndLogIn();
			okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02, "Red Hat Enterprise Linux (v. 5 for 32-bit x86)", "ks-rhel-i386-server-5-u1", "automation", IRhnBase.SSH_PUBLIC_KEY, "false", "true", true);
		
		}

		@Test(dependsOnMethods="test05_createKSProfile01_RHEL5Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test06_initiateVirtGuestKickstart_RHEL5Guest(){
			task_RhnBase.OpenAndLogIn();
			okick.KickStartVirtSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation");
		}

		@Test(dependsOnMethods="test06_initiateVirtGuestKickstart_RHEL5Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test06_waitForVirtGuestInstall_RHEL5Guest(){
			test06_waitForVirtGuestInstall_RHEL5Guest_();
		}
		protected void test06_waitForVirtGuestInstall_RHEL5Guest_(){ // allows this test to be overridden in a subclass
			task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
		}
		
		@Test(dependsOnMethods="test06_waitForVirtGuestInstall_RHEL5Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test07_SSHtoGuest_RHEL5Guest(){
			String virtGuestHostName = null;
			task_RhnBase.OpenAndLogIn();
			virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
			okick.checkIfSSHOpen(virtGuestHostName, true);
			Assert.assertTrue(okick.checkIfSSHOpen(virtGuestHostName, true));
		}

		@Test(dependsOnMethods="test07_SSHtoGuest_RHEL5Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test08_stopGuest_RHEL5Guest(){
			String virtGuestHostName = null;
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, true, true, IRhnBase.VIRT_GUEST_STOP);
			virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
			//okick.checkIfSSHOpen(virtGuestHostName);
			rh.sleepForSeconds(120);
			Assert.assertFalse(okick.checkIfSSHOpen(virtGuestHostName, true));
			task_KickStart.virtGuest_Status(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02,false, "Stopped");
		}

		@Test(dependsOnMethods="test08_stopGuest_RHEL5Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test09_startGuest_RHEL5Guest(){
			String virtGuestHostName = null;
			
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, true, true, IRhnBase.VIRT_GUEST_START);
			virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
			//okick.checkIfSSHOpen(virtGuestHostName);
			rh.sleepForSeconds(120);
			Assert.assertTrue(okick.checkIfSSHOpen(virtGuestHostName, true));
			task_KickStart.virtGuest_Status(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02,false, "Running");
		}

		@Test(dependsOnMethods="test09_startGuest_RHEL5Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test10_destroyVirtGuest_RHEL5Guest(){
			
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, true, true, IRhnBase.VIRT_GUEST_STOP);
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false, true, IRhnBase.VIRT_GUEST_DESTROY);
			
			task_TestPrep.command_generic("xm ", "destroy "+"automation_"+IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("rm " , " -Rf /var/lib/xen/images/*", IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("virsh " , "undefine automation", IRhnBase.SYSTEM_HOSTNAME02, false);
		
		}

		
		
		//RHEL 4 Guest

		@Test(dependsOnMethods="test10_destroyVirtGuest_RHEL5Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test11_deleteKSProfile(){
			task_KickStart.DeleteKickstartProfile("automation", true);
		}

		@Test(dependsOnMethods="test11_deleteKSProfile",groups = { "testplan-Virtualization-Satellite" })
		public void test12_createKSProfile01_RHEL4Guest(){
			task_RhnBase.OpenAndLogIn();
			okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02, "Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)", "ks-rhel-i386-as-4-u6", "automation", IRhnBase.SSH_PUBLIC_KEY, "false", "true", true);
		}

		@Test(dependsOnMethods="test12_createKSProfile01_RHEL4Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test13_initiateVirtGuestKickstart_RHEL4Guest(){
			task_RhnBase.OpenAndLogIn();
			okick.KickStartVirtSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation");
		}

		@Test(dependsOnMethods="test13_initiateVirtGuestKickstart_RHEL4Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test14_waitForVirtGuestInstall_RHEL4Guest(){
			task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
		
		}

		@Test(dependsOnMethods="test14_waitForVirtGuestInstall_RHEL4Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test15_SSHtoGuest_RHEL4Guest(){
			String virtGuestHostName = null;
			task_RhnBase.OpenAndLogIn();
			virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
			okick.checkIfSSHOpen(virtGuestHostName, true);
			Assert.assertTrue(okick.checkIfSSHOpen(virtGuestHostName, true));
		}


		@Test(dependsOnMethods="test15_SSHtoGuest_RHEL4Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test16_stopGuest_RHEL4Guest(){
			String virtGuestHostName = null;
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, true, true, IRhnBase.VIRT_GUEST_STOP);
			virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
			okick.checkIfSSHOpen(virtGuestHostName, true);
			Assert.assertFalse(okick.checkIfSSHOpen(virtGuestHostName, true));
			task_KickStart.virtGuest_Status(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02,false, "Stopped");
		}

		@Test(dependsOnMethods="test16_stopGuest_RHEL4Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test17_startGuest_RHEL4Guest(){
			String virtGuestHostName = null;
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, true, true, IRhnBase.VIRT_GUEST_START);
			virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
			okick.checkIfSSHOpen(virtGuestHostName, true);
			Assert.assertTrue(okick.checkIfSSHOpen(virtGuestHostName, true));
			task_KickStart.virtGuest_Status(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02,false, "Running");
		}


		@Test(dependsOnMethods="test17_startGuest_RHEL4Guest",groups = { "testplan-Virtualization-Satellite" })
		public void test18_destroyVirtGuest_RHEL4Guest(){
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, true, true, IRhnBase.VIRT_GUEST_STOP);
			task_KickStart.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false, true, IRhnBase.VIRT_GUEST_DESTROY);
			task_TestPrep.command_generic("xm ", "destroy "+"automation_"+IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("rm " , " -Rf /var/lib/xen/images/*", IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("virsh " , "undefine automation", IRhnBase.SYSTEM_HOSTNAME02, false);

		}

		
		
	
}
