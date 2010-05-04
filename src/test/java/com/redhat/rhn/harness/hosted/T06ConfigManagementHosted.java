package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.ConfigManagement;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;


public class T06ConfigManagementHosted extends ConfigManagement{
	RhnHelper rh = new RhnHelper();

	

	public void testPrep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	public void testPrep02(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.TESTPREFIX, true);
	}

	public void testPrep03(){
		
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void testPrep04(){
		
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_REG_CMD,true, true);

	}
/*	public void testEnableAndUnScheduleRHNConfigMgt(){
		
		rb.enableAndUnScheduleRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01);

	}*/

	public void testPrepEnableRHNConfigMgt(){
		
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, true, true);
	}

	public void testPrepDeleteConfigChannel(){
		
		DeleteConfigChannel("RhnAuto01", true);
		
	}

	public void testCreateNewConfigChannel(){
		
		CreateNewConfigChannel("RhnAuto01", true, false, true);
		
	}

	public void testDeleteAndCreateConfigChannel(){
		
		//DeleteAndCreateConfigChannel("RhnAuto01");
		
	}


	public void testConfigChannelErrorMsg01(){
		
		ConfigChannelErrorMsg01();
		
	}

	public void testCreateDuplicateConfigChannel(){
		
	//	CreateDuplicateConfigChannel();
		
	}

	public void testUploadFilesToChannel(){
		
		uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", false, "/tmp/uploadfile01", true, 1);
		
	}

	/*public void testUploadBinaryFilesToChannel(){
		
		uploadBinaryFilesToChannel("UpLoadFile01", "/etc/redhat-release", "/tmp/uploadfile01");
		
	}*/

	public void testImportFilesToChannel(){
		
		ImportFilesToChannel("ImportFile01", "/tmp/uploadfile01");
		
	}

	public void testCreateFileInChannel(){
		
		CreateFileInChannel("CreateFile01", "/tmp/createdfile01", "This file was created by automation");
		
	}

	public void testCreateFileInChannelWithError(){
		
		CreateFileInChannelWithError("RhnAuto01", "shouldFail", "This file was created by automation");
		
	}

	public void testCreateDirectoryInChannel(){
		
		CreateDirectoryInChannel("CreateDir01", "/tmp/testdir01");
		
	}

	public void testCreateDuplicateDirectoryInChannel(){
		
		CreateDuplicateDirectoryInChannel("DupDir", "/tmp/testdupdir01-qaz");
		
	}

	public void testCreateMultipleRevisionsOfDirectoryInChannel(){
		
		CreateMultipleRevisionOfDirectoryInChannel("DupDir", "/tmp/testdupdir01-qaz");
		
	}

	public void testSubscribeChannelToSystem(){
		
		subscribeSystemToChannel("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}

	public void testUnsubscribeChannelToSystem(){
		
		unsubscribeSystemToChannel("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}

	public void testSubscribeAndDeployFile(){
		
		subscribeSystemDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}
	public void testLocalOverrideSingleDeploy(){
		
		localOverrideSingleDeploy("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01, true);
		
	}

	public void testCompareTwoConfigFiles(){
		
		compareTwoConfigFilesAfterChange("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01, false);
		
	}

	public void testDeployMulitpleFiles(){
		
		deployMultipleFiles("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}

	public void testCopyFilesToLocalSystems(){
		
		copyMultipleFilesToSystems("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}
	public void testDeployLocalOverrides(){
		
		localOverrideMultipleDeploy("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}

	public void testSDCCompareMultipleFiles(){
		
		sdcCompareMultipleFiles("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}

	public void testSDCCreateDeleteCreateCopyFile(){
		
		sdcCreateDeleteCreateCopyFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}
	public void testSDCDeployFile(){
		
		sdcDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}

	public void testSDCEditReDeployFile(){
		
		sdcEditReDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);
		
	}

	public void testSDCManageConfigChannels(){
		
		sdcManageConfigChannels(IRhnBase.SYSTEM_HOSTNAME01);
		
	}


}
