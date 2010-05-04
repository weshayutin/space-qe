package com.redhat.rhn.harness.Space01;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.Sat51.pages.SatelliteToolsPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

@Test(groups="tests")
public class MultiOrg2 extends SeleniumSetup {

	RhnHelper rh = new RhnHelper();
	
	
	
	String[] orgs ={"centralOrg","org1auto","org2auto","org3auto"};
	String[] orgUser ={"centraluser","org1auto","org2auto","org3auto"};

	String centralCustomChannel = "centralcustomchannel";
	String centralClonedChannel = "centralclonedchannel";
	String centralSinglSharedChannel = "centralsinglsharedchannel";
	String centralErrataChannel = "centralerratachannel";
	String centralAutoChannel = "centralautochannel";
	String errataName = "centralerrata01";
	String orgChannel = "20-test";
	String orgEmail = "whayutin@redhat.com";
	String orgPaswd = "dog8code";
	
	String login01 = "testuser01";
	String login02 = "testuser02";
	String login03 = "testuser03";
	String firstName = "auto";
	String lastName = "sat";
	String emailFirst = "me";
	String emailLast = "rhn";

	SatelliteToolsPage page_SatelliteTools = (SatelliteToolsPage)instantiator.getVersionedInstance(SatelliteToolsPage.class);
	
	private int TOTAL_SAT_BASE_ENTITLEMENTS = 0;
	int bvt_total = 20000;
	int non_bvt_total = 100;
	int shughesZBigTotal_system = 123450;
	int shughesZBigTotal_core = 22000;
	int number;
	
//alwaysRun=true

	@BeforeClass(groups = { "setup" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
		
	}
	
	
	@Test(groups = { "setup" })
	public void test00Prep01(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("100", false);
		task_TestPrep.removeAllSystemProfiles(false);
		TOTAL_SAT_BASE_ENTITLEMENTS = task_RhnBase.verifyEntitlementCounts(false, IRhnBase.ENTITLEMENT_BASE, false);
		task_SatelliteTools.deleteAllOrganizations(false);
		task_TestPrep.command_generic("touch", "/tmp/wesItWorks", IRhnBase.SERVER_HOSTNAME, false);
		task_TestPrep.command_change_webForceUnentitlement(IRhnBase.SERVER_HOSTNAME,false, false);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false, true, false, false);	
	}
		
