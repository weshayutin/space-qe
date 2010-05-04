package com.redhat.rhn.harness.hosted;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.KickStart;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.octokick.OKickStart;

public class T09KickStartHosted extends KickStart{

	protected RhnHelper rh = new RhnHelper();
	
	protected OKickStart okick = new com.redhat.rhn.harness.common.octokick.OKickStart();
	
	protected static String distroLbl_RHEL5 = "";
	protected static String extURL_RHEL5 = 
		"http://porkchop.devel.redhat.com/released/RHEL-5-Server/U3/i386/os/";
	protected static String distroLbl_RHEL4 = "";
	protected static String extURL_RHEL4 =
		"http://porkchop.devel.redhat.com/released/RHEL-4/U7/AS/i386/tree/";
	protected final String BM_Label = "rhel5Satellite"; 
	
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
	
	@Test(groups = { "testplan-Kickstart", "RHEL5_KS", "RHEL4_KS", "BM_KS"})
	public void testKS09EnableProvisioning(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_remove_localKnownHosts(false);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_KickStart.DeleteKickstartProfile("automation", false);
	}
	
	
	//kickstart to RHEL 5
	
	@Test(dependsOnMethods="testKS09EnableProvisioning",
			groups = { "testplan-Kickstart", "RHEL5_KS"})
	public void test04CreateKSDistro01_RHEL5(){
		task_RhnBase.OpenAndLogIn();
		distroLbl_RHEL5 = "RHEL5-"+task_RhnBase.generate_randomLabel();
		this.createKickstartDistribution(distroLbl_RHEL5, 
				extURL_RHEL5, 
				"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
				"rhn-kickstart",
				"Red Hat Enterprise Linux 5");
	}
	
