package com.redhat.rhn.harness.Space01;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class SatelliteSync extends SeleniumSetup {
	protected RhnHelper rh = new RhnHelper();
	
	protected String centosChannelName = "Automation CentOS Test For Sat Sync";
	protected String centosChannelLabel = "autocentostestsatsync";
	protected String rhelChannelName = "Automation RHEL Test For Sat Sync";
	protected String rhelChannelLabel = "autorheltestsatsync";
	protected String nevraExport = "export-auto-sat-sync-nevra-01.tar.bz2";
	
	protected String nevraPkg1 = "hesinfo-3.1.0-1.1.i386";
	protected String nevraPkg2 = "procinfo-18-19.i386";
	
	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel(centosChannelLabel);
		task_Channels.deleteCustomChannel(rhelChannelLabel);
	}
	
	@Test(groups = { "testplan-SatelliteSync-NEVRA", "testplan-NEVRA" })
	public void test01_VerifySyncNEVRA(){
		task_RhnBase.OpenAndLogIn();
		if (HarnessConfiguration.isNevraTestingEnabled() == false) {
			return;
		}
		task_Channels.verifyNEVRAisEnabled();
		String exportLocation = "/tmp/export-auto-sat-sync-nevra-01";
		String exportFile = "./src/main/resources/export-auto-sat-sync-nevra-01.tar.bz2";
		List<String> channels = new ArrayList<String>();
		channels.add(centosChannelLabel);
		channels.add(rhelChannelLabel);
		task_SatSync.copyExportToSat(exportFile, "/tmp");
		task_SatSync.sync(exportLocation, channels);
	}
	
	@Test(dependsOnMethods="test01_VerifySyncNEVRA", groups={"testplan-SatelliteSync-NEVRA", "testplan-NEVRA"})
	public void test02_VerifyChannelsExistNEVRA() {
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyChannelExists(centosChannelName, true);
		task_Channels.verifyChannelExists(rhelChannelName, true);
	}
	
	@Test(dependsOnMethods="test02_VerifyChannelsExistNEVRA", groups={"testplan-SatelliteSync-NEVRA", "testplan-NEVRA"})
	public void test03_VerifyPackagesNEVRA() {
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel(centosChannelName, nevraPkg1);
		task_Channels.verifyPackageInChannel(centosChannelName, nevraPkg2);
		task_Channels.verifyPackageInChannel(rhelChannelName, nevraPkg1);
		task_Channels.verifyPackageInChannel(rhelChannelName, nevraPkg2);
	}
	
	@Test(dependsOnMethods="test03_VerifyPackagesNEVRA", groups={"testplan-SatelliteSync-NEVRA", "testplan-NEVRA"})
	public void test04_VerifyProvidersNEVRA() {
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyProviderOfPackageInChannel(centosChannelName, nevraPkg1, "CentOS");
		task_Channels.verifyProviderOfPackageInChannel(centosChannelName, nevraPkg2, "CentOS");
		task_Channels.verifyProviderOfPackageInChannel(rhelChannelName, nevraPkg1,  "Red Hat, Inc.");
		task_Channels.verifyProviderOfPackageInChannel(rhelChannelName, nevraPkg2,  "Red Hat, Inc.");
	}
		
	@Test(dependsOnMethods="test04_VerifyProvidersNEVRA", groups={"testplan-SatelliteSync-NEVRA", "testplan-NEVRA"})
	public void test05_VerifyFileSystemPathNEVRA() 
	{
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyFileSystemPathOfPackageInChannel(centosChannelName, nevraPkg1);
		task_Channels.verifyFileSystemPathOfPackageInChannel(centosChannelName, nevraPkg2);
		task_Channels.verifyFileSystemPathOfPackageInChannel(rhelChannelName, nevraPkg1);
		task_Channels.verifyFileSystemPathOfPackageInChannel(rhelChannelName, nevraPkg2);
	}
	
	@Test(dependsOnMethods="test05_VerifyFileSystemPathNEVRA", groups={"testplan-SatelliteSync-NEVRA", "testplan-NEVRA"})
	public void test06_VerifyMD5SumNEVRA() {
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyMd5sumOfPackageInChannel(centosChannelName, nevraPkg1);
		task_Channels.verifyMd5sumOfPackageInChannel(centosChannelName, nevraPkg2);
		task_Channels.verifyMd5sumOfPackageInChannel(rhelChannelName, nevraPkg1);
		task_Channels.verifyMd5sumOfPackageInChannel(rhelChannelName, nevraPkg2);
	}
}
