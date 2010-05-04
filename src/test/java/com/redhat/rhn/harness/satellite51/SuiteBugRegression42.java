package com.redhat.rhn.harness.satellite51;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.KickStartPage;
import com.redhat.rhn.harness.Sat42.pages.SDCPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;
import com.redhat.rhn.harness.common.Sat42.tasks.TestPrep;
import com.redhat.rhn.harness.common.octokick.OKickStart;
import com.thoughtworks.selenium.SeleniumException;

public class SuiteBugRegression42 extends TestPrep{
	
	
	RhnHelper rh = new RhnHelper();
	
	OKickStart okick = new com.redhat.rhn.harness.common.octokick.OKickStart();
	
	 

	public void test01_Bugzilla_224629_228703(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.deleteKickstartDistribution("autotestRHEL5");
		task_RhnBase.createKickstartDistribution("autotestRHEL5", 
									"http://barn.rhndev.redhat.com/redhat/released/RHEL-5-Server/U2/i386/os/",
									"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
									"rhn-kickstart",
									"Red Hat Enterprise Linux 5");
		assertTrue(rh.isTextPresent("Kickstart Distribution Created"));
		assertFalse(rh.isTextPresent("Autokickstart"));
		
		task_RhnBase.deleteKickstartDistribution("autotestRHEL4");
		task_RhnBase.createKickstartDistribution("autotestRHEL4", 
				"http://barn.rhndev.redhat.com/redhat/released/RHEL-4/U7/AS/i386/tree/",
				"Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)",
				"ks-rhel-i386-as-4-u7",
				"Red Hat Enterprise Linux 4");
		assertTrue(rh.isTextPresent("Kickstart Distribution Created"));
		assertFalse(rh.isTextPresent("Autokickstart"));
		
		task_RhnBase.createKickstartDistribution("autotestRHELbad", 
				"asdfasdf12345",
				"Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)",
				"ks-rhel-i386-as-4-u7",
				"Red Hat Enterprise Linux 4");
		assertTrue(rh.isTextPresent("The External Location field is not a valid URL. Please reformat the field to contain a valid http/https/ftp type value."));
		
		task_RhnBase.createKickstartDistribution("autotestRHEL4", 
				"http://barn.rhndev.redhat.com/redhat/released/RHEL-4/U7/AS/i386/tree/",
				"Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)",
				"ks-rhel-i386-as-4-u7",
				"Red Hat Enterprise Linux 4");
		assertTrue(rh.isTextNotPresent("Internal Server Error"));
		
	}
	
	
	public void test02_Bugzilla_229668(){
		String virtGuestHostName = null;
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
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
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.enableVirtualizationPlatform(IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_RhnBase.command_GrubDefaultBoot0(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_KickStart.DeleteKickstartProfile("automation", false);
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
										"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
										"ks-rhel-i386-server-5-u1", 
										"automation",
										IRhnBase.SSH_PUBLIC_KEY,
										"false",
										"true",
										true);
		okick.KickStartVirtSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation");
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", false);
		virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "automation_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		okick.checkIfSSHOpen(virtGuestHostName, true);
		Assert.assertTrue(okick.checkIfSSHOpen(virtGuestHostName, true));
		log.fine("break");
		page_SDC.clickLink_Virtualization_Provisioning();
		assertTrue(rh.isTextPresent("Schedule Kickstart"));
		page_SDC.clickLink_Provisioning();
		assertTrue(rh.isTextPresent("Schedule Kickstart"));
		assertTrue(rh.isTextNotPresent("pending kickstart scheduled."));
		
	}
	
	public void test03_Bugzilla_231813(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,HarnessConfiguration.RHN_SAT_REG_CMD, true, true);
		task_RhnBase.command_tailLog(IRhnBase.SYSTEM_HOSTNAME02, "/var/log/up2date");
		
	}
	
	public void test04_Buzilla_232483(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,HarnessConfiguration.RHN_SAT_REG_CMD, true, true);
		task_KickStart.DeleteKickstartProfile("Ax86profile", false);
		task_KickStart.DeleteKickstartProfile("Ax86_64profile", false);
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME01,
				"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
				"ks-rhel-i386-server-5-u1", 
				"Ax86profile",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"false",
				true);
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME01,
				"Red Hat Enterprise Linux (v. 5 for 64-bit x86_64)",
				"ks-rhel-x86_64-server-5-u1", 
				"Ax86_64profile",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"false",
				true);
		okick.KickStartSystem(IRhnBase.SYSTEM_HOSTNAME01,"x86profile",false);
		page_KickStart.clickLink_CancelKickstart();
		page_KickStart.clickButton_CancelKickStart();
		page_SDC.clickLink_Schedule();
		assertTrue(rh.isTextPresent("Ax86profile"));
		assertTrue(rh.isTextNotPresent("Ax86_64profile"));
	}

	

}
