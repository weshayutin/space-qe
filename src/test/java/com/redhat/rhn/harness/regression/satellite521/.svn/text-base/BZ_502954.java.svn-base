package com.redhat.rhn.harness.regression.satellite521;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class BZ_502954 extends SeleniumSetup{
	
	protected RhnHelper rh = new RhnHelper();
	protected static String confg_channel_name ="RhnAuto01";
	protected static String bad_system = "bad-system";
	protected static String deployed_fileloc = "/tmp/uploadfile01-import";
	protected static int dummy_systems_count = 3;
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		// cleanup
		task_RhnBase.DeleteConfigChannel(confg_channel_name, false);
		cleanupSystem(IRhnBase.SYSTEM_HOSTNAME01);
		cleanupSystem(IRhnBase.SYSTEM_HOSTNAME02);
		cleanupSystem(IRhnBase.SYSTEM_HOSTNAME03);
		task_TestPrep.removeAllProfilesOfASystem(bad_system);
		for(int i=0;i<dummy_systems_count;i++){
			task_TestPrep.removeAllProfilesOfASystem(IRhnBase.TESTPREFIX+i);
		}
		
		// setup
		task_ConfigMang.CreateNewConfigChannel(confg_channel_name, false, false, true);
		task_RhnBase.uploadFilesToChannel(confg_channel_name, "/etc/redhat-release", 
				false, deployed_fileloc, true, 1);
	}
		
	@Test(groups = { "bz-502954" })
	public void test01_MakeDummySystemsToBeUsedBySSM(){
		task_RhnBase.OpenAndLogIn();
		for(int i=0;i<dummy_systems_count;i++){
			task_TestPrep.registerSystemToSatellite(
					IRhnBase.SYSTEM_HOSTNAME01, true, false);
			task_TestPrep.changeProfileName(IRhnBase.SYSTEM_HOSTNAME01, 
					IRhnBase.TESTPREFIX+i, false);
		}		
	}

	@Test(dependsOnMethods="test01_MakeDummySystemsToBeUsedBySSM", 
			groups = { "bz-502954" })
	public void test02_EnableConfigManagementInSystems(){
		task_RhnBase.OpenAndLogIn();
		enableConfigManagement(IRhnBase.SYSTEM_HOSTNAME01);
		enableConfigManagement(IRhnBase.SYSTEM_HOSTNAME02);
		enableConfigManagement(IRhnBase.SYSTEM_HOSTNAME03);
	}

	@Test(dependsOnMethods="test02_EnableConfigManagementInSystems", 
			groups = { "bz-502954" })
	public void test03_ManageSystemsInSSM(){
		task_RhnBase.OpenAndLogIn();
		for(int i=0;i<dummy_systems_count;i++){
			manageInSSM(IRhnBase.TESTPREFIX+i);
		}
		manageInSSM(IRhnBase.SYSTEM_HOSTNAME01);
		manageInSSM(IRhnBase.SYSTEM_HOSTNAME02);
		manageInSSM(IRhnBase.SYSTEM_HOSTNAME03);
		
		task_TestPrep.changeProfileName(IRhnBase.SYSTEM_HOSTNAME01, bad_system, false);
		enableConfigManagement(IRhnBase.SYSTEM_HOSTNAME01);// htat one was renamed already
		manageInSSM(IRhnBase.SYSTEM_HOSTNAME01);
		manageInSSM(bad_system);
	}
	
	@Test(dependsOnMethods="test03_ManageSystemsInSSM", 
			groups = { "bz-502954" })
	public void test04_MakeFileDeployment(){
		task_RhnBase.OpenAndLogIn();
		page_SSM.open();
		page_SSM.clickLink_Configuation();
		page_SSM.clickLink_DeployFiles();
		page_SSM.check_FirstItemInList();
		page_SSM.clickButton_ScheduleFileDeploy();		
		Assert.assertTrue((rh.checkForErrors()==IRhnBase.ERROR_NO_ERROR_FOUND));		
	}
	
	
	private void cleanupSystem(String sysname){
		task_TestPrep.removeRHNConfigFilesFromSystem(sysname);
		task_TestPrep.removeAllProfilesOfASystem(sysname);
		task_TestPrep.command_generic("rm", "-f "+deployed_fileloc, 
				sysname, false);
	}
	private void enableConfigManagement(String sysname){
		task_TestPrep.registerSystemToSatellite(sysname, true, false);
		task_RhnBase.goToSystem(sysname);
		task_TestPrep.enableRHNConfigManag(sysname, false, false);
		task_ConfigMang.subscribeSystemToChannel(confg_channel_name, sysname,false);		
	}
	private void manageInSSM(String sysname){
		task_RhnBase.goToSystem(sysname);
		page_RhnCommon.check_FirstItemInList();		
	}
}

