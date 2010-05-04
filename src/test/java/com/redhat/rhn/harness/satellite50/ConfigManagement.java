package com.redhat.rhn.harness.satellite50;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class ConfigManagement extends SeleniumSetup{

	RhnHelper rh = new RhnHelper();
    
    @BeforeClass(groups = { "setup" })
	public void test01_Prep01(){
		task_ConfigMang.deleteAllConfigChannels(true);
		task_TestPrep.removeAllSystemProfiles(false);
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
	}


    @Test(groups="sniff")
	public void test02_createNewConfigChannel(){
		task_ConfigMang.CreateNewConfigChannel("sniff", true, false, true);
	}

    @Test(groups="testplan-ConfigurationManagement")
	public void test05_enable_RHNConfigMang(){
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, true);
	}

	@Test(dependsOnMethods="test05_enable_RHNConfigMang",groups = { "testplan-ConfigurationManagement" })
	public void test07_createNew_configChannel(){
		task_RhnBase.DeleteConfigChannel("RhnAuto01", true);
		task_ConfigMang.CreateNewConfigChannel("RhnAuto01", true, false, true);
	}

	@Test(dependsOnMethods="test07_createNew_configChannel",groups = { "testplan-ConfigurationManagement" })
	public void test08_Create_ConfigChannel(){
		task_RhnBase.DeleteConfigChannel("RhnAuto01", true);
		task_RhnBase.CreateNewConfigChannel("RhnAuto01", false, false, true);
	}

	@Test(dependsOnMethods="test08_Create_ConfigChannel",groups = { "testplan-ConfigurationManagement" })
	public void test09_fieldCheck_CreateConfigChannel(){
		task_ConfigMang.ConfigChannelErrorMsg01();
	}

	@Test(dependsOnMethods="test09_fieldCheck_CreateConfigChannel",groups = { "testplan-ConfigurationManagement" })
	public void test10_createDuplicate_ConfigChannel(){
		String myChannel = "duplicate";
		task_RhnBase.CreateNewConfigChannel(myChannel, true, true, true);
		task_RhnBase.CreateNewConfigChannel(myChannel, false, false, false);
		Assert.assertTrue(rh.isTextPresent("Label 'duplicate' already exists. Please choose a different label for the new channel."));
	}

	@Test(dependsOnMethods="test10_createDuplicate_ConfigChannel",groups = { "testplan-ConfigurationManagement" })
	public void test11_uploadFiles_toChannel01(){	
		task_RhnBase.CreateNewConfigChannel("UpLoadFile01", true, true, true);
		task_RhnBase.removeConfigFile("UploadFile01", "/tmp/redhat-release", false, false);
		task_RhnBase.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", false, "/tmp/redhat-release", true, 1);
	}

	@Test(dependsOnMethods="test11_uploadFiles_toChannel01",groups = { "testplan-ConfigurationManagement" })
	public void test11_pploadFiles_toChannel_negative(){
		task_RhnBase.CreateNewConfigChannel("UpLoadFile01", true, true, true);
		task_RhnBase.removeConfigFile("UploadFile01", "/tmp/uploadfile01-import", false, false);
		task_RhnBase.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", false, "/tmp/uploadfile01-import", true, 1);
		task_RhnBase.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", false, "/tmp/uploadfile01-import", false, 1);
		Assert.assertTrue(rh.isTextPresent("A file named \"/tmp/uploadfile01-import\" already exists in this channel."));
	}

	@Test(dependsOnMethods="test11_pploadFiles_toChannel_negative",groups = { "testplan-ConfigurationManagement" })
	public void test11_uploadFiles_toChannel_Binary(){
		task_RhnBase.CreateNewConfigChannel("UpLoadFile01", true, true, true);
		task_RhnBase.removeConfigFile("UploadFile01", "/tmp/binary", false, false);
		task_RhnBase.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", true, "/tmp/binary", true, 1);
	}

	@Test(dependsOnMethods="test11_uploadFiles_toChannel_Binary",groups = { "testplan-ConfigurationManagement" })
	public void test11_uploadFiles_toChannel_Binary_andEdit(){
		String myChannel = "UploadFile01";
		String myFile = "/tmp/updatebin";
		
		task_RhnBase.CreateNewConfigChannel(myChannel, true, true, true);
		task_RhnBase.removeConfigFile(myChannel, myFile, false, false);
		task_RhnBase.uploadFilesToChannel(myChannel, "/etc/redhat-release", true, myFile, true, 1);

		task_RhnBase.updateConfigurationFile(myChannel, myFile, "", 2, false, true);
		task_RhnBase.updateConfigurationFile(myChannel, myFile, "", 3, false, true);
		task_RhnBase.updateConfigurationFile(myChannel, myFile, "", 4, false, true);

	}

	@Test(dependsOnMethods="test11_uploadFiles_toChannel_Binary_andEdit",groups = { "testplan-ConfigurationManagement" })
	public void test12_createFile_inChannel(){
		task_ConfigMang.CreateFileInChannel("CreateFile01", "/tmp/uploadfile01-import", "This file was created by automation");
	}

	@Test(dependsOnMethods="test12_createFile_inChannel",groups = { "testplan-ConfigurationManagement" })
	public void test12_importFiles_toChannel(){
		task_ConfigMang.ImportFilesToChannel("ImportFile01", "/tmp/uploadfile01");
	}

	@Test(dependsOnMethods="test12_importFiles_toChannel",groups = { "testplan-ConfigurationManagement" })
	public void test13_createFile_InChannel_negativeSetup(){
		task_ConfigMang.CreateFileInChannel("CreateFile01", "/tmp/createdfile01", "This file was created by automation");
	}

	@Test(dependsOnMethods="test13_createFile_InChannel_negativeSetup",groups = { "testplan-ConfigurationManagement" })
	public void test14_createFile_InChannel_negativeTest(){
		task_ConfigMang.CreateFileInChannelWithError("RhnAuto01", "shouldFail", "This file was created by automation");
	}

	@Test(dependsOnMethods="test14_createFile_InChannel_negativeTest",groups = { "testplan-ConfigurationManagement" })
	public void test15_createDirectory_inChannel(){
		task_ConfigMang.CreateDirectoryInChannel("CreateDir01", "/tmp/testdir01");
	}

	@Test(dependsOnMethods="test15_createDirectory_inChannel",groups = { "testplan-ConfigurationManagement" })
	public void test16_createDuplicateDirectory_inChannel(){
		task_ConfigMang.CreateDuplicateDirectoryInChannel("DupDir", "/tmp/testdupdir01-qaz");
	}

	@Test(dependsOnMethods="test16_createDuplicateDirectory_inChannel",groups = { "testplan-ConfigurationManagement" })
	public void test17_createMultipleRevisions_ofDirectory_inChannel(){
		task_ConfigMang.CreateMultipleRevisionOfDirectoryInChannel("DupDir", "/tmp/testdupdir01-qaz");
	}

	@Test(dependsOnMethods="test17_createMultipleRevisions_ofDirectory_inChannel",groups = { "testplan-ConfigurationManagement" })
	public void test18_subscribeChannel_toSystem(){
		task_ConfigMang.subscribeSystemToChannel("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test18_subscribeChannel_toSystem",groups = { "testplan-ConfigurationManagement" })
	public void test19_unsubscribeChannel_fromSystem(){
		task_ConfigMang.unsubscribeSystemToChannel("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test19_unsubscribeChannel_fromSystem",groups = { "testplan-ConfigurationManagement" })
	public void test20_subscribe_deployFile(){
		task_ConfigMang.subscribeSystemDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}
	
	@Test(dependsOnMethods="test20_subscribe_deployFile",groups = { "testplan-ConfigurationManagement" })
	public void test21_localOverrideOfFile_singleFileDeploy(){	
		task_ConfigMang.localOverrideSingleDeploy("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	@Test(dependsOnMethods="test21_localOverrideOfFile_singleFileDeploy",groups = { "testplan-ConfigurationManagement" })
	public void test22_compare_twoConfigFiles(){
		task_ConfigMang.compareTwoConfigFilesAfterChange("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	@Test(dependsOnMethods="test22_compare_twoConfigFiles",groups = { "testplan-ConfigurationManagement" })
	public void test23_deploy_mulitpleFiles(){
		task_ConfigMang.deployMultipleFiles("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test22_compare_twoConfigFiles",groups = { "testplan-ConfigurationManagement" })
	public void test24_copyFiles_toLocalSystems(){	
		task_ConfigMang.copyMultipleFilesToSystems("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}
	
	@Test(dependsOnMethods="test24_copyFiles_toLocalSystems",groups = { "testplan-ConfigurationManagement" })
	public void test25_deploy_localOverrides(){	
		task_ConfigMang.localOverrideMultipleDeploy("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test24_copyFiles_toLocalSystems",groups = { "testplan-ConfigurationManagement" })
	public void test26_SDC_compare_multipleFiles(){
		task_ConfigMang.sdcCompareMultipleFiles("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test26_SDC_compare_multipleFiles",groups = { "testplan-ConfigurationManagement" })
	public void test27_SDC_createDeleteCreateCopyFiles(){
		task_ConfigMang.sdcCreateDeleteCreateCopyFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}
	
	@Test(dependsOnMethods="test27_SDC_createDeleteCreateCopyFiles",groups = { "testplan-ConfigurationManagement" })
	public void test28_SDC_deployFile(){	
		task_ConfigMang.sdcDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test28_SDC_deployFile",groups = { "testplan-ConfigurationManagement" })
	public void test29_SDC_editReDeployFile(){
		task_ConfigMang.sdcEditReDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test29_SDC_editReDeployFile",groups = { "testplan-ConfigurationManagement" })
	public void test30_SDC_manageConfigChannels(){
		task_ConfigMang.sdcManageConfigChannels(IRhnBase.SYSTEM_HOSTNAME01);
	}

	
	public void test31_DeleteAllConfig(){
		task_RhnBase.deleteAllConfigChannels(true);
	}
}

