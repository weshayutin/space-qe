package com.redhat.rhn.harness.satellite42;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

public class ErrataManagement extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	
	
	
	protected static String clonedChannel01 = "autoclone01";
	protected static String autoErrata01 = "autoerrata01";
	protected static String autoErrata02 = "autoerrata02";
	protected static String autoErrataDEL = "autoerrataDEL";
	protected static String autoChannelDEL = "autochanneldel";
	protected static String errataprep = "autoerrataprep";
	protected static String autoErrataChannel01 = "autoerratachannel01";
	


	@Test(groups = { "custom-channel" })
	public void test00_DisplayPages(){
			task_RhnBase.OpenAndLogIn();
			task_ErrataMang.DisplayErrataOverviewPage();
			task_ErrataMang.DisplayAllErrataPage();
			task_ErrataMang.DisplayReleventErrataPage();
			task_ErrataMang.DisplayManageErrataPage();
			task_ErrataMang.DisplayPublishedErrataPage();
			task_ErrataMang.DisplayUnpublishedErrataPage();
	}

	//setup for custom-channel
	@Test(groups = { "custom-channel" }, alwaysRun=true)
	public void test01Prep01(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
		task_TestPrep.command_rpm(" -e testAutoFile-1-1.0 --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e testAutoFile-2-1.0", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
		assertFalse(task_TestPrep.command_rpmQuery(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile-1"));
		assertFalse(task_TestPrep.command_rpmQuery(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile-2"));
		task_Channels.deleteAllCustomChannels();
		task_ErrataMang.DeleteAllErrata(false);
	}
	
	@Test(dependsOnMethods="test01Prep01",groups = { "custom-channel" })
	public void test08_CreateCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel(errataprep);
		task_Channels.createCustomChannel(errataprep);
	}
	
	@Test(dependsOnMethods="test08_CreateCustomChannel",groups = { "custom-channel" })
	public void test09_RhnPushPackage_toCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(errataprep, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
		task_Channels.rhnPushPackageToChannel(errataprep, "testAutoFile-2-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME,IRhnBase.USER, IRhnBase.PASSWORD);
	}
	
	@Test(dependsOnMethods="test09_RhnPushPackage_toCustomChannel",groups = { "custom-channel" })
	public void test11_Prep03(){	
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
		task_TestPrep.command_rpm(" -e testAutoFile-1-1.0 --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e testAutoFile-2-1.0 --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
		
	}
	
	@Test(dependsOnMethods="test11_Prep03",groups = { "custom-channel" })
	public void test12_CreateCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.createCustomChannel(autoErrataChannel01);
	}
	
	@Test(dependsOnMethods="test12_CreateCustomChannel",groups = { "custom-channel" })
	public void test13_RhnPushPackage_toCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(autoErrataChannel01, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}
	
	@Test(dependsOnMethods="test13_RhnPushPackage_toCustomChannel",groups = { "custom-channel" })
	public void test14_subscribeSystem_ToCustomBaseChannel(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, autoErrataChannel01);
	}
	
	@Test(dependsOnMethods="test14_subscribeSystem_ToCustomBaseChannel",groups = { "custom-channel" })
	public void test15_installPackage_FromCustomBaseChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile");
		assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile"));
		
	}
	
	@Test(dependsOnMethods="test15_installPackage_FromCustomBaseChannel",groups = { "custom-channel" })
	public void test16_createNewErrata(){
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.CreateNewErrata(autoErrata01);
	}
	
	@Test(dependsOnMethods="test16_createNewErrata",groups = { "custom-channel" })
	public void test17_AddPackage_toCustomErrata(){
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.addPackageToUnPubErrataAndPublishToChannel(autoErrata01, autoErrataChannel01, "testAutoFile-2");
	}
	
	//Bugzilla created bz#480073 and bz#480076
	@Test(dependsOnMethods="test17_AddPackage_toCustomErrata",groups = { "custom-channel" })
	public void test20_installErrata_FromCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installErrata(IRhnBase.SYSTEM_HOSTNAME01,autoErrata01, "testAutoFile-2-1.0:0", "testAutoFile-1-1.0:0", "testAutoFile");
	}
	
	


	//setup for cloned-channel
	@Test(dependsOnGroups="custom-channel",groups = { "cloned-channel" }, alwaysRun=true)
	public void test30_PrepForClonedChannel(){	
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem02Name(), true);
		task_TestPrep.command_rpm(" -e testAutoFile-1-1.0 --nodeps", IRhnBase.SYSTEM_HOSTNAME02, false);
		task_TestPrep.command_rpm(" -e testAutoFile-2-1.0", IRhnBase.SYSTEM_HOSTNAME02, false);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true, true);
		assertFalse(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME02, "testAutoFile"));
		assertFalse(task_TestPrep.command_rpmQuery(IRhnBase.SYSTEM_HOSTNAME02, "testAutoFile-1"));
		assertFalse(task_TestPrep.command_rpmQuery(IRhnBase.SYSTEM_HOSTNAME02, "testAutoFile-2"));
		task_Channels.deleteCustomChannel(clonedChannel01);
		
	}
	
	@Test(dependsOnMethods="test30_PrepForClonedChannel",groups = { "cloned-channel" })
	public void test31_createClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.createChannelClone(autoErrataChannel01,clonedChannel01 ,clonedChannel01, 1);
	}
	
	@Test(dependsOnMethods="test31_createClonedChannel",groups = { "cloned-channel" })
	public void test32_RhnPushPackage_toClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(clonedChannel01, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}
	
	@Test(dependsOnMethods="test32_RhnPushPackage_toClonedChannel",groups = { "cloned-channel" })
	public void test33_subscribeSystem_toClonedBaseChannel(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME02);
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME02, clonedChannel01);
	}
	
	@Test(dependsOnMethods="test33_subscribeSystem_toClonedBaseChannel",groups = { "cloned-channel" })
	public void test34_installPackage_fromClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME02, "testAutoFile");
		assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME02, "testAutoFile"));
	}
	
	@Test(dependsOnMethods="test34_installPackage_fromClonedChannel",groups = { "cloned-channel" })
	public void test35_createNewErrata(){
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.CreateNewErrata(autoErrata02);
	}
	
	@Test(dependsOnMethods="test35_createNewErrata",groups = { "cloned-channel" })
	public void test36_addPackageToErrata(){
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.addPackageToUnPubErrataAndPublishToChannel(autoErrata02, clonedChannel01,"testAutoFile-2-1.0:0-i386");
	}
	
	@Test(dependsOnMethods="test36_addPackageToErrata",groups = { "cloned-channel" })
	public void test37_installErrata_fromClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installErrata(IRhnBase.SYSTEM_HOSTNAME02,autoErrata02, "testAutoFile-2-1.0:0", "testAutoFile-1-1.0:0", "testAutoFile");
	}
	
	
	
	//setup for delete-channel
	@Test(dependsOnGroups="cloned-channel", groups = { "delete-channel" }, alwaysRun=true)
	public void test50_createNewErrata(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, false);
		task_Sdc.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile");
		task_Channels.createCustomChannel(autoChannelDEL);
		task_Channels.rhnPushPackageToChannel(autoChannelDEL, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
		task_Search.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, autoChannelDEL);
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile-1");
		task_ErrataMang.CreateNewErrata(autoErrataDEL);
	}
	
	@Test(dependsOnMethods="test50_createNewErrata", groups = { "delete-channel" })
	public void test51_AddPackage_toCustomErrata(){
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.addPackageToUnPubErrataAndPublishToChannel(autoErrataDEL, autoChannelDEL,"testAutoFile-2");
	}
	
	//Bugzilla created bz#480073 and bz#480076
	@Test(dependsOnMethods="test51_AddPackage_toCustomErrata", groups = { "delete-channel" })
	public void test54_installErrata_FromCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installErrata(IRhnBase.SYSTEM_HOSTNAME01,autoErrataDEL, "testAutoFile-2-1.0:0", "testAutoFile-1-1.0:0", "testAutoFile");
	}
	
	@Test(dependsOnMethods="test54_installErrata_FromCustomChannel", groups = { "delete-channel" })
	public void test55_DeletePackage_fromPublishedErrata() {
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.deletePackageFromPublishedErrata(autoErrataDEL, autoChannelDEL, "testAutoFile-2-1.0:0-i386");
	}
	
	
	@Test(dependsOnMethods="test55_DeletePackage_fromPublishedErrata", groups = { "delete-channel" })
	public void test56_addChannel_toPublishedErrata() {
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.addChannelToPublishedErrata(autoErrataDEL, autoErrataChannel01);
	}
	
	@Test(dependsOnMethods="test56_addChannel_toPublishedErrata", groups = { "delete-channel" })
	public void test57_deleteChannel_fromPublishedErrata() {
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.deleteChannelFromPublishedErrata(autoErrataDEL, autoErrataChannel01);
	}
	
	@Test(dependsOnMethods="test57_deleteChannel_fromPublishedErrata", groups = { "delete-channel" })
	public void test58_delete_All_Errata() {
		task_RhnBase.OpenAndLogIn();
		task_ErrataMang.DeleteAllErrata(true);
		//assertFalse(rh.isTextPresent("Internal Server Error"));
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
	}
	
	
}
