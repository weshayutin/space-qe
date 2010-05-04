package com.redhat.rhn.harness.satellite42;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class Channels extends SeleniumSetup{


	protected RhnHelper rh = new RhnHelper();
	
	

	
	
	
	
	protected static String channelError01 = "Invalid channel label 'autoChannel01' - must be at least 6 characters long, begin with a letter, and contain only lowercase letters, digits, '-', '_', and '.'";
	protected static String autoclonechannel01 = "autoclonechannel01";
	protected static String customChannel01 = "autocustomchannel01";
	//protected static String channelError01 = "Invalid channel label 'autoChannel01' - must be at least 6 characters long, begin with a letter, and contain only lowercase letters, digits, '-', '_', and '.'";
	protected static String autochannel01 = "autochannel01";

	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, false);
		task_Sdc.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile");
		task_TestPrep.command_rpm(" -e testAutoFile-1-1.0 --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e testAutoFile-2-1.0", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_Channels.deleteAllCustomChannels();
		task_ErrataMang.DeleteAllErrata(false);
	
	}


	@Test(groups = { "testplan-SoftwareChannelManagement" }) 
	public void test01_VerifyBaseChannel01(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyChannelExists(IRhnBase.RHN_CHANNEL01, true);
	}
	
	@Test(dependsOnMethods="test01_VerifyBaseChannel01", groups = { "testplan-SoftwareChannelManagement" })
	public void test02_VerifyBaseChannel02(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyChannelExists(IRhnBase.RHN_CHANNEL02, true);
	}
	
	@Test(groups={"sniff"})//#472432
	public void test0300CreateClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, "sniffchannel","sniffchannel", 0);
	}

	@Test(dependsOnMethods="test02_VerifyBaseChannel02", groups = { "testplan-SoftwareChannelManagement" })
	public void test03_CreateClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, autochannel01,autochannel01, 0);
	}

	@Test(dependsOnMethods="test03_CreateClonedChannel", groups = { "testplan-SoftwareChannelManagement" })
	public void test04_RhnPushPackage_toClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(autochannel01, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}

	@Test(dependsOnMethods="test04_RhnPushPackage_toClonedChannel", groups = { "testplan-SoftwareChannelManagement" })
	public void test05_DeleteClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel(autochannel01);
		task_Channels.verifyChannelExists(autochannel01, false);
	}

	@Test(dependsOnMethods="test05_DeleteClonedChannel", groups = { "testplan-SoftwareChannelManagement"})
	public void test10_CreateCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.createCustomChannel(customChannel01);
	}

	@Test(dependsOnMethods="test10_CreateCustomChannel", groups = { "testplan-SoftwareChannelManagement"})
	public void test11_RhnPushPackage_toCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(customChannel01, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}

	@Test(dependsOnMethods="test11_RhnPushPackage_toCustomChannel", groups = { "testplan-SoftwareChannelManagement"})
	public void test12_VerifyPushedPackage_InCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel(customChannel01,"testAutoFile" );
		Assert.assertTrue(rh.isTextPresent("RHN test automation, test rpm file"));
	}

	@Test(dependsOnMethods="test12_VerifyPushedPackage_InCustomChannel", groups = { "testplan-SoftwareChannelManagement"})
	public void test13_SubscribeSystem_ToCustomBaseChannel(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, customChannel01);
	}

	@Test(dependsOnMethods="test13_SubscribeSystem_ToCustomBaseChannel", groups = { "testplan-SoftwareChannelManagement"})
	public void test14_InstallPackage_FromCustomBaseChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile");
	}

	@Test(dependsOnMethods="test14_InstallPackage_FromCustomBaseChannel", groups = { "testplan-SoftwareChannelManagement"})
	public void test15_RhnPushNewVersionOfPackage_ToCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(customChannel01, "testAutoFile-2-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}

	@Test(dependsOnMethods="test15_RhnPushNewVersionOfPackage_ToCustomChannel", groups = { "testplan-SoftwareChannelManagement"})
	public void test16_VerifyNewVersionOfPackage_InCustomChannel(){	
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel(customChannel01,"testAutoFile-2" );
		Assert.assertTrue(rh.isTextPresent("RHN test automation, test rpm file"));
		Assert.assertTrue(rh.isTextPresent("testAutoFile-2"));
	}
	
	//bz# 477530
	@Test(groups = { "testplan-SoftwareChannelManagement"})
	public void test17_DisableBaseChannel_ResubscribeToBaseChannel(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		//Custom base channel
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, "(none, disable service)");
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, customChannel01);
		//RedHat base channel
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.RHN_CHANNEL01);
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, "(none, disable service)");
		task_Sdc.changeBaseChannel(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.RHN_CHANNEL01);
	}

}
