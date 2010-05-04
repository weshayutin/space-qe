package com.redhat.rhn.harness.satellite42;

import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

public class SSM extends SeleniumSetup{
	
	protected RhnHelper rh = new RhnHelper();
	
	
	
	
	protected String ssmChannel="ssmchannel";
	protected String rpm1="testAutoFile-1";
	protected String rpm1_full="testAutoFile-1-1.0.i386.rpm";
	
	
	@Test(groups="sniff")
	public void test00Prep01(){
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.registerMultipleProfiles(rh.getTestSystem01Name(),IRhnBase.TESTPREFIX, 1,IRhnBase.RHN_SAT_REG_CMD, true, true, true);
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.registerMultipleProfiles(rh.getTestSystem01Name(),IRhnBase.TESTPREFIX, 1,IRhnBase.RHN_SAT_REG_CMD, true, true, true);
	}
	
	//system names auto0001 - auto00010
	public void test01Prep01(){
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.registerMultipleProfiles(rh.getTestSystem01Name(),IRhnBase.TESTPREFIX, 10,IRhnBase.RHN_SAT_REG_CMD, true, true, true);
	}
	
	public void test01Prep02(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteAllCustomChannels();
		task_ErrataMang.DeleteAllErrata(false);

	}
	
	public void test05SelectedSystems(){
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSystems();
		page_RhnCommon.clickButton_SelectAll();
		assertTrue(rh.isTextPresent("10 systems selected"));
		page_RhnCommon.clickButton_SSM_Manage();
		assertTrue(rh.isTextPresent("System Set Manager"));
		page_RhnCommon.clickButton_SSM_Clear();
		assertTrue(rh.isTextPresent("No systems selected"));
	}
	
	public void test06BasicSSM01(){
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01, HarnessConfiguration.RHN_SAT_REG_CMD, true, false);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02, HarnessConfiguration.RHN_SAT_REG_CMD, true, false);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02, true);
		task_Sdc.takeSnapShot(IRhnBase.SYSTEM_HOSTNAME01, false);
		task_Sdc.takeSnapShot(IRhnBase.SYSTEM_HOSTNAME02,false);
	}
	
	public void test07_SSM_SnapshotTags(){
		task_RhnBase.OpenAndLogIn();
		task_SSM.SSM_TagSystems("groupAuto");

		assertTrue(rh.isTextPresent("Tag added."));
		log.fine("break");
	}
	
	public void test08Prep02_CreateChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.createCustomChannel(ssmChannel);
		task_Channels.rhnPushPackageToChannel(ssmChannel, rpm1_full, "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}

	public void test09_SSM_ChangeBaseChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_SSM.SSM_ChangeBaseChannel(ssmChannel);
		log.fine("break");
	}

	public void test10_SSM_InstallPackages(){
		
		task_RhnBase.OpenAndLogIn();
		task_SSM.SSM_InstallPackage(ssmChannel, rpm1);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
	}

	public void test11_SSM_RemovePackages(){
	
		task_RhnBase.OpenAndLogIn();
		task_SSM.SSM_RemovePackages("testAutoFile");
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_SSM.SSM_TestForPackages("testAutoFile", false);
	}
	
	public void test12_SSM_VerifyPackages(){
		
		task_RhnBase.OpenAndLogIn();
		task_SSM.SSM_InstallPackage(ssmChannel, rpm1);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		task_SSM.SSM_TestForPackages("testAutoFile", true);
		
		task_SSM.SSM_VerifyPackages("testAutoFile");
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);	
	}
	
	
	
	
	


}
