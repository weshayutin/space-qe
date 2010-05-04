package com.redhat.rhn.harness.Space01;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Space01.tasks.RhnBase;

@Test(groups="tests")
public class Channels extends com.redhat.rhn.harness.satellite51.Channels{
	protected static String customCentos_01 = "autocustomcentos5201";
	protected static String customRhel_01 = "autocustomrhel5201";
	
	protected static String testCentosPkg = "hesinfo-3.1.0-1.1.i386";
	protected static String testRhelPkg = "hesinfo-3.1.0-1.1.i386";
	
	protected static String centosProvider = "CentOS";
	
	
	@BeforeMethod(groups = { "testplan-SoftwareChannelManagement" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		page_RhnCommon.JustOpen();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
	}

	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
		
	}
	
	@AfterClass(groups = { "teardown" })
	public void cleanUp() {
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel(customCentos_01);
		task_Channels.deleteCustomChannel(customRhel_01);
	}	
	

	
	
	
	@Test(groups={"testplan-NEVRA"})
	public void test100_VerifyNEVRAEnabled() {
		if (!HarnessConfiguration.isNevraTestingEnabled()) {throw new SkipException("Skip this test when NEVRA is not enabled.");}
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyNEVRAisEnabled();
	}
	
	@Test(groups={"testplan-NEVRA"}, dependsOnMethods="test100_VerifyNEVRAEnabled")
	public void test101_RhnPushPackageWithSameNEVRA_InDifferentCustomChannels() {
		if (!HarnessConfiguration.isNevraTestingEnabled()) {throw new SkipException("Skip this test when NEVRA is not enabled.");}
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel(customCentos_01);
		task_Channels.deleteCustomChannel(customRhel_01);
		task_Channels.createCustomChannel(customCentos_01);
		task_Channels.createCustomChannel(customRhel_01);
		task_Channels.rhnPushPackageToChannel(customCentos_01, testCentosPkg + ".rpm", 
				"./src/main/resources/centosPkgs/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD, "/tmp/centos");
		task_Channels.rhnPushPackageToChannel(customRhel_01, testRhelPkg + ".rpm", 
				"./src/main/resources/rhelPkgs/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD, "/tmp/rhel");
		task_Channels.verifyPackageInChannel(customCentos_01, testCentosPkg);
		task_Channels.verifyPackageInChannel(customRhel_01, testRhelPkg );
	}
	
	@Test(groups={"testplan-NEVRA"}, dependsOnMethods="test101_RhnPushPackageWithSameNEVRA_InDifferentCustomChannels")
	public void test102_NEVRA_checkFileSystemPaths() {
		if (!HarnessConfiguration.isNevraTestingEnabled()) {throw new SkipException("Skip this test when NEVRA is not enabled.");}
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel(customCentos_01, testCentosPkg);
		task_Channels.verifyFileSystemPathOfPackageInChannel(customCentos_01, testCentosPkg);
	}
	
	@Test(groups={"testplan-NEVRA"}, dependsOnMethods="test101_RhnPushPackageWithSameNEVRA_InDifferentCustomChannels")
	public void test103_NEVRA_checkMd5sum() {
		if (!HarnessConfiguration.isNevraTestingEnabled()) {throw new SkipException("Skip this test when NEVRA is not enabled.");}
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel(customCentos_01, testCentosPkg);
		task_Channels.verifyMd5sumOfPackageInChannel(customCentos_01, testCentosPkg);
	}
	
	@Test(groups={"testplan-NEVRA"}, dependsOnMethods="test101_RhnPushPackageWithSameNEVRA_InDifferentCustomChannels")
	public void test104_NEVRA_eraseExistingPkgThenRePush() {
		if (!HarnessConfiguration.isNevraTestingEnabled()) {throw new SkipException("Skip this test when NEVRA is not enabled.");}
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel(customCentos_01, testCentosPkg);
		task_Channels.removePackageFromFileSystem(customCentos_01, testCentosPkg);
		task_Channels.rhnPushPackageToChannel(customCentos_01, testCentosPkg + ".rpm", 
				"./src/main/resources/centosPkgs/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD, "/tmp/centos");
		task_Channels.verifyFileSystemPathOfPackageInChannel(customCentos_01, testCentosPkg);
		task_Channels.verifyMd5sumOfPackageInChannel(customCentos_01, testCentosPkg);
		// TODO: Implement means to verify only one instance of this package is present
		// to test, in off chance package is duplicated in webui and displayed twice
		task_Channels.verifyPackageInChannel(customCentos_01, testCentosPkg);
	}
	
	@Test(groups={"testplan-NEVRA"}, dependsOnMethods="test101_RhnPushPackageWithSameNEVRA_InDifferentCustomChannels")
	public void test105_NEVRA_checkProvidersInfo() {
		if (!HarnessConfiguration.isNevraTestingEnabled()) {throw new SkipException("Skip this test when NEVRA is not enabled.");}
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel(customCentos_01, testCentosPkg);
		task_Channels.verifyProviderOfPackageInChannel(customCentos_01, testCentosPkg, centosProvider);
		
	}
	
}