	@Test(groups = { "setup" }, dependsOnMethods="test00Prep01")
	public void test01_MultiOrg2_CreateNewOrgs(){
		
		task_RhnBase.createNewOrganization( orgs[0], orgUser[0], orgEmail, orgPaswd, true);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[0]+" created successfully"));
		
		task_RhnBase.createNewOrganization( orgs[1], orgUser[1], orgEmail, orgPaswd, true);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[1]+" created successfully"));
		task_RhnBase.createNewOrganization( orgs[2], orgUser[2], orgEmail, orgPaswd, true);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[2]+" created successfully"));
		task_RhnBase.createNewOrganization( orgs[3], orgUser[3], orgEmail, orgPaswd, true);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[3]+" created successfully"));
		
		for(int i=0; i<4; i++){
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_BASE, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_MONITORING, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_PROVISIONING, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_VIRT, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_VIRT_PLATFORM, "10", true);
			task_SatelliteTools.updateOrgSoftwareChannelEntitlements(orgs[i], false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "10", true);
		}
	}
	
	@Test( groups = { "setup" },dependsOnMethods="test01_MultiOrg2_CreateNewOrgs")
	public void test02_MultiOrg2_ModifyTrusts(){
		task_RhnBase.OpenAndLogIn();
		task_SatelliteTools.modifyOrgTrusts(orgs[1], orgs[0], true);
		task_SatelliteTools.modifyOrgTrusts(orgs[2], orgs[0], true);
		task_SatelliteTools.modifyOrgTrusts(orgs[3], orgs[0], true);
		task_RhnBase.setTxt_FilterBy(orgs[0]);
		task_RhnBase.clickButton_Filter_Go();
		//assertTrue(rh.getText("xpath=//form/table/tbody/tr/td[4]/a").equalsIgnoreCase("3"));
		assertTrue(rh.isTextPresent("3"));
		log.info("pass, central org has three trusts");
	}
	
	
	
	
	//****** basic-trust
	@Test(alwaysRun=true, dependsOnGroups="setup", groups={ "basic-trust" })
	public void test03_MultiOrg2_CreateCustomChannel(){
		task_RhnBase.OpenAndLogIn(orgUser[0],orgPaswd);
		task_Channels.createCustomChannel(centralCustomChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, null);
		task_Channels.rhnPushPackageToChannel(centralCustomChannel, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_Channels.rhnPushPackageToChannel(centralCustomChannel, "testAutoFile-2-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_Channels.verifyPackageInChannel(centralCustomChannel,"testAutoFile" );
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test03_MultiOrg2_CreateCustomChannel", groups={ "basic-trust" })
	public void test04_MultiOrg2_CheckSharedChannel_InOverview(){
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertTrue(task_RhnBase.checkForSharedChannel(centralCustomChannel, orgs[0]));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test04_MultiOrg2_CheckSharedChannel_InOverview", groups={ "basic-trust" })
	public void test05_MultiOrg2_UtilizeSharedChannel01(){
		task_RhnBase.OpenAndLogIn(orgUser[1], orgPaswd);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL + " --force", orgUser[1], orgPaswd, true, true);
		task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralCustomChannel);
 		assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile"));
		task_Sdc.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile");
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test05_MultiOrg2_UtilizeSharedChannel01", groups={ "basic-trust" })
	public void test06_MultiOrg2_CreateClonedChannel(){
		task_RhnBase.OpenAndLogIn(orgUser[0],orgPaswd);
		task_Channels.deleteCustomChannel(centralClonedChannel);
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, centralClonedChannel, centralClonedChannel, 1);
		task_Channels.modifyChannelAccessControl(centralClonedChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, true);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertTrue(task_RhnBase.checkForSharedChannel(centralClonedChannel, orgs[0]));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test06_MultiOrg2_CreateClonedChannel", groups={ "basic-trust" })
	public void test07_DeleteOrg(){
		task_SatelliteTools.deleteOrganization(orgs[2], true);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[2]+" was successfully deleted."));
		task_RhnBase.createNewOrganization( orgs[2], orgUser[2], orgEmail, orgPaswd, false);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[2]+" created successfully"));
		task_SatelliteTools.modifyOrgTrusts(orgs[2], orgs[0], true);
		//now delete org w/ consuming channel
		log.info("now delete org w/ consuming channel");
		task_SatelliteTools.deleteOrganization(orgs[1], true);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[1]+" was successfully deleted."));
		task_RhnBase.createNewOrganization( orgs[1], orgUser[1], orgEmail, orgPaswd, false);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgs[1]+" created successfully"));
		task_SatelliteTools.modifyOrgTrusts(orgs[1], orgs[0], true);
		
		for(int i=1; i<3; i++){
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_BASE, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_MONITORING, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_PROVISIONING, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_VIRT, "10", true);
			task_SatelliteTools.updateOrgSystemEntitlements(orgs[i], false, IRhnBase.ENTITLEMENT_VIRT_PLATFORM, "10", true);
			task_SatelliteTools.updateOrgSoftwareChannelEntitlements(orgs[i], false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "10", true);
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test07_DeleteOrg", groups={ "basic-trust" })
	public void test08_MultiOrg2_CheckSharedChannelRemoved_InOverview(){
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL + " --force", orgUser[i], orgPaswd, true, true);
			task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralCustomChannel);
			assertTrue(task_RhnBase.checkForSharedChannel(centralCustomChannel, orgs[0]));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralCustomChannel));
		}
		
		task_RhnBase.OpenAndLogIn();
		task_SatelliteTools.modifyOrgTrusts(orgs[1], orgs[0], false);
		task_SatelliteTools.modifyOrgTrusts(orgs[2], orgs[0], false);
		task_SatelliteTools.modifyOrgTrusts(orgs[3], orgs[0], false);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertFalse(task_RhnBase.checkForSharedChannel(centralCustomChannel, orgs[0]));
		}
		
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn();
			task_SatelliteTools.modifyOrgTrusts(orgs[i], orgs[0], true);
		}
		

		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_Channels.modifyChannelAccessControl(centralCustomChannel, IRhnBase.CHANNEL_SHARE_PROTECTED, IRhnBase.CHANNEL_SHARE_ALL_USERS, true);
		
		
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertTrue(task_RhnBase.checkForSharedChannel(centralCustomChannel, orgs[0]));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test08_MultiOrg2_CheckSharedChannelRemoved_InOverview", groups={ "basic-trust" })
	public void test09_MultiOrg2_UtilizeSharedChannel02(){
		task_RhnBase.OpenAndLogIn(orgUser[1], orgPaswd);
		task_ActivationKeys.createActivationKeyWithBaseChannel("sharedChannelKey", "", centralCustomChannel, "10", false, false, false, false, false);
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test09_MultiOrg2_UtilizeSharedChannel02", groups={ "basic-trust" })
	public void test11_MultiOrg2_Subscribers(){
		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL + " --force", orgUser[0], orgPaswd, true, false);
		task_Channels.modifyChannelAccessControl(centralCustomChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_SELECT_USERS, true);
		task_Channels.modifyChannelAccessControl(centralCustomChannel, IRhnBase.CHANNEL_SHARE_PROTECTED, IRhnBase.CHANNEL_SHARE_SELECT_USERS, true);
		task_YourRhn.createUserCustom(login01, firstName, lastName, emailFirst, emailLast);
		task_Channels.addSubscriberToChannel(centralCustomChannel, login01);
		task_YourRhn.createUserCustom(login02, firstName, lastName, emailFirst, emailLast);
		//task_YourRhn.createUserCustom(login03, firstName, lastName, emailFirst, emailLast);
		task_UserRoles.groupDelete("groupAuto");
		task_UserRoles.groupCreate("groupAuto");
		task_UserRoles.addAdminToSystemGroup(IRhnBase.SYSTEM_HOSTNAME01, "groupAuto", 2);
		task_UserRoles.addSystemToSystemGroup(IRhnBase.SYSTEM_HOSTNAME01, "groupAuto");  
		page_RhnCommon.LogIn(login01, orgPaswd);
		task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralCustomChannel);
		assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralCustomChannel));
		task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.RHN_CHANNEL01);
		assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.RHN_CHANNEL01));
		
		page_RhnCommon.LogIn(login02, orgPaswd);
		try{
			task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralCustomChannel);
		}
		catch(SeleniumException se){
			log.info("SUCCESS: was unable to subscribe to channel");
		}
		assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralCustomChannel));
		
	
	}
	
	
	

	//****** shared-single-channel
	@Test(alwaysRun=true, dependsOnGroups={"setup","basic-trust"}, groups = { "shared-single-channel" })
	public void test08_00_MultiOrg2_CheckSharedChannel_InOverview(){
		Reset_Channels01();
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL + " --force", orgUser[i], orgPaswd, true, true);
			task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel);
			assertTrue(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test08_00_MultiOrg2_CheckSharedChannel_InOverview", groups={ "shared-single-channel" })
	public void test08_01_MultiOrg2_CheckSharedChannelRemoved_InOverview(){
		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_Channels.modifyChannelAccessControl(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PRIVATE, IRhnBase.CHANNEL_SHARE_ALL_USERS, false);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertFalse(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test08_01_MultiOrg2_CheckSharedChannelRemoved_InOverview", groups={ "shared-single-channel" })
	public void test08_02_MultiOrg2_CheckSharedChannel_InOverview(){
		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_Channels.modifyChannelAccessControl(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PROTECTED, IRhnBase.CHANNEL_SHARE_ALL_USERS, true);
		
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel);
			assertTrue(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test08_02_MultiOrg2_CheckSharedChannel_InOverview", groups={ "shared-single-channel" })
	public void test08_03_MultiOrg2_CheckSharedChannel_InOverview(){
		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_Channels.modifyChannelAccessControl(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, true);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertTrue(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test08_03_MultiOrg2_CheckSharedChannel_InOverview", groups={ "shared-single-channel" })
	public void test08_04_MultiOrg2_CheckSharedChannelRemoved_InOverview(){
		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_Channels.modifyChannelAccessControl(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PROTECTED, IRhnBase.CHANNEL_SHARE_ALL_USERS, false);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertFalse(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
		}
	}
	
		
	
	//****** system-migration
	@Test(alwaysRun=true, dependsOnGroups={"setup","shared-single-channel"}, groups = { "system-migration" })
	public void test03_SystemMigration_00(){
		task_RhnBase.OpenAndLogIn();
			task_SatelliteTools.modifyOrgTrusts(orgs[1], orgs[0], true);
			task_SatelliteTools.modifyOrgTrusts(orgs[1], orgs[2], true);
			task_SatelliteTools.modifyOrgTrusts(orgs[1], orgs[3], true);
			
			task_SatelliteTools.modifyOrgTrusts(orgs[2], orgs[0], true);
			task_SatelliteTools.modifyOrgTrusts(orgs[2], orgs[1], true);
			task_SatelliteTools.modifyOrgTrusts(orgs[2], orgs[3], true);
			
			task_SatelliteTools.modifyOrgTrusts(orgs[3], orgs[0], true);
			task_SatelliteTools.modifyOrgTrusts(orgs[3], orgs[1], true);
			task_SatelliteTools.modifyOrgTrusts(orgs[3], orgs[2], true);
		
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test03_SystemMigration_00", groups={ "system-migration" })
	public void test03_SystemMirgration_01(){
		String systemID;
		String[] orgIDs ={"orgID0","orgID1","orgID2","orgID3"};
		
		task_RhnBase.OpenAndLogIn();
		for(int i=0; i < orgs.length; i++){
		task_SatelliteTools.goToOrganization(orgs[i], false);
		orgIDs[i] = page_SatelliteTools.getOrgID();
		}
		for(int i=0; i < orgs.length; i++){
			log.info("org id"+i+" ="+orgIDs[i]);
		}
		task_RhnBase.LogIn(orgUser[0], orgPaswd);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL + " --force", orgUser[0], orgPaswd, true, true);
		task_Search.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		systemID = task_Search.getDetails_System_ID();
		log.info("System id = "+systemID);
		
		for(int i=1; i <= orgs.length - 1; i++){
			int oldOrg = i - 1;
			task_TestPrep.command_migrateSystemProfile(IRhnBase.SERVER_HOSTNAME, systemID, orgIDs[i]);
			task_RhnBase.LogIn(orgUser[i], orgPaswd);
			task_TestPrep.verifySystemProfile(IRhnBase.SYSTEM_HOSTNAME01);
			
			task_RhnBase.LogIn(orgUser[oldOrg], orgPaswd);
			page_SatelliteSystems.open();
			assertTrue(rh.isTextPresent("No systems"));
		}
	}
	
   
	
	
	//****** shared-errata-channel
	@Test(alwaysRun=true, dependsOnGroups={"setup","system-migration"}, groups = { "shared-errata-channel" })
	public void test20_MultiOrg2_CreateCustomErrataChannel(){
		
		task_RhnBase.OpenAndLogIn(orgUser[0],orgPaswd);
		task_Channels.deleteCustomChannel(centralErrataChannel);
		task_Channels.deleteCustomChannel(centralAutoChannel);
		task_Channels.createCustomChannel(centralAutoChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, null);
		task_Channels.rhnPushPackageToChannel(centralAutoChannel, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_Channels.rhnPushPackageToChannel(centralAutoChannel, "testAutoFile-2-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_Channels.verifyPackageInChannel(centralAutoChannel,"testAutoFile-1" );
		task_Channels.verifyPackageInChannel(centralAutoChannel,"testAutoFile-2" );
		
		
		task_Channels.createCustomChannel(centralErrataChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, null);
		task_Channels.rhnPushPackageToChannel(centralErrataChannel, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_RhnBase.OpenAndLogIn(orgUser[1], orgPaswd);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ," --force", orgUser[1], orgPaswd, true, true);
		task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralErrataChannel);
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile");
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test20_MultiOrg2_CreateCustomErrataChannel", groups={ "shared-errata-channel" })
	public void test21_MultiOrg2_CreateCustomErrata(){
		task_RhnBase.OpenAndLogIn(orgUser[0],orgPaswd);
		task_ErrataMang.CreateNewErrata(errataName);
		task_ErrataMang.addPackageToUnPubErrataAndPublishToChannel(errataName, centralErrataChannel,"testAutoFile-2-1.0:0-i386");
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test21_MultiOrg2_CreateCustomErrata", groups={ "shared-errata-channel" })
	public void test22_installCustomErrata_FromCustomSharedChannel(){
		task_RhnBase.OpenAndLogIn(orgUser[1], orgPaswd);
		task_Sdc.installErrata(IRhnBase.SYSTEM_HOSTNAME01,errataName, "testAutoFile-2-1.0:0", "testAutoFile-1-1.0:0", "testAutoFile");
		
	}
	

	
	
	
	
	//delete providing org... after all consuming orgs.. consume something.
	
	
	//kickstart
	//install package from shared channel after kickstart
	

	//****** activation-key
	@Test(alwaysRun=true, dependsOnGroups={"setup","shared-errata-channel"}, groups = { "activation-key" })
	public void test30_ActivationKeys_create01(){
		Reset_Channels01();
			task_RhnBase.OpenAndLogIn(orgUser[1], orgPaswd);
			assertTrue(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
			task_ActivationKeys.deleteActivationKey("autotest01", false);
			task_ActivationKeys.createActivationKeyWithBaseChannel("autotest01", "12345", centralSinglSharedChannel, "10", false, true, false, false, false);
			task_ActivationKeys.addChildChannelToKey("autotest01", "child1", "child2", "child3");
			
			String activationKeyID = task_ActivationKeys.getActivationKey_ID("autotest01");
			task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"prof01","--activationkey="+activationKeyID+" --force",orgUser[1], IRhnBase.PASSWORD,true, true);
			task_ActivationKeys.isSystemActivated("prof01", "autotest01");
			assertTrue(task_Sdc.check_SDC_Channel(false, "prof01", centralSinglSharedChannel));
			assertTrue(task_Sdc.check_SDC_Channel(false, "prof01", "child1"));
			assertTrue(task_Sdc.check_SDC_Channel(false, "prof01", "child2"));
			assertTrue(task_Sdc.check_SDC_Channel(false, "prof01", "child3"));
		
		
	}
	
	
	//****** child-channels
	@Test(alwaysRun=true, dependsOnGroups={"setup","activation-key"}, groups={ "child-channels" })
	public void test40_00_MultiOrg2_CheckSharedChild_Channel_InOverview(){
		Reset_Channels01();
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL + " --force", orgUser[i], orgPaswd, true, true);
			task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel);
			task_Sdc.alterChildChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, "child1"); //will select all child channels
			assertTrue(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertTrue(task_RhnBase.checkForSharedChannel("child1", orgs[0]));
			assertTrue(task_RhnBase.checkForSharedChannel("child2", orgs[0]));
			assertTrue(task_RhnBase.checkForSharedChannel("child3", orgs[0]));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child1"));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child2"));
			assertTrue(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child3"));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test40_00_MultiOrg2_CheckSharedChild_Channel_InOverview", groups={ "child-channels" })
	public void test40_01_MultiOrg2_CheckSharedChannelRemoved_InOverview(){
		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_Channels.modifyChannelAccessControl(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PRIVATE, IRhnBase.CHANNEL_SHARE_ALL_USERS, false);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			assertFalse(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			assertFalse(task_RhnBase.checkForSharedChannel("child1", orgs[0]));
			assertFalse(task_RhnBase.checkForSharedChannel("child2", orgs[0]));
			assertFalse(task_RhnBase.checkForSharedChannel("child3", orgs[0]));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child1"));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child2"));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child3"));
		}
	}
	
	@Test(dependsOnGroups="setup",dependsOnMethods="test40_01_MultiOrg2_CheckSharedChannelRemoved_InOverview", groups={ "child-channels" })
	public void test40_02_MultiOrg2_CheckSharedChannelRemoved_InOverview(){
		Reset_Channels01();
		task_RhnBase.OpenAndLogIn(orgUser[0], orgPaswd);
		task_Channels.modifyChannelAccessControl(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PRIVATE, IRhnBase.CHANNEL_SHARE_ALL_USERS, false);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			
			assertFalse(task_RhnBase.checkForSharedChannel(centralSinglSharedChannel, orgs[0]));
			 // even though the public parent channel was modified to private, the children were created as public and therefore remains public
			assertTrue(task_RhnBase.checkForSharedChannel("child1", orgs[0]));
			assertTrue(task_RhnBase.checkForSharedChannel("child2", orgs[0]));
			assertTrue(task_RhnBase.checkForSharedChannel("child3", orgs[0]));
			
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel));
			 // from the perspective of a system, if it's base channel was modified to private, then he can no longer see it nor the base's child channels		
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child1"));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child2"));
			assertFalse(task_Sdc.check_SDC_Channel(false, IRhnBase.SYSTEM_HOSTNAME01, "child3"));
		}
	}
	
	//test bi-directional
	
	
	
	
	/*	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
		
	}*/
	
	/*	@AfterSuite
	public void test999Prep01(){
		
		rb.removeAllSystemProfiles(true);
		rb.changePaginationSettings("5", false);
		rb.deleteOrganization(org2, false);
		
		
	}*/
	
	private void Reset_Channels01(){
		task_RhnBase.OpenAndLogIn(orgUser[0],orgPaswd);
		//task_Channels.deleteCustomChannel(centralSinglSharedChannel);
		task_Channels.deleteAllCustomChannels();
		task_Channels.createCustomChannel(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, null);
		
		task_Channels.createCustomChannel("child1", IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, centralSinglSharedChannel);
		task_Channels.createCustomChannel("child2", IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, centralSinglSharedChannel);
		task_Channels.createCustomChannel("child3", IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, centralSinglSharedChannel);
		
		task_Channels.rhnPushPackageToChannel(centralSinglSharedChannel, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_Channels.rhnPushPackageToChannel(centralSinglSharedChannel, "testAutoFile-2-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_Channels.verifyPackageInChannel(centralSinglSharedChannel,"testAutoFile" );
		task_Channels.deleteCustomChannel(orgChannel);
		task_Channels.createCustomChannel(orgChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, null);
		task_Channels.rhnPushPackageToChannel(orgChannel, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", "http://"+IRhnBase.SERVER_HOSTNAME+"/APP", orgUser[0],orgPaswd);
		task_Channels.verifyPackageInChannel(orgChannel,"testAutoFile" );
		
		
		
		task_Channels.modifyChannelAccessControl(centralSinglSharedChannel, IRhnBase.CHANNEL_SHARE_PUBLIC, IRhnBase.CHANNEL_SHARE_ALL_USERS, true);
		for(int i=1;i<4;i++){
			task_RhnBase.OpenAndLogIn(orgUser[i], orgPaswd);
			task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
			task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01 ,IRhnBase.RHN_REG_SAT_URL + " --force", orgUser[i], orgPaswd, true, true);
			task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, centralSinglSharedChannel);
		}
		
		
	
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
