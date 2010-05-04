package com.redhat.rhn.harness.satellite42;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

@Test(groups="tests")
public class SdcSoftware extends SeleniumSetup{

	protected RhnHelper rh = new RhnHelper();

	
	

	static String firstSnapShot = "firstSnapShot";
	

	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);    
		task_TestPrep.command_rpm(" -e squid --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e httpd --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e zsh --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e zsh-html --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e rhns-proxy-broker --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e rhns-proxy-management --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
		task_Sdc.prepUp2dateFile(IRhnBase.SYSTEM_HOSTNAME01);
	}
	
	/*public void testPrep04(){
		
		if(rb.checkRHELVersion(IRhnBase.SYSTEM_HOSTNAME01) >= 5)
			prepRhnYumPlugin(IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void testPrep05(){
		
		if(rb.checkRHELVersion(IRhnBase.SYSTEM_HOSTNAME01) >= 5)
			rhnPluginFix(IRhnBase.SYSTEM_HOSTNAME01);
	}*/
	
	@Test(groups="sniff")
	public void test00Sniff(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
		task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "dos2unix");
		task_Sdc.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "dos2unix");
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "dos2unix");
		task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "dos2unix");
	}


	@Test(groups="testplan-SDCSoftware")
	public void test04_takeSnapShot_baseSnapShot(){
		task_Sdc.takeSnapShot(IRhnBase.SYSTEM_HOSTNAME01, true);  
	}

	@Test(dependsOnMethods="test04_takeSnapShot_baseSnapShot",groups = { "testplan-SDCSoftware" })
	public void test05_installPackage(){
		log.info("test05_installPackage");
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test05_installPackage",groups = { "testplan-SDCSoftware" })
	public void test06_listRemovePackages(){
		//this will install the package "zsh"
		task_Sdc.listRemovePackages(IRhnBase.SYSTEM_HOSTNAME01, true);
		//task_SdcSoftware.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "squid");
		//assertFalse(task_SdcSoftware.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "squid"));
	}

	/**
	 * package rollback have been broken on the client code
	 */
	@Test(dependsOnMethods="test06_listRemovePackages",groups = { "testplan-SDCSoftware" })
	public void test07_rollBackPackages_afterPackageInstall(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh");
		task_Sdc.rollBackPackages(IRhnBase.SYSTEM_HOSTNAME01, false);
		assertFalse(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh"));
	}

	
	@Test(dependsOnMethods="test07_rollBackPackages_afterPackageInstall",groups = { "testplan-SDCSoftware" })
	public void test08_updatePackages(){
		//task_SdcSoftware.rollBackPackages(IRhnBase.SYSTEM_HOSTNAME01, true);
		task_Sdc.updateErrataAndPackages(IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	@Test(dependsOnMethods="test08_updatePackages",groups = { "testplan-SDCSoftware" })
	public void test09_rollBackPackage_afterUpdate(){
		task_Sdc.rollBackPackages(IRhnBase.SYSTEM_HOSTNAME01, true);
		if((task_Sdc.checkSystemForErrataUpdates(IRhnBase.SYSTEM_HOSTNAME01) == false) && (task_Sdc.checkSystemForPackageUpdates(IRhnBase.SYSTEM_HOSTNAME01) == false )){
			throw new SeleniumException("no errata or package updates found, profile rollback did not work");
		}
	}

	
	@Test(dependsOnMethods="test09_rollBackPackage_afterUpdate",groups = { "testplan-SDCSoftware" })
	public void test10_updatePackages(){
		task_Sdc.updateErrataAndPackages(IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	@Test(dependsOnMethods="test10_updatePackages",groups = { "testplan-SDCSoftware" })
	public void test11_verifyPackages(){
		task_Sdc.verifyPackages(IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test11_verifyPackages",groups = { "testplan-SDCSoftware" })
	public void test12_createSoftwareProfile(){
		task_Sdc.createProfile(IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="test12_createSoftwareProfile",groups = { "testplan-SDCSoftware" })
	public void test13_compareSoftwareProfiles(){
		task_Sdc.createProfileAndCompare(IRhnBase.SYSTEM_HOSTNAME01);
	}
	
	/**
	 * package rollback have been broken on the client code
	 */
	@Test(dependsOnMethods="test13_compareSoftwareProfiles",groups = { "testplan-SDCSoftware" })
	public void test14_rollBackPackages(){
		task_Sdc.rollBackPackages(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

}
