package com.redhat.rhn.harness.satellite50;

import java.util.Random;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat50.tasks.ConfigManagement;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;


public class ConfigManagementScale extends ConfigManagement{
	RhnHelper rh = new RhnHelper();
	
	protected static String AutoChannelName = "01RhnAuto";
	protected static String AutoChannelLabel = "01RhnAutoLabel";
	protected static String AutoChannelDesc = "01RhnAutoLabel";

	protected static String AutoImportChannelName = "ImportRhnAuto";
	protected static String AutoImportChannelLabel = "ImportRhnAutoLabel";
	protected static String AutoImportChannelDesc = "ImportRhnAutoLabel";
	protected static String file01="/tmp/testMultDeploy01";
    protected static String file02="/tmp/uploadfile01-import";
    protected static String file03="/tmp/testMultDeploy02";
    protected static String fileSingle="/tmp/testSingleFile";
    protected static String fileSDCSingle="/tmp/testSDCSingleFile";

	public void test01Prep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
	}

	public void test02Prep02(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.TESTPREFIX, true);
	}

	public void test03Prep03(){
		
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void test04Prep04(){
		
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
	}

	public void test05PrepEnableRHNConfigMgt(){
		
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, true);
	}
	
	public void test07CreateNewConfigChannel(){
		Random rand = new Random();
		int n = 10000;
		int i = rand.nextInt(n+1);
		
		CreateNewConfigChannel("scaletest"+i, true, false, true);
		for(int loop=0;loop<200;loop++){
			CreateNewConfigChannel("scaletest"+i, false, false, true);
		}

	}

	/*public void test06PrepDeleteConfigChannel(){
		
		rb.DeleteConfigChannel("RhnAuto01", true);

	}

	

	public void test08DeleteAndCreateConfigChannel(){
		
		rb.DeleteConfigChannel("RhnAuto01", true);
		rb.CreateNewConfigChannel("RhnAuto01", false, false, true);
	}

	public void test09ConfigChannelErrorMsg01(){
		
		ConfigChannelErrorMsg01();
	}

	public void test10CreateDuplicateConfigChannel(){
		String myChannel = "duplicate";
		
		rb.CreateNewConfigChannel(myChannel, true, true, true);
		rb.CreateNewConfigChannel(myChannel, false, false, false);
		Assert.assertTrue(rh.isTextPresent("Label 'duplicate' already exists. Please choose a different label for the new channel."));
	}

	public void test11UploadFilesToChannel01(){
		
		rb.CreateNewConfigChannel("UpLoadFile01", true, true, true);
		rb.removeConfigFile("UploadFile01", "/tmp/redhat-release", false, false);
		rb.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", false, "/tmp/redhat-release", true, 1);
	}



	public void test11UploadFilesToChannelBinary01(){
		
		rb.CreateNewConfigChannel("UpLoadFile01", true, true, true);
		rb.removeConfigFile("UploadFile01", "/tmp/binary", false, false);
		rb.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", true, "/tmp/binary", true, 1);
	}

	public void test11UploadFilesToChannelBinary02(){
		String myChannel = "UploadFile01";
		String myFile = "/tmp/updatebin";
		
		rb.CreateNewConfigChannel(myChannel, true, true, true);
		rb.removeConfigFile(myChannel, myFile, false, false);
		rb.uploadFilesToChannel(myChannel, "/etc/redhat-release", true, myFile, true, 1);

		rb.updateConfigurationFile(myChannel, myFile, "", 2, false, true);
		rb.updateConfigurationFile(myChannel, myFile, "", 3, false, true);
		rb.updateConfigurationFile(myChannel, myFile, "", 4, false, true);

	}

	public void test11UploadFilesToChannel03(){
		
		rb.CreateNewConfigChannel("UpLoadFile01", true, true, true);
		rb.removeConfigFile("UploadFile01", "/tmp/uploadfile01-import", false, false);
		rb.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", false, "/tmp/uploadfile01-import", true, 1);
		rb.uploadFilesToChannel("UpLoadFile01", "/etc/redhat-release", false, "/tmp/uploadfile01-import", false, 1);
		Assert.assertTrue(rh.isTextPresent("A file named \"/tmp/uploadfile01-import\" already exists in this channel."));
	}

	public void test12ImportFilesToChannel(){
		
		ImportFilesToChannel("ImportFile01", "/tmp/uploadfile01");

	}

	public void test13CreateFileInChannel(){
		
		CreateFileInChannel("CreateFile01", "/tmp/createdfile01", "This file was created by automation");

	}

	public void test14CreateFileInChannelWithError(){
		
		CreateFileInChannelWithError("RhnAuto01", "shouldFail", "This file was created by automation");

	}

	public void test15CreateDirectoryInChannel(){
		
		CreateDirectoryInChannel("CreateDir01", "/tmp/testdir01");

	}

	public void test16CreateDuplicateDirectoryInChannel(){
		
		CreateDuplicateDirectoryInChannel("DupDir", "/tmp/testdupdir01-qaz");

	}

	public void test17CreateMultipleRevisionsOfDirectoryInChannel(){
		
		CreateMultipleRevisionOfDirectoryInChannel("DupDir", "/tmp/testdupdir01-qaz");

	}

	public void test18SubscribeChannelToSystem(){
		
		subscribeSystemToChannel("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}

	public void test19UnsubscribeChannelToSystem(){
		
		unsubscribeSystemToChannel("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}

	public void test20SubscribeAndDeployFile(){
		
		subscribeSystemDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}
	public void test21LocalOverrideSingleDeploy(){
		
		localOverrideSingleDeploy("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01, false);

	}

	public void test22CompareTwoConfigFiles(){
		
		compareTwoConfigFilesAfterChange("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01, false);

	}

	public void test23DeployMulitpleFiles(){
		
		deployMultipleFiles("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}

	public void test24CopyFilesToLocalSystems(){
		
		copyMultipleFilesToSystems("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}
	public void test25DeployLocalOverrides(){
		
		localOverrideMultipleDeploy("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}

	public void test26SDCCompareMultipleFiles(){
		
		sdcCompareMultipleFiles("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}

	public void test27SDCCreateDeleteCreateCopyFile(){
		
		sdcCreateDeleteCreateCopyFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}
	public void test28SDCDeployFile(){
		
		sdcDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}

	public void test29SDCEditReDeployFile(){
		
		sdcEditReDeployFile("channelSystemTest", IRhnBase.SYSTEM_HOSTNAME01);

	}

	public void test30SDCManageConfigChannels(){
		
		sdcManageConfigChannels(IRhnBase.SYSTEM_HOSTNAME01);

	}
*/

}

