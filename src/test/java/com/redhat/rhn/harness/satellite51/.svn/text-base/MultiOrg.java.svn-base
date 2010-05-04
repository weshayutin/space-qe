package com.redhat.rhn.harness.satellite51;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

@Test(groups="tests")
public class MultiOrg extends SeleniumSetup {

	protected RhnHelper rh = new RhnHelper();
	
	

	protected String org2 = "autoOrg01";
	protected String org2user = "autoOrgUser02";
	protected String org2email = "automOrgUser01@redhat.com";
	protected String org2paswd = "dog8code";
	protected int TOTAL_SAT_BASE_ENTITLEMENTS = 0;
	protected int bvt_total = 20000;
	protected int non_bvt_total = 100;
	protected int shughesZBigTotal_system = 123450;
	protected int shughesZBigTotal_core = 22000;
	protected int number;
	

	@BeforeClass(groups = { "setup" })
	public void test00Prep01(){
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.changePaginationSettings("100", false);
		
		//task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME01, true);
		TOTAL_SAT_BASE_ENTITLEMENTS = task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, false);
		task_SatelliteTools.deleteAllOrganizations(false);
		
		task_TestPrep.command_generic("touch", "/tmp/wesItWorks", IRhnBase.SERVER_HOSTNAME, false);
		task_TestPrep.command_change_webForceUnentitlement(IRhnBase.SERVER_HOSTNAME,false, false);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false, true, false, false);	
	}

	@Test(groups = { "testplan-Multi1Org-1","sniff" })
	public void test02_createNewOrg(){
		task_RhnBase.createNewOrganization(org2, org2user, org2email, org2paswd, true);
		Assert.assertTrue(rh.isTextPresent("Organization "+org2+" created successfully"));
	}

	@Test(dependsOnMethods="test02_createNewOrg",groups = { "testplan-Multi1Org-1" })
	public void test03_deleteOrg(){
		task_SatelliteTools.deleteOrganization(org2, true);
		Assert.assertTrue(rh.isTextPresent("Organization "+org2+" was successfully deleted."));
		task_RhnBase.createNewOrganization(org2, org2user, org2email, org2paswd, false);
		Assert.assertTrue(rh.isTextPresent("Organization "+org2+" created successfully"));
	}

	@Test(dependsOnMethods="test03_deleteOrg",groups = { "testplan-Multi1Org-1" })
	public void test04_updateOrgDetails(){
		task_SatelliteTools.updateOrganizationName(org2, true, "newName");
		Assert.assertTrue(rh.isTextPresent("Organization newName was successfully updated."));
		task_SatelliteTools.updateOrganizationName("newName", false, org2);
	}

	@Test(dependsOnMethods="test04_updateOrgDetails",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test05_login_asNewOrgAdmin(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		task_RhnBase.clickYourRHN();
		task_RhnBase.clickYourAccount();
		Assert.assertTrue(rh.isTextPresent(org2user));
		rh.clickLink("link="+org2user, true);
		//Assert.assertTrue(rh.isTextPresent("Above roles are granted via the Organization Administrator role."));
	}

	@Test(dependsOnMethods="test05_login_asNewOrgAdmin",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test05_grantSystemEntitlements_Base(){
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "10", true);
		//rh.checkForErrors();
	}

	@Test(dependsOnMethods="test05_grantSystemEntitlements_Base",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test06_removeSystemEntitlements_Base(){
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "0", true);
	}

	@Test(dependsOnMethods="test06_removeSystemEntitlements_Base",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test07_fieldCheck_baseEntitlements_Alpha(){
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "asdf", false);
		if(rh.checkForErrors() == IRhnBase.ERROR_ISE)
			throw new SeleniumException("ISE found on page");
		Assert.assertTrue(rh.isTextPresent("must be a long") || rh.isTextPresent("must be a valid number"));
	}

	@Test(dependsOnMethods="test07_fieldCheck_baseEntitlements_Alpha",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test07_fieldCheck_baseEntitlements_Buffer(){
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "999999999999999999999999999999999999999999999999999999999999999999", false);
		if(rh.checkForErrors() == IRhnBase.ERROR_ISE)
			throw new SeleniumException("ISE found on page");
		Assert.assertTrue(rh.isTextPresent("must be a long") || rh.isTextPresent("must be a valid number"));
	}

	@Test(dependsOnMethods="test07_fieldCheck_baseEntitlements_Buffer",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test08_grantSystemEntitlements_All(){
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "50", true);
		task_SatelliteTools.updateOrgSystemEntitlements(org2, false, IRhnBase.ENTITLEMENT_MONITORING, "40", true);
		task_SatelliteTools.updateOrgSystemEntitlements(org2, false, IRhnBase.ENTITLEMENT_PROVISIONING, "30", true);
		task_SatelliteTools.updateOrgSystemEntitlements(org2, false, IRhnBase.ENTITLEMENT_VIRT, "20", true);
		task_SatelliteTools.updateOrgSystemEntitlements(org2, false, IRhnBase.ENTITLEMENT_VIRT_PLATFORM, "10", true);
		//rh.checkForErrors();
	}
	
	@Test(dependsOnMethods="test08_grantSystemEntitlements_All",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test09_verifiyEntitlementCounts(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, false) == 50);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_PROVISIONING, false) == 30);
	}

	@Test(dependsOnMethods="test09_verifiyEntitlementCounts",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test10_registerSystemTo_Org01(){	
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL+ " --force", org2user, org2paswd, false, true);

	}

	@Test(dependsOnMethods="test10_registerSystemTo_Org01",groups = { "testplan-Multi1Org-1" })
	public void test11_grantChannelEntitlement_Org01(){
		task_TestPrep.changePaginationSettings("50", true);
		task_SatelliteTools.updateOrgSoftwareChannelEntitlements(org2, false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "03", true);
		task_TestPrep.changePaginationSettings("5", false);
	}

	@Test(dependsOnMethods="test11_grantChannelEntitlement_Org01",groups = { "testplan-Multi1Org-1" })
	public void test12_registerSystemToOrg(){
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL+ " --force", org2user, org2paswd, true, true);
	}

	@Test(dependsOnMethods="test12_registerSystemToOrg",groups = { "testplan-Multi1Org-1" })
	public void test12_verifyOrgEntitlement_BaseCounts_Available(){
		//rb.verifyOrgSystemEntitlementCounts(true, "", consumed);
		task_SatelliteTools.verifyOrgSystemEntitlementCounts(true, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE);
	}


	@Test(dependsOnMethods="test12_verifyOrgEntitlement_BaseCounts_Available",groups = { "testplan-Multi1Org-1" })
	public void test13_verifyEntitlement_BaseCounts(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, true) == 1);
	}

	@Test(dependsOnMethods="test13_verifyEntitlement_BaseCounts",groups = { "testplan-Multi1Org-1" })
	public void test14_consume_provisioning_Entitlement(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME01, true);
	}


	@Test(dependsOnMethods="test14_consume_provisioning_Entitlement",groups = { "testplan-Multi1Org-1" },alwaysRun=true)
	public void test15_verifyProvisioning_Consumption(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_PROVISIONING, true) == 1);
	}

	@Test(dependsOnMethods="test15_verifyProvisioning_Consumption",groups = { "testplan-Multi1Org-1" })
	public void test16_consume_allSoftwareChannelEntitlements(){
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"2ndEntitlement" ," --force", org2user, org2paswd, true, true);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"3rdEntitlement" ," --force", org2user, org2paswd, true, false);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"4thEntitlement" ," --force", org2user, org2paswd, false, false);
	}

	@Test(dependsOnMethods="test15_verifyProvisioning_Consumption",groups = { "testplan-Multi1Org-1" })
	public void test17_removeAllSystems(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		task_TestPrep.removeAllSystemProfiles(false);
	}


	@Test(dependsOnMethods="test17_removeAllSystems",groups = { "testplan-Multi1Org-1" })
	public void test18_verifiyEntitlement_Counts(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, true) == 0);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_PROVISIONING, true) == 0);
	}

	@Test(dependsOnMethods="test18_verifiyEntitlement_Counts",groups = { "testplan-Multi1Org-1" })
	public void test19_consume_allSoftwareChannelEntitlements(){
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"1ndEntitlement" ," --force", org2user, org2paswd, true, true);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"2rdEntitlement" ," --force", org2user, org2paswd, true, false);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"3rdEntitlement" ," --force", org2user, org2paswd, true, false);
	}


	@Test(dependsOnMethods="test19_consume_allSoftwareChannelEntitlements",groups = { "testplan-Multi1Org-1" })
	public void test19_verifiy_SystemEntitlement_Counts(){
		task_RhnBase.OpenAndLogIn(org2user, org2paswd);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, true) == 3);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_PROVISIONING, true) == 0);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, false) == 50);
		Assert.assertTrue(task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_PROVISIONING, false) == 30);
	}

	@Test(dependsOnMethods="test19_verifiy_SystemEntitlement_Counts",groups = { "testplan-Multi1Org-1" })
	public void test20_verify_OrgSystemEntitlements_Counts(){
		number = getTotalAvailableSystems();
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "10", true);
		//totalBaseEnt = rb.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, false);
		if(HarnessConfiguration.RHN_BVT.equalsIgnoreCase("0")){
		Assert.assertTrue(task_SatelliteTools.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_TOTAL) == number);
		Assert.assertTrue(task_SatelliteTools.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE) == (number - 10));
		Assert.assertTrue(task_SatelliteTools.verifyOrgSoftwareEntitlementUsage(false, IRhnBase.CHANNEL_RHEL_CORE_SERVER).equalsIgnoreCase("3 of 3 (100%)"));
		}

	}


	@Test(dependsOnMethods="test20_verify_OrgSystemEntitlements_Counts",groups = { "testplan-Multi1Org-1" })
	public void test21_addThreeSystems(){
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"1stBaseEntitlement" ," --force", IRhnBase.USER, IRhnBase.PASSWORD, true, true);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"2ndBaseEntitlement" ," --force", IRhnBase.USER, IRhnBase.PASSWORD, true, false);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"3rdBaseEntitlement" ," --force", IRhnBase.USER, IRhnBase.PASSWORD, true, false);
	}

	@Test(dependsOnMethods="test21_addThreeSystems",groups = { "testplan-Multi1Org-1" })
	public void test22_verifySystemEntitlements_Counts(){
		number = getTotalAvailableSystems();
		Assert.assertTrue((task_SatelliteTools.verifyOrgSystemEntitlementCounts(true, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_TOTAL)) == number);
		Assert.assertTrue((task_SatelliteTools.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (number - 13));
		Assert.assertTrue((task_SatelliteTools.verifyOrgSystemEntitlementUsage(false, IRhnBase.ENTITLEMENT_BASE)).equalsIgnoreCase("6 of 13 (47%)"));
		
		
	}

	@Test(dependsOnMethods="test22_verifySystemEntitlements_Counts",groups = { "testplan-Multi1Org-1" })
	public void test23_verifySoftwareChannelEntitlements(){
		number = getTotalAvailableSystems();
		
		//rb.OpenAndLogIn();
		//rb.changePaginationSettings("50", true);
		Assert.assertTrue((task_RhnBase.verifyOrgSoftwareEntitlementCounts(true, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_TOTAL)) == number);
		Assert.assertTrue((task_RhnBase.verifyOrgSoftwareEntitlementCounts(false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (number - 6));
		Assert.assertTrue((task_RhnBase.verifyOrgSoftwareEntitlementUsage(false, IRhnBase.CHANNEL_RHEL_CORE_SERVER)).equalsIgnoreCase("6 of 6 (100%)"));
	}

	@Test(dependsOnMethods="test23_verifySoftwareChannelEntitlements",groups = { "testplan-Multi1Org-1" })
	public void test24_removeSystemEntitlementsBase_Negative(){
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "0", false);
		//Assert.assertTrue(rh.isTextPresent("Warning: Your Proposed entitlement allocation for Management requires more entitlements than are in use for the autoOrg01 Organization. Please adjust your proposed allocation accordingly."));
		Assert.assertTrue(rh.isTextPresent("Warning: Your Proposed Management allocation provides less entitlements than are currently in use in the autoOrg01 organization. Please adjust your proposed allocation to no less then the amount of entitlements currently in use."));
	}

	@Test(dependsOnMethods="test24_removeSystemEntitlementsBase_Negative",groups = { "testplan-Multi1Org-1" })
	public void test25_TurnOnForceUnentitle_inRhnConf(){
		task_RhnBase.OpenAndLogIn();
		//rb.command_tailLog(IRhnBase.SERVER_HOSTNAME, "/var/log/messages");
		//true, "ssh", "root@"+system +" tail "+logName
		//rb.command_generic("touch", "/tmp/wesItWorks", IRhnBase.SERVER_HOSTNAME, false);
		task_TestPrep.command_change_webForceUnentitlement(IRhnBase.SERVER_HOSTNAME,true, false);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false, true, false, false);
	}

	@Test(dependsOnMethods="test25_TurnOnForceUnentitle_inRhnConf",groups = { "testplan-Multi1Org-1" })
	public void test26_removeSystemEntitlements_Base(){
		task_SatelliteTools.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "0", true);
	}

	@Test(dependsOnMethods="test26_removeSystemEntitlements_Base",groups = { "testplan-Multi1Org-1" })
	public void test27_verifySoftwareChannelEntitlements_Count(){
		number = getTotalAvailableSystems();
		Assert.assertTrue((task_RhnBase.verifyOrgSoftwareEntitlementCounts(true, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_TOTAL)) == number);
		Assert.assertTrue((task_RhnBase.verifyOrgSoftwareEntitlementCounts(false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (number - 6));
		Assert.assertTrue((task_RhnBase.verifyOrgSoftwareEntitlementUsage(false, IRhnBase.CHANNEL_RHEL_CORE_SERVER)).equalsIgnoreCase("3 of 6 (50%)"));
	}
	
	@Test(dependsOnMethods="test27_verifySoftwareChannelEntitlements_Count",groups = { "testplan-Multi1Org-1" })
	public void test28_verifySystemEntitlements(){
		number = getTotalAvailableSystems();
		Assert.assertTrue((task_SatelliteTools.verifyOrgSystemEntitlementCounts(true, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_TOTAL)) == number);
		Assert.assertTrue((task_SatelliteTools.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (number - 3));
		Assert.assertTrue((task_SatelliteTools.verifyOrgSystemEntitlementUsage(false, IRhnBase.ENTITLEMENT_BASE)).equalsIgnoreCase("3 of 3 (100%)"));
	}
	
	
	/////////////////////////check entitlments numbers after changing certs//////////////////
	
	/*
	 * Clear out all systems from all orgs
	 */
	@Test(dependsOnMethods="test28_verifySystemEntitlements",groups = { "testplan-Multi1Org-1" })
	public void test30_removeAllSystemProfiles(){
		number = getTotalAvailableSystems();
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.removeAllSystemProfiles(false);
	/*	rb.SignOut();
		rb.LogIn(org2user, org2paswd);
		rb.removeAllSystemProfiles(false);
		rb.SignOut();
		rb.LogIn(IRhnBase.USER, IRhnBase.PASSWORD);*/
		log.fine("number = "+number);
		Assert.assertTrue((task_SatelliteTools.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == number );
		
	}
	
	@Test(dependsOnMethods="test30_removeAllSystemProfiles",groups = { "testplan-Multi1Org-1" })
	public void test31_consumeAllEntitlments(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("100", false);
		task_SatelliteTools.updateOrgSystemEntitlements(org2, false, IRhnBase.ENTITLEMENT_BASE, "100", true);
		task_SatelliteTools.updateOrgSoftwareChannelEntitlements(org2, false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "100", true);
		for(int x=1;x<101;x++){
			task_TestPrep.registerSystemNOGUI(IRhnBase.SYSTEM_HOSTNAME01, "auto"+x, " --force", org2user, org2paswd);
			rh.sleepForSeconds(1);
		}	
	}
	
	@Test(dependsOnMethods="test31_consumeAllEntitlments",groups = { "testplan-Multi1Org-1" })
	public void test32_deleteOrg(){
		task_SatelliteTools.deleteOrganization(org2, true);
		Assert.assertTrue(rh.isTextPresent("Organization "+org2+" was successfully deleted."));
	}
/*	public void test32UpgradeToALargerCert(){
		rb.OpenAndLogIn();
		//rb.command_generic("rhnreg_ks", "--activationkey=1dfecdf43e83fb8a08ecc9e1e7469c5f --serverUrl=https://xmlrpc.rhn.stage.redhat.com --force",IRhnBase.SERVER_HOSTNAME, false);
		rb.command_generic("rhnreg_ks", " --serverUrl=https://xmlrpc.rhn.webqa.redhat.com   --user=whayutin --pass=redhat --force",IRhnBase.SERVER_HOSTNAME, false);
		rb.command_generic("cat"," /etc/sysconfig/rhn/up2date | grep serverURL=",IRhnBase.SERVER_HOSTNAME,false);
		rb.command_generic("perl"," -p -e 's/webqa/stage/' -i /etc/rhn/rhn.conf",IRhnBase.SERVER_HOSTNAME,false);
		//rb.command_generic("scp", "src/main/resources/shughes-test-virt-z-big.cert", IRhnBase.SERVER_HOSTNAME, false);
 
		//need to scp cert over first
		rb.command_runCommandWithAT(IRhnBase.SERVER_HOSTNAME, "rhn-satellite-activate --rhn-cert=/root/shughes-test-virt-z-big.cert", false);
		rh.sleepForSeconds(600);
	}
	
	public void test33VerifyLargerEntitlements(){
		rb.OpenAndLogIn();
		Assert.assertTrue((rb.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_system -100) );
		Assert.assertTrue((rb.verifyOrgSoftwareEntitlementCounts(true, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_core -100));
		rb.updateOrgSystemEntitlements(org2, false, IRhnBase.ENTITLEMENT_BASE, "110", true);
		rb.updateOrgSoftwareChannelEntitlements(org2, false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "110", true);
		for(int x=1;x<11;x++){
			task_TestPrep.registerSystemNOGUI(IRhnBase.SYSTEM_HOSTNAME01, "2auto"+x, " --force", org2user, org2paswd);
			rh.sleepForSeconds(1);
		}	
		
		Assert.assertTrue((rb.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_system -110) );
		Assert.assertTrue((rb.verifyOrgSoftwareEntitlementCounts(true, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_core -110));
		
	}
	
	
	public void test34ReactivateWithSmallerCert(){
		rb.OpenAndLogIn();
		//this should fail
		//RHN_PARENT: satellite.rhn.webqa.redhat.com
		//ERROR:: You do not have enough entitlements in the base org.

		rb.command_runCommandWithAT(IRhnBase.SERVER_HOSTNAME, "rhn-satellite-activate --rhn-cert=/root/cert.cert", false);
		Assert.assertTrue((rb.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_system -110) );
		Assert.assertTrue((rb.verifyOrgSoftwareEntitlementCounts(false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_core -110));
		rb.updateOrgSystemEntitlements(org2, false, IRhnBase.ENTITLEMENT_BASE, "100", true);
		rb.updateOrgSoftwareChannelEntitlements(org2, false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "100", true);
		Assert.assertTrue((rb.verifyOrgSystemEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_system -100) );
		Assert.assertTrue((rb.verifyOrgSoftwareEntitlementCounts(false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, IRhnBase.ORG_ENTITLEMENT_AVAILABLE)) == (shughesZBigTotal_core -100));
		//this should work
		rb.command_runCommandWithAT(IRhnBase.SERVER_HOSTNAME, "rhn-satellite-activate --rhn-cert=/root/cert.cert", false);
		rh.sleepForSeconds(600);
	}
	*/
	
	@AfterSuite(groups = { "testplan-Multi1Org-1" })
	public void test999_removeSystemProfiles(){
		
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.changePaginationSettings("5", false);
		//rb.deleteOrganization(org2, false);		
	}
	


	
	
	private int getTotalAvailableSystems(){
		int number;
		if(HarnessConfiguration.RHN_BVT.equalsIgnoreCase("0")){
			number = non_bvt_total;
		}
		else{
			number = bvt_total;
		}
		//dirty fix for now.. not using this at this time
		//debugging this now
		return non_bvt_total;
		
	}




}
