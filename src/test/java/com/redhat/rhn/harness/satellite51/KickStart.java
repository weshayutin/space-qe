package com.redhat.rhn.harness.satellite51;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;

public class KickStart extends com.redhat.rhn.harness.satellite50.KickStart{


	//KICKSTART TO RHEL5 PV HOST
	@Test(groups = { "testplan-Kickstart" })
	public void testKS15EnableProvisioning(){
		
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.removeAllSystemProfiles(false);
		task_TestPrep.command_remove_localKnownHosts(false);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,HarnessConfiguration.RHN_SAT_REG_CMD, true, false);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_KickStart.DeleteKickstartProfile("automation", true);
	}

	@Test(dependsOnMethods="testKS15EnableProvisioning",groups = { "testplan-Kickstart" })
	public void testKS17CreateKSProfile01_RHEL5PVHost(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
										"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
										"ks-rhel-i386-server-5-u1", "automation",
										IRhnBase.SSH_PUBLIC_KEY,
										"true",
										"false", true);
	}

	@Test(dependsOnMethods="testKS17CreateKSProfile01_RHEL5PVHost",groups = { "testplan-Kickstart" })
	public void testKS18InitiateKickstart_RHEL5PVHost(){
		task_RhnBase.OpenAndLogIn();
		assertTrue(okick.KickStartSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation", true));
		task_TestPrep.command_rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		
	}

	@Test(dependsOnMethods="testKS18InitiateKickstart_RHEL5PVHost",groups = { "testplan-Kickstart" })
	public void testKS19WaitForKickstart_RHEL5PVHost(){
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
		
	}

	@Test(dependsOnMethods="testKS19WaitForKickstart_RHEL5PVHost",groups = { "testplan-Kickstart" })
	public void testKS20SSHtoGuest_RHEL5PVHost(){
		//this is very redundant, need to test and fix
		task_RhnBase.OpenAndLogIn();
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		task_RhnBase.sleepForSeconds(300);
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		Assert.assertTrue(okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true));
	}
	

	// Bare Metal Kickstart test   - bare metal kickstart a virt guest system
	
	@Test(dependsOnMethods="testKS20SSHtoGuest_RHEL5PVHost",groups = { "testplan-Kickstart" })
	public void testKS30DeleteKSProfile(){
		task_KickStart.DeleteKickstartProfile("automation", true);
		task_TestPrep.command_remove_localKnownHosts(false);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.enableVirtualizationPlatform(IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_Virt.removeAllVirtGuestsFromSystem(IRhnBase.SYSTEM_HOSTNAME02, false);
	}
	
	@Test(dependsOnMethods="testKS30DeleteKSProfile",groups = { "testplan-Kickstart" })
	public void testKS31CreateKSProfile01_RHEL5Guest(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
										"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
										"ks-rhel-i386-server-5-u1",
										"automation",
										IRhnBase.SSH_PUBLIC_KEY,
										"false",
										"true", true);
	}
	
	@Test(dependsOnMethods="testKS31CreateKSProfile01_RHEL5Guest",groups = { "testplan-Kickstart" })
	public void testKS32InitiateBareMetalKickstart_RHEL5Guest(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_remove_localKnownHosts(false);
		
		String 		command = 		null;
		String 		ks_url = 		task_RhnBase.get_BareMetal_KickstartUrl("automation");
		String 		Name = 			"bmkick";
		String 		Ram  = 			"256";
		String  	Drive_Dir =		"/var/lib/xen/images/automation";
		String 		Drive_Size =	"3"; //gigs
		String		Repo_URL =		"http://porkchop.devel.redhat.com/released/RHEL-5-Server/U1/i386/os/";
		
		task_TestPrep.command_generic("xm", "destroy bmkick", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_generic("virsh", "undefine bmkick", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_generic("rm", "-Rf "+Drive_Dir, IRhnBase.SYSTEM_HOSTNAME02, true);
		//rb.command_generic("ln","-s /var/satellite/rhn/kickstart/ /var/www/html/pub/kickstart",IRhnBase.SERVER_HOSTNAME,true);
		
		command = "virt-install -n "+Name+" -r "+Ram+" -f "+Drive_Dir+" -s "+Drive_Size+" --nographics -p -l "+Repo_URL+" -x ks="+ks_url;
		System.out.println("command = "+command);
		
		task_RhnBase.command_runCommandWithAT(IRhnBase.SYSTEM_HOSTNAME02, command, true);
				
	}

	@Test(dependsOnMethods="testKS32InitiateBareMetalKickstart_RHEL5Guest",groups = { "testplan-Kickstart" })
	public void testKS33CheckBareMetalKickstart_Status(){
	String virtGuestStatus = null;
	task_RhnBase.OpenAndLogIn();
	do{
		task_Virt.getVirtGuestStatusFromRHN(IRhnBase.SYSTEM_HOSTNAME02, "bmkick", false);
		log.fine("CHECKING FOR STATUS = ANY");
	}
	while(rh.isTextPresent("No virtual systems."));
		do {
			rh.sleepForSeconds(300);
			log.fine("CHECKING FOR STATUS = Running");
		}
		while (task_Virt.getVirtGuestStatusFromRHN(IRhnBase.SYSTEM_HOSTNAME02, "bmkick", false).equalsIgnoreCase("Running"));
		log.fine("status = "+virtGuestStatus);
		task_Virt.sleepForSeconds(20);
		log.fine("CHECKING FOR STATUS = Stopped");
		if(task_Virt.getVirtGuestStatusFromRHN(IRhnBase.SYSTEM_HOSTNAME02, "bmkick", false).equalsIgnoreCase("Stopped")){
			log.fine("status = "+virtGuestStatus);
			log.fine("proceeding");
		}
		if(rh.isTextPresent(".rhndev.redhat.com")){
			log.fine("System appears to have registered to satellite");
		}
		else{
			log.fine("Found status other than stopped, something bad happened");
			assertTrue(false);
		}
	}
		
	@Test(dependsOnMethods="testKS33CheckBareMetalKickstart_Status",groups = { "testplan-Kickstart" })
	public void testKS34CheckBareMetalKickstartSuccess(){		
		String virtGuestHostName = null;
		task_RhnBase.OpenAndLogIn();
		task_Virt.getVirtGuestStatusFromRHN(IRhnBase.SYSTEM_HOSTNAME02, "bmkick", false);
		if(rh.isTextPresent(".rhndev.redhat.com")){
			//rb.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "bmkick", false, true, IRhnBase.VIRT_GUEST_START);
			task_TestPrep.command_generic("virsh", "start bmkick", IRhnBase.SYSTEM_HOSTNAME02, false);
			rh.sleepForSeconds(60);
			virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, "bmkick", false);
			okick.checkIfSSHOpen(virtGuestHostName, true);
			task_RhnBase.sleepForSeconds(30);
			assertTrue(okick.checkIfSSHOpen(virtGuestHostName, true)); 
		}
		else{
			assertTrue(false);
			log.fine("test failed");
			
		}
	
	
	}

}
