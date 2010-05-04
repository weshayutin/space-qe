package com.redhat.rhn.harness.regression.satellite521;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.Hosted.pages.RhnCommon;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Hosted.tasks.ConfigManagement;
import com.redhat.rhn.harness.common.Space01.tasks.RhnBase;

/**
 * This class should be TestNG-ed with:
 *  "sat.version=5.3"
 * @author gkhachik
 *
 */
public class BZ_501086 extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	protected static String clonedChannel01 = "autoclone01";
	protected static String autoErrata01 = "BZ-501086";
	protected static String autoErrataChannel01 = "autoerratachannel01";
	protected static String actKeyCustomChannel = "BZ 501086";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		// cleanup
		task_Channels.deleteAllCustomChannels();
		task_ActivationKeys.deleteActivationKey(actKeyCustomChannel, false);
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.command_rpm(" -e testAutoFile --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		// setup
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, clonedChannel01 ,clonedChannel01, 0);
		String sarch = task_Channels.getChannelArchByName(clonedChannel01);
		task_Channels.createCustomChannelWithParent(autoErrataChannel01, clonedChannel01, sarch);
		task_ActivationKeys.createActivationKey(actKeyCustomChannel, "", "", clonedChannel01, false, true, false, false, false);
		task_ActivationKeys.addChildChannelToKey(actKeyCustomChannel,	autoErrataChannel01);
		
	}

	@Test(groups = { "bz-501086" })
	public void test02_pushPackageRegClientAndSetupPackage(){
		task_RhnBase.OpenAndLogIn();
		String act_key = task_ActivationKeys.getAKFromKeyName(actKeyCustomChannel);
		task_Channels.rhnPushPackageToChannel(autoErrataChannel01, 
				"testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", 
				IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
		task_TestPrep.registerSystemWithActKey(IRhnBase.SYSTEM_HOSTNAME01,
				IRhnBase.SYSTEM_HOSTNAME01,act_key,true);
		task_TestPrep.command_yum("install -y testAutoFile --nogpgcheck", 
				IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_updateSystemProfile(IRhnBase.SYSTEM_HOSTNAME01);
		assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "testAutoFile"));
	}
		
	@Test(dependsOnMethods="test02_pushPackageRegClientAndSetupPackage", 
			groups = { "bz-501086" })
	public void test03_pushPackageNewVerMakeCustomErrataWithPackage(){
		int old_erratasCount = -1;
		int old_packagesCount = -1;
		int new_erratasCount = -1;
		int new_packagesCount = -1;
		
		// Fix errata/package counts for the client
		task_RhnBase.OpenAndLogIn();		
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		old_erratasCount = Integer.parseInt(
				sel.getText("xpath=//table[@class='list']/tbody/tr[1]/td[4]").trim());
		old_packagesCount = Integer.parseInt(
				sel.getText("xpath=//table[@class='list']/tbody/tr[1]/td[5]").trim());

		task_Channels.rhnPushPackageToChannel(autoErrataChannel01, 
				"testAutoFile-2-1.0.i386.rpm", "./src/main/resources/", 
				IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
		task_ErrataMang.CreateNewErrata(autoErrata01);
		task_ErrataMang.publishErrata(autoErrata01, clonedChannel01, "testAutoFile-2-1.0");

		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		new_erratasCount = Integer.parseInt(
				sel.getText("xpath=//table[@class='list']/tbody/tr[1]/td[4]").trim());
		new_packagesCount = Integer.parseInt(
				sel.getText("xpath=//table[@class='list']/tbody/tr[1]/td[5]").trim());
		
		// assertions...
		log.info("before errata push:  packages["+old_packagesCount+"]; erratas ["+old_erratasCount+"]");
		log.info("  after errata push:  packages["+new_packagesCount+"]; erratas ["+new_erratasCount+"]");
		Assert.assertTrue((new_packagesCount - old_packagesCount) >= 0);
		Assert.assertTrue((new_erratasCount - old_erratasCount) >= 0);
	}
	
}
