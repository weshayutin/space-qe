package com.redhat.rhn.harness.satellite42;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class ActivationKeys extends SeleniumSetup {

	protected RhnHelper rh = new RhnHelper();
	public static String RhnReg_basic01 = "rhnreg_ks --IRhnBase.USERname="+IRhnBase.USER+ "  --IRhnBase.PASSWORD="+ IRhnBase.PASSWORD;
	public static String SERVER_REG = "http://"+HarnessConfiguration.RHN_HOST + "/XMLRPC";
	protected String KEY = "123456";
	protected String ADDITIONAL_COMMAND = "--activationkey="+KEY+"  --force";

	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.deleteActivationKey("autotest01", false);
		task_ActivationKeys.deleteActivationKey("autotest02", false);
		task_ActivationKeys.deleteActivationKey("autotest03", false);
		task_TestPrep.modifyServerParent(IRhnBase.SYSTEM_HOSTNAME01, true, "serverURL="+SERVER_REG);
	}
	
	@Test(groups="sniff")
	public void test01Sniff(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.createActivationKey("sniff", "12345", "5", false, false, false, false, false);
		task_ActivationKeys.deleteActivationKey("sniff", true);
	}
	
	
	@Test(groups="testplan-ActivationKeys")
	public void test03_createKey(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.createActivationKey("autotest01", "12345", "5", false, false, false, false, false);
	}

	@Test(dependsOnMethods="test03_createKey",groups = { "testplan-ActivationKeys" })
	public void test04_deleteKey(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.deleteActivationKey("autotest01", false);
	}

	@Test(dependsOnMethods="test04_deleteKey",groups = { "testplan-ActivationKeys" })
	public void test05_createKey(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.createActivationKey("autotest02", "12345", "5", false, false, false, false, false);
	}

	@Test(dependsOnMethods="test05_createKey",groups = { "testplan-ActivationKeys" })
	public void test06_addProvisioningEntitlement_toKey(){
		task_ActivationKeys.addProvisioningToKey("autotest02");
	}

	@Test(dependsOnMethods="test06_addProvisioningEntitlement_toKey",groups = { "testplan-ActivationKeys" })
	public void test07_createKey_withProvisioning(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.createActivationKey("autotest03", KEY, "1", false, true, false, false, false);
	}

	@Test(dependsOnMethods="test07_createKey_withProvisioning",groups = { "testplan-ActivationKeys" })
	public void test08_register_withProvisioning(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"prof01",ADDITIONAL_COMMAND,IRhnBase.USER, IRhnBase.PASSWORD,true, false);
		boolean activated = task_ActivationKeys.isSystemActivated("prof01", "autotest03");
		Assert.assertTrue(activated, "System '"+IRhnBase.SYSTEM_HOSTNAME01+"' has been registered using activation key '"+"autotest03"+"'.");
	}

	@Test(dependsOnMethods="test08_register_withProvisioning",groups = { "testplan-ActivationKeys" })
	public void test09_activationKey_register_limit_negative(){	
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"prof02",ADDITIONAL_COMMAND,IRhnBase.USER, IRhnBase.PASSWORD,false, false);
	}

	@Test(dependsOnMethods="test09_activationKey_register_limit_negative",groups = { "testplan-ActivationKeys" })
	public void test10_installPackage_activationKey(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01," --force",IRhnBase.USER, IRhnBase.PASSWORD,true, false);
		task_Sdc.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh");
		Assert.assertFalse(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh"));
		task_ActivationKeys.addPackageToKey("autotest03", "zsh");
		task_TestPrep.unregisterSystem("prof01", false);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01,ADDITIONAL_COMMAND,IRhnBase.USER, IRhnBase.PASSWORD,true, false);
		Assert.assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh"));
	}

	@Test(dependsOnMethods="test10_installPackage_activationKey",groups = { "testplan-ActivationKeys" })
	public void test11_installMultiplePackages_activationKey(){	
		task_RhnBase.OpenAndLogIn();
		task_Sdc.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh");
		task_ActivationKeys.addPackageToKey("autotest03", "zsh\nscreen\npdksh\n");
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01,ADDITIONAL_COMMAND,IRhnBase.USER, IRhnBase.PASSWORD,true, false);
		Assert.assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh"));
		Assert.assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "screen"));
		Assert.assertTrue(task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "pdksh"));
	}
	
	@AfterClass
	public void test12_deleteKey(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.deleteActivationKey("autotest01", false);
		task_ActivationKeys.deleteActivationKey("autotest02", false);
		task_ActivationKeys.deleteActivationKey("autotest03", false);
	}

}