	@Test(dependsOnMethods="test04CreateKSDistro01_RHEL5",
			groups = { "testplan-Kickstart", "RHEL5_KS" })
	public void test05CreateKSProfile01_RHEL5(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
				"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
				distroLbl_RHEL5, "automation",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"false", true);
	}
	
	@Test(dependsOnMethods="test05CreateKSProfile01_RHEL5",
			groups = { "testplan-Kickstart", "RHEL5_KS" })
	public void testKS06InitiateKickstart_RHEL5(){
		boolean kickstartSuccess = false;
		task_RhnBase.OpenAndLogIn();
		kickstartSuccess = (okick.KickStartSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation", true));
		assertTrue(kickstartSuccess);
		if(kickstartSuccess){
			task_TestPrep.command_rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		}
		
	}
	
	@Test(dependsOnMethods="testKS06InitiateKickstart_RHEL5",
			groups = { "testplan-Kickstart", "RHEL5_KS" })
	public void testKS07WaitForKickstart_RHEL5(){
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
	}
	
	@Test(dependsOnMethods="testKS07WaitForKickstart_RHEL5",
			groups = { "testplan-Kickstart", "RHEL5_KS" })
	public void testKS08SSHtoGuest_RHEL5(){
		task_RhnBase.OpenAndLogIn();
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true);
		Assert.assertTrue(okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02, true));
	}
	
	/*
	//kickstart to RHEL 4
	
	
	
	@Test(dependsOnMethods="testKS09EnableProvisioning",
			groups = { "testplan-Kickstart", "RHEL4_KS"})
	public void test10CreateKSDistro01_RHEL4(){
		task_RhnBase.OpenAndLogIn();
		distroLbl_RHEL5 = "RHEL4-"+task_RhnBase.generate_randomLabel();
		this.createKickstartDistribution(distroLbl_RHEL4, 
				extURL_RHEL4, 
				"Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)", 
				"ks-rhel-i386-as-4-u7",
				"Red Hat Enterprise Linux 4");
	}
	
	@Test(dependsOnMethods="testKS10CreateKSDistro01_RHEL4",
			groups = { "testplan-Kickstart", "RHEL4_KS"})
	public void testKS11CreateKSProfile01_RHEL4(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
				"Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)",
				distroLbl_RHEL4, "automation",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"false", true);
	
	}
	
	@Test(dependsOnMethods="testKS11CreateKSProfile01_RHEL4",
			groups = { "testplan-Kickstart", "RHEL4_KS"})
	public void testKS12InitiateKickstart_RHEL4(){
		boolean kickstartSuccess = false;
		task_RhnBase.OpenAndLogIn();
		kickstartSuccess = (okick.KickStartSystem(IRhnBase.SYSTEM_HOSTNAME02, "automation", true));
		assertTrue(kickstartSuccess);
		if(kickstartSuccess){
			task_TestPrep.command_rebootSystem(IRhnBase.SYSTEM_HOSTNAME02, true);
		}
		
	}
	
	@Test(dependsOnMethods="testKS12InitiateKickstart_RHEL4",
			groups = { "testplan-Kickstart", "RHEL4_KS"})
	public void testKS13WaitForKickstart_RHEL4(){
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, "automation", true);
	}
	
	@Test(dependsOnMethods="testKS13WaitForKickstart_RHEL4",
			groups = { "testplan-Kickstart", "RHEL4_KS"})
	public void testKS14SSHtoGuest_RHEL4(){
		task_RhnBase.OpenAndLogIn();
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02);
		okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02);
		Assert.assertTrue(okick.checkIfSSHOpen(IRhnBase.SYSTEM_HOSTNAME02));
	}*/
	
	//kickstart a virtualized guest via Bare Metal
	/*
	@Test(dependsOnMethods="testKS09EnableProvisioning",
			groups = { "testplan-Kickstart", "BM_KS" })
	public void testKS30DeleteKSProfile(){
		task_KickStart.DeleteKickstartProfile("automation", true);
		task_TestPrep.command_remove_localKnownHosts(false);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
		task_TestPrep.enableVirtualizationPlatform(IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_Virt.removeAllVirtGuestsFromSystem(IRhnBase.SYSTEM_HOSTNAME02, false);
	}
	
	@Test(dependsOnMethods="testKS09EnableProvisioning",
			groups = { "testplan-Kickstart", "BM_KS"})
	public void test31CreateKSDistro01_RHEL5Guest(){
		task_RhnBase.OpenAndLogIn();
		distroLbl_RHEL5 = "RHEL5-"+task_RhnBase.generate_randomLabel();
		this.createKickstartDistribution(distroLbl_RHEL5, 
				extURL_RHEL5, 
				"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
				"rhn-kickstart",
				"Red Hat Enterprise Linux 5");
	}
	
	@Test(dependsOnMethods="testKS31CreateKSDistro01_RHEL5Guest",
			groups = { "testplan-Kickstart", "BM_KS" })
	public void testKS32CreateKSProfile01_RHEL5Guest(){
		task_RhnBase.OpenAndLogIn();
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02,
				"Red Hat Enterprise Linux (v. 5 for 32-bit x86)",
				distroLbl_RHEL5, "automation",
				IRhnBase.SSH_PUBLIC_KEY,
				"false",
				"false", true);
	}
	
	@Test(dependsOnMethods="testKS32CreateKSProfile01_RHEL5Guest",
			groups = { "testplan-Kickstart", "BM_KS" })
	public void testKS33InitiateBareMetalKickstart_RHEL5Guest(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_remove_localKnownHosts(false);
		
		String 		command = 		null;
		String 		ks_url = 		task_RhnBase.get_BareMetal_KickstartUrl("automation");
		String 		Name = 			BM_Label;
		String 		Ram  = 			"256";
		String  	Drive_Dir =		"/var/lib/xen/images/automation";
		String 		Drive_Size =	"3"; //gigs
		String		Repo_URL =		extURL_RHEL5;
		
		task_TestPrep.command_generic(
				"xm", "destroy "+ BM_Label, IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_generic(
				"virsh", "undefine "+BM_Label, IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_generic(
				"rm", "-Rf "+Drive_Dir, IRhnBase.SYSTEM_HOSTNAME02, true);
		//rb.command_generic("ln","-s /var/satellite/rhn/kickstart/ /var/www/html/pub/kickstart",IRhnBase.SERVER_HOSTNAME,true);
		
		command = "virt-install -n "+Name+" -r "+Ram+" -f "+Drive_Dir+" -s "+Drive_Size+" --nographics -p -l "+Repo_URL+" -x ks="+ks_url;
		System.out.println("command = "+command);
		
		task_RhnBase.command_runCommandWithAT(IRhnBase.SYSTEM_HOSTNAME02, command, true);
				
	}

	@Test(dependsOnMethods="testKS33InitiateBareMetalKickstart_RHEL5Guest",
			groups = { "testplan-Kickstart", "BM_KS" })
	public void testKS34CheckBareMetalKickstart_Status(){
		String virtGuestStatus = null;
		task_RhnBase.OpenAndLogIn();
		do{
			task_Virt.getVirtGuestStatusFromRHN(IRhnBase.SYSTEM_HOSTNAME02, BM_Label, false);
			log.fine("CHECKING FOR STATUS = ANY");
		}
		while(rh.isTextPresent("No virtual systems."));
		if (!task_Virt.getVirtGuestStatusFromRHN(
				IRhnBase.SYSTEM_HOSTNAME02, 
				BM_Label, 
				false).equalsIgnoreCase(BM_Label)){
			log.fine("Bare Metal Virtualized kickstart failed, did not find label");
			fail();
		}
		else{
			log.fine("Bare Metal Virtualized kickstart succeeded");
		}
		int i = 0;
		do {
			if(i == 15)
				break;
			rh.sleepForSeconds(60);
			log.fine("CHECKING FOR STATUS = Running");
			i++;
		}
		while (task_Virt.getVirtGuestStatusFromRHN(
				IRhnBase.SYSTEM_HOSTNAME02, 
				BM_Label,
				false).equalsIgnoreCase(BM_Label));
		log.fine("status = "+virtGuestStatus);
		task_Virt.sleepForSeconds(20);
		log.fine("CHECKING FOR STATUS = Registered");
		if(rh.isTextPresent(".rhndev.redhat.com")){
			log.fine("System appears to have registered to satellite");
		}
		else{
			log.fine("Found status other than stopped, something bad happened");
			assertTrue(false);
		}
	}
		
	@Test(dependsOnMethods="testKS34CheckBareMetalKickstart_Status",
			groups = { "testplan-Kickstart", "BM_KS" })
	public void testKS35CheckBareMetalKickstartSuccess(){		
		String virtGuestHostName = null;
		task_RhnBase.OpenAndLogIn();
		task_Virt.getVirtGuestStatusFromRHN(IRhnBase.SYSTEM_HOSTNAME02, BM_Label, false);
		//rb.virtGuest_Action(IRhnBase.SYSTEM_HOSTNAME02, "bmkick", false, true, IRhnBase.VIRT_GUEST_START);
		task_TestPrep.command_generic("virsh", 
				"start "+BM_Label, 
				IRhnBase.SYSTEM_HOSTNAME02, 
				false);
		rh.sleepForSeconds(60);
		virtGuestHostName = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, BM_Label, false);
		okick.checkIfSSHOpen(virtGuestHostName);
		task_RhnBase.sleepForSeconds(30);
		assertTrue(okick.checkIfSSHOpen(virtGuestHostName)); 
	}
	*/
}